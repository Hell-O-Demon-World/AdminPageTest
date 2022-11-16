package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company save(Company company);

    void delete(Long id);

    List<Company> findCompanies(CompanySearchCond companySearch);

    Optional<Company> findById(Long id);
}
