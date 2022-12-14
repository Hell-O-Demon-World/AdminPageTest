package com.golfzonaca.adminpage.service.answer;

import com.golfzonaca.adminpage.domain.Answer;

public interface AnswerService {

    Answer save(Long inquiryId, String answer);
}
