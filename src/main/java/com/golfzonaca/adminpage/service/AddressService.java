package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Location;

import java.util.Optional;

public interface AddressService {

     Location save(Location location);

     Optional<Location> findByAddressId(Long id);

     void delete(Long id);
}
