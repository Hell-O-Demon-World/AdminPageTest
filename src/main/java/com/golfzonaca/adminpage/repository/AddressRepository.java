package com.golfzonaca.adminpage.repository;

import com.golfzonaca.adminpage.domain.Location;

import java.util.Optional;

public interface AddressRepository {

    Location save(Location location);

    Optional<Location> findByAddressId(Long id);

    void delete(Long id);
}
