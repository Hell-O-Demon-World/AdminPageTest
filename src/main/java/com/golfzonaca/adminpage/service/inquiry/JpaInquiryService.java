package com.golfzonaca.adminpage.service.inquiry;

import com.golfzonaca.adminpage.domain.Inquiry;
import com.golfzonaca.adminpage.repository.answer.AnswerRepository;
import com.golfzonaca.adminpage.repository.inquiry.InquiryRepository;
import com.golfzonaca.adminpage.repository.inquirystatus.InquiryStatusRepository;
import com.golfzonaca.adminpage.service.inquiry.dto.InquiryData;
import com.golfzonaca.adminpage.service.inquiry.dto.InquiryDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaInquiryService implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryStatusRepository inquiryStatusRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Map<Integer, InquiryData> findAll() {
        List<Inquiry> inquiryList = inquiryRepository.findAll();
        Map<Integer, InquiryData> inquiryData = new LinkedHashMap<>();
        for (int i = 0; i < inquiryList.size(); i++) {
            Inquiry inquiry = inquiryList.get(i);
            inquiryData.put(i, new InquiryData(inquiry.getId(), inquiry.getTitle(), inquiry.getContent(), inquiry.getDateTime().toLocalDate().toString() + " " + inquiry.getDateTime().toLocalTime().toString(), inquiry.getInquiryStatus().getStatus()));
        }
        return inquiryData;
    }

    @Override
    public InquiryDetails findDetails(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId);
        InquiryDetails inquiryDetails = InquiryDetails.builder()
                .inquiryId(inquiry.getId())
                .inquiryTitle(inquiry.getTitle())
                .inquiryContext(inquiry.getContent())
                .writtenDateTime(inquiry.getDateTime().toLocalDate().toString() + " " + inquiry.getDateTime().toLocalTime().toString())
                .inquiryStatus(inquiry.getInquiryStatus().getStatus())
                .build();
        if (inquiry.getAnswer() != null) {
            inquiryDetails.setAnswer(inquiry.getAnswer().getAnswer());
        }
        return inquiryDetails;
    }
}
