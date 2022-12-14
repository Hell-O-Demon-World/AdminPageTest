package com.golfzonaca.adminpage.service.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InquiryData {
    private Long inquiryId;
    private String inquiryTitle;
    private String inquiryContext;
    private String writtenDateTime;
    private Boolean inquiryStatus;
}
