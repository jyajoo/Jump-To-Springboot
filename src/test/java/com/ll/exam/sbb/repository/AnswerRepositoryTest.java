package com.ll.exam.sbb.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.Question;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnswerRepositoryTest {

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  private void createSampleData() {
    QuestionRepositoryTest.createSampleData(questionRepository);
  }

  private void clearData() {
    QuestionRepositoryTest.clearData(questionRepository);
    answerRepository.truncateTable();
  }

  @Test
  void 저장() {
    Question question = questionRepository.findById(2).get();
    Answer answer = new Answer();
    answer.setContent("자동으로 생성됩니다.");
    answer.setQuestion(question);
    answer.setCreateDate(LocalDateTime.now());
    answerRepository.save(answer);
  }
}