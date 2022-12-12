package com.golfzonaca.adminpage.repository.address;

import com.golfzonaca.adminpage.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Repository
@RequiredArgsConstructor
public class CustomAddressRepository implements AddressRepository {

    private final SpringJpaAddressRepository springJpaAddressRepository;
    private final QueryAddressRepository queryAddressRepository;


    @Override
    public Address save(Address address) {
        return springJpaAddressRepository.save(address);
    }

    @Override
    public Address findById(Long addressId) {
        return springJpaAddressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 주소입니다."));
    }

    @Override
    public void delete(Address address) {
        springJpaAddressRepository.delete(address);
    }
}
