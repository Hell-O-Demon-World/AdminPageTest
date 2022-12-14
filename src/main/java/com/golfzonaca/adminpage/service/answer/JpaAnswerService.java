package com.golfzonaca.adminpage.service.answer;

import com.golfzonaca.adminpage.domain.Answer;
import com.golfzonaca.adminpage.domain.Inquiry;
import com.golfzonaca.adminpage.repository.answer.AnswerRepository;
import com.golfzonaca.adminpage.repository.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaAnswerService implements AnswerService {
    private final AnswerRepository answerRepository;
    private final InquiryRepository inquiryRepository;

    @Override
    public Answer save(Long inquiryId, String answerData) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId);
        inquiry.getInquiryStatus().updateStatus(true);
        return answerRepository.save(new Answer(inquiry, answerData));
    }
}
