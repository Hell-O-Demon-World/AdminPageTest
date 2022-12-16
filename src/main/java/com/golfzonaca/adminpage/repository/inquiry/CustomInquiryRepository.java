package com.golfzonaca.adminpage.repository.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;
import com.golfzonaca.adminpage.exception.NonExistedInquiryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomInquiryRepository implements InquiryRepository {
    private final SpringJpaInquiryRepository jpaRepository;

    @Override
    public List<Inquiry> findAll() {
        return jpaRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Inquiry findById(Long inquiryId) {
        return jpaRepository.findById(inquiryId).orElseThrow(() -> new NonExistedInquiryException("존재하지 않는 문의입니다."));
    }
}
