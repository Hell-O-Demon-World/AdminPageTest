package com.golfzonaca.adminpage.service.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.address.AddressRepository;
import com.golfzonaca.adminpage.repository.company.CompanyRepository;
import com.golfzonaca.adminpage.service.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    @Value("${kakao.map.apiKey}")
    private String kakaoMapApiKey;

    @Override
    public Company save(CompanyDto companyDto) {

        Map<String, Double> coordinate = getCoordinate(companyDto.getAddress());

        Address address = addressRepository.save(new Address(companyDto.getAddress(), companyDto.getPostalCode(), coordinate.get("lng"), coordinate.get("lat")));

        Company company = new Company(companyDto.getCompanyLoginId(),
                companyDto.getCompanyPw(),
                companyDto.getCompanyName(),
                companyDto.getCompanyTel(),
                companyDto.getCompanyRegNum(),
                companyDto.getCompanyRepName(),
                address);

        return companyRepository.save(company);
    }

    private Map<String, Double> getCoordinate(String address) {
        Map<String, Double> coordinate = new LinkedHashMap<>();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String url = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/address.json")
                .queryParam("query", "{query}")
                .encode()
                .toUriString();

        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        headers.set("AUTHORIZATION", kakaoMapApiKey);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        Map<String, Object> params = new HashMap<>();
        params.put("query", address);

        Object object = restTemplate.exchange(url, HttpMethod.GET, request, Object.class, params).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(object, Map.class);
        List<Map<String, String>> elements = (List<Map<String, String>>) map.get("documents");
        Map<String, String> coordinateMap = elements.get(0);
        Double lng = Double.valueOf(coordinateMap.get("x"));
        Double lat = Double.valueOf(coordinateMap.get("y"));
        coordinate.put("lng", lng);
        coordinate.put("lat", lat);
        return coordinate;
    }

    @Override
    public void delete(Long companyId) {
        Company findCompany = companyRepository.findById(companyId);
        Address address = findCompany.getAddress();
        companyRepository.delete(findCompany);
        addressRepository.delete(address);
    }


    @Override
    public List<Company> findCompanies(String companyName) {
        return companyRepository.findCompanies(companyName);
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId);
    }
}
