package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Location;
import com.golfzonaca.adminpage.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBatisAddressService implements AddressService{

    private final AddressRepository addressRepository;

    @Override
    public Location save(Location location) {
        return addressRepository.save(location);
    }

    @Override
    public Optional<Location> findByAddressId(Long id) {
        return addressRepository.findByAddressId(id);
    }

    @Override
    public void delete(Long id) {
        addressRepository.delete(id);
    }
}
