package com.golfzonaca.adminpage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    
    private Long id;
    private String companyLoginId; // 업체의 백오피스 아이디
    private String companyPw;
    private String companyName;
    private String companyTel;
    private String companyRegNum;
    private String companyRepName;
    private Long addressId;
}