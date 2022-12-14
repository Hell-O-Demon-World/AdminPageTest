package com.golfzonaca.adminpage.repository.inquirystatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomInquiryStatusRepository implements InquiryStatusRepository {
}
