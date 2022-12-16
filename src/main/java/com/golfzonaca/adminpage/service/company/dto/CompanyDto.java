package com.golfzonaca.adminpage.service.company.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyDto {

    private Long id;
    private String address;
    private String companyLoginId;
    private String companyPw;
    private String companyName;
    private String companyTel;
    private String companyRegNum;
    private String companyRepName;
    private String postalCode;

}
