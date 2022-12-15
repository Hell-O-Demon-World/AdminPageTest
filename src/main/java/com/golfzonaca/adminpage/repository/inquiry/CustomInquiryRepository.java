package com.golfzonaca.adminpage.repository.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
        return jpaRepository.findById(inquiryId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 문의입니다."));
    }
}
