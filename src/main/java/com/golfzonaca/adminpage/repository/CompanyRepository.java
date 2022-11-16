package com.golfzonaca.adminpage.repository;

import com.golfzonaca.adminpage.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);

    void delete(Long id);

    List<Company> findCompanies(CompanySearchCond companySearch);

    Optional<Company> findById(Long id);
}
