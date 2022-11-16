package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompanyMapper {

    void save(Company company);

    void delete(Long id);

    List<Company> findCompanies(CompanySearchCond companySearch);

    Optional<Company> findById(Long id);
}
