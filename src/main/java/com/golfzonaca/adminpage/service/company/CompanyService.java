package com.golfzonaca.adminpage.service.company;

import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.company.CompanySearchCond;
import com.golfzonaca.adminpage.service.company.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    Company save(CompanyDto companyDto);

    void delete(Long companyId);

    List<Company> findCompanies(String companyName);

    Company findById(Long companyId);

}
