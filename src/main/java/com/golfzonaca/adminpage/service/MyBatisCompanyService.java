package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MyBatisCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public Company save(Company company) {
        log.info("company.getCompanyPw()={}",company.getCompanyPw());
        String encodedPw = bCryptPasswordEncoder.encode(company.getCompanyPw());
        Company savedCompany = companyRepository.save(new Company(
                company.getId(),
                company.getCompanyLoginId(), encodedPw,
                company.getCompanyName(), company.getCompanyTel(),
                company.getCompanyRegNum(), company.getCompanyRepName(),
                company.getAddressId()));
        company.setId(savedCompany.getId());
        return company;
    }

    @Override
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    public List<Company> findCompanies(CompanySearchCond companySearch) {
        return companyRepository.findCompanies(companySearch);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
}