package com.golfzonaca.adminpage.repository.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaInquiryRepository extends JpaRepository<Inquiry, Long> {
}
