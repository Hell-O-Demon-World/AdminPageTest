package com.golfzonaca.adminpage.service.company;

import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.address.AddressRepository;
import com.golfzonaca.adminpage.repository.company.CompanyRepository;
import com.golfzonaca.adminpage.service.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;

    @Override
    public Company save(CompanyDto companyDto) {
        Address address = addressRepository.save(new Address(companyDto.getAddress(), companyDto.getPostalCode()));

        Company company = new Company(address, companyDto.getCompanyLoginId(),
                companyDto.getCompanyPw(),
                companyDto.getCompanyName(),
                companyDto.getCompanyTel(),
                companyDto.getCompanyRegNum(),
                companyDto.getCompanyRepName());

        return companyRepository.save(company);
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
