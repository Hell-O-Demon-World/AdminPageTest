package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AddressMapper {

    void save(Location location);

    Optional<Location> findByAddressId(Long id);

    void delete(Long id);
}
