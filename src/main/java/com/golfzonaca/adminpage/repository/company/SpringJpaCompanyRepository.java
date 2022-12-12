package com.golfzonaca.adminpage.repository.company;

import com.golfzonaca.adminpage.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaCompanyRepository extends JpaRepository<Company, Long> {
}
