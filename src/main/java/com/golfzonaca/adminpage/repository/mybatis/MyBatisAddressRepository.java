package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Location;
import com.golfzonaca.adminpage.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisAddressRepository implements AddressRepository {

    private final AddressMapper addressMapper;

    @Override
    public Location save(Location location) {
        addressMapper.save(location);
        return location;
    }

    @Override
    public Optional<Location> findByAddressId(Long id) {
        return addressMapper.findByAddressId(id);
    }

    @Override
    public void delete(Long id) {
        addressMapper.delete(id);
    }
}
