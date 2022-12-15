package com.golfzonaca.adminpage.repository.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringJpaInquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByOrderByIdDesc();
}
