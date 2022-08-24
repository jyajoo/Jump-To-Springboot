package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.SiteUser;
import com.ll.exam.sbb.exception.DataNotFoundException;
import com.ll.exam.sbb.repository.AnswerRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

  private final AnswerRepository answerRepository;

  public void create(Question question, String content, SiteUser siteUser) {
    Answer answer = new Answer();
    answer.setContent(content);
    answer.setCreateDate(LocalDateTime.now());
    answer.setAuthor(siteUser);
    question.addAnswer(answer);
    answerRepository.save(answer);
  }

  public Answer getAnswer(Long id) {
    return answerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("answer not found"));
  }

  public void modify(Answer answer, String content) {
    answer.setContent(content);
    answer.setModifyDate(LocalDateTime.now());
    answerRepository.save(answer);
  }

  public void delete(Answer answer) {
    answerRepository.delete(answer);
  }
}
