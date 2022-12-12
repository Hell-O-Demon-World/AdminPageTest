package com.golfzonaca.adminpage.repository.company;

import com.golfzonaca.adminpage.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Repository
@RequiredArgsConstructor
public class CustomCompanyRepository implements CompanyRepository {

    private final SpringJpaCompanyRepository springJpaCompanyRepository;
    private final QueryCompanyRepository queryCompanyRepository;


    @Override
    public Company save(Company company) {
        return springJpaCompanyRepository.save(company);
    }

    @Override
    public void delete(Company company) {
        springJpaCompanyRepository.delete(company);
    }

    @Override
    public List<Company> findCompanies(String companyName) {
        return queryCompanyRepository.findCompanies(companyName);
    }

    @Override
    public Company findById(Long companyId) {
        return springJpaCompanyRepository.findById(companyId).orElseThrow(()->new NoSuchElementException("존재하지 않는 업체입니다."));
    }
}
