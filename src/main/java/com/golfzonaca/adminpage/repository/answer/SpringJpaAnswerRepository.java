package com.golfzonaca.adminpage.repository.answer;

import com.golfzonaca.adminpage.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringJpaAnswerRepository extends JpaRepository<Answer, Long> {
}
