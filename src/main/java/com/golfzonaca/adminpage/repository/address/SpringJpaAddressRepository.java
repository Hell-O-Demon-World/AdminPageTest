package com.golfzonaca.adminpage.repository.address;

import com.golfzonaca.adminpage.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaAddressRepository extends JpaRepository<Address, Long> {
}
