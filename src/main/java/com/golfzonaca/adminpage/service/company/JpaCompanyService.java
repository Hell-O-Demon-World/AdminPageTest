package com.golfzonaca.adminpage.service.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.exception.WrongAddressException;
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

import java.util.*;

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

        Map<String, String> coordinate = getCoordinate(companyDto.getAddress());
        if (Optional.ofNullable(coordinate.get("postalCode")).isEmpty()) {
            coordinate.put("postalCode", companyDto.getPostalCode());
        }

        Address address = addressRepository.save(new Address(coordinate.get("roadAddress"), coordinate.get("postalCode"), Double.valueOf(coordinate.get("longitude")), Double.valueOf(coordinate.get("latitude"))));

        Company company = new Company(companyDto.getCompanyLoginId(),
                companyDto.getCompanyPw(),
                companyDto.getCompanyName(),
                companyDto.getCompanyTel(),
                companyDto.getCompanyRegNum(),
                companyDto.getCompanyRepName(),
                address);

        return companyRepository.save(company);
    }

    private Map<String, String> getCoordinate(String address) {
        Map<String, String> coordinate = new LinkedHashMap<>();
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
        List<Map<String, Object>> elements = (List<Map<String, Object>>) map.get("documents");
        if (elements.isEmpty()) {
            throw new WrongAddressException("존재하지 않는 주소입니다.");
        }
        Map<String, Object> coordinateMap = elements.get(0);
        Optional<Map<String, String>> optionalAddressMap = Optional.ofNullable((Map<String, String>) coordinateMap.get("roadAddressMap"));
        if (optionalAddressMap.isEmpty()) {
            coordinate.put("roadAddress", address);
            coordinate.put("longitude", (String) coordinateMap.get("x"));
            coordinate.put("latitude", (String) coordinateMap.get("y"));
            return coordinate;
        }
        Map<String, String> roadAddressMap = optionalAddressMap.get();
        String roadAddress = roadAddressMap.get("address_name");
        String postalCode = roadAddressMap.get("zone_no");
        String longitude = roadAddressMap.get("x");
        String latitude = roadAddressMap.get("y");
        coordinate.put("roadAddress", roadAddress);
        coordinate.put("postalCode", postalCode);
        coordinate.put("longitude", longitude);
        coordinate.put("latitude", latitude);
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
