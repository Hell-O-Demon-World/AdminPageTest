package com.golfzonaca.adminpage.service.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class InquiryDetails {
    private Long inquiryId;
    private String inquiryTitle;
    private String inquiryContext;
    private String writtenDateTime;
    private Boolean inquiryStatus;
    private String answer;
}
