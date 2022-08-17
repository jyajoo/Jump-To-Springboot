package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.repository.AnswerRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;

  public void create(Question question, String content) {
    Answer answer = new Answer();
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    question.addAnswer(answer);
    answerRepository.save(answer);
  }
}