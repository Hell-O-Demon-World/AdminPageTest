package com.golfzonaca.adminpage.repository.answer;

import com.golfzonaca.adminpage.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomAnswerRepository implements AnswerRepository {
    private final SpringJpaAnswerRepository jpaRepository;

    @Override
    public Answer save(Answer answer) {
        return jpaRepository.save(answer);
    }
}
