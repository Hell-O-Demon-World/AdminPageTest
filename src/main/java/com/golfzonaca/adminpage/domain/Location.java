package com.golfzonaca.adminpage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private Long id;
    private String address;
    private String postalCode;
}
