package com.golfzonaca.adminpage.repository.company;

import com.golfzonaca.adminpage.domain.Company;

import java.util.List;

public interface CompanyRepository {

    Company save(Company company);

    void delete(Company company);

    List<Company> findCompanies(String companyName);

    Company findById(Long companyId);
}
