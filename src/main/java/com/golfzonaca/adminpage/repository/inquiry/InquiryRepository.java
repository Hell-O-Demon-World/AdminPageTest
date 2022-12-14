package com.golfzonaca.adminpage.repository.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;

import java.util.List;

public interface InquiryRepository {
    List<Inquiry> findAll();

    Inquiry findById(Long inquiryId);
}
