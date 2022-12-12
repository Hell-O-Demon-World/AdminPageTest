package com.golfzonaca.adminpage.repository.address;

import com.golfzonaca.adminpage.domain.Address;

public interface AddressRepository {

    Address save(Address address);

    Address findById(Long addressId);

    void delete(Address address);
}
