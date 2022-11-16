package com.golfzonaca.adminpage.config;

import com.golfzonaca.adminpage.repository.AddressRepository;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.mybatis.AddressMapper;
import com.golfzonaca.adminpage.repository.mybatis.CompanyMapper;
import com.golfzonaca.adminpage.repository.mybatis.MyBatisAddressRepository;
import com.golfzonaca.adminpage.repository.mybatis.MyBatisCompanyRepository;
import com.golfzonaca.adminpage.service.AddressService;
import com.golfzonaca.adminpage.service.CompanyService;
import com.golfzonaca.adminpage.service.MyBatisAddressService;
import com.golfzonaca.adminpage.service.MyBatisCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final CompanyMapper companyMapper;

    private final AddressMapper addressMapper;

    @Bean
    public CompanyService companyService() {
        return new MyBatisCompanyService(companyRepository());
    }

    @Bean
    public CompanyRepository companyRepository() {
        return new MyBatisCompanyRepository(companyMapper);
    }

    @Bean
    public AddressService addressService() {
        return new MyBatisAddressService(addressRepository());
    }

    @Bean
    public AddressRepository addressRepository() {
        return new MyBatisAddressRepository(addressMapper);
    }
}