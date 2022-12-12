package com.golfzonaca.adminpage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "Company", columnNames = {"COMPANY_LOGINID", "COMPANY_NAME", "COMPANY_TEL", "COMPANY_REGNUM"})})
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "COMPANY_LOGINID")
    private String companyLoginId; // 업체의 백오피스 아이디

    @Column(name = "COMPANY_PW")
    private String companyPw;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_TEL")
    private String companyTel;

    @Column(name = "COMPANY_REGNUM")
    private String companyRegNum;

    @Column(name = "COMPANY_REPNAME")
    private String companyRepName;

    public void updateAddress(Address address) {
        this.address = address;
    }

    public Company(Address address, String companyLoginId, String companyPw, String companyName, String companyTel, String companyRegNum, String companyRepName) {
        this.address = address;
        this.companyLoginId = companyLoginId;
        this.companyPw = companyPw;
        this.companyName = companyName;
        this.companyTel = companyTel;
        this.companyRegNum = companyRegNum;
        this.companyRepName = companyRepName;
    }
}