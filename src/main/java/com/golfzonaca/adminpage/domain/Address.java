package com.golfzonaca.adminpage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "ADDRESS", columnNames = {"ADDRESS"})})
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "POSTALCODE", nullable = false, length = 5)
    private String postalCode;

    public Address(String address, String postalCode) {
        this.address = address;
        this.postalCode = postalCode;
    }
}
