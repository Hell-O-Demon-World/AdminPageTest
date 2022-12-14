package com.golfzonaca.adminpage.service.inquiry;

import com.golfzonaca.adminpage.service.inquiry.dto.InquiryData;
import com.golfzonaca.adminpage.service.inquiry.dto.InquiryDetails;

import java.util.Map;

public interface InquiryService {
    Map<Integer, InquiryData> findAll();

    InquiryDetails findDetails(Long inquiryId);
}
