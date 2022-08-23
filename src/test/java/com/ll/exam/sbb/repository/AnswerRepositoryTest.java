package com.ll.exam.sbb.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.SiteUser;
import com.ll.exam.sbb.service.UserService;
import com.ll.exam.sbb.service.UserServiceTest;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class AnswerRepositoryTest {

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @BeforeEach
  void beforeEach() {
    clearData();
    createSampleData();
  }

  private void createSampleData() {
    QuestionRepositoryTest.createSampleData(userService, questionRepository);

    // 관련 답변이 하나없는 상태에서 쿼리 발생
    Question q = questionRepository.findById(1L).get();
    Answer a1 = new Answer();
    a1.setContent("sbb는 질문답변 게시판 입니다.");
    a1.setQuestion(q);
    a1.setCreateDate(LocalDateTime.now());
    a1.setAuthor(new SiteUser(1L));
    q.addAnswer(a1);

    Answer a2 = new Answer();
    a2.setContent("sbb에서는 주로 스프링부트관련 내용을 다룹니다.");
    a2.setQuestion(q);
    a2.setCreateDate(LocalDateTime.now());
    a2.setAuthor(new SiteUser(2L));
    q.addAnswer(a2);
    questionRepository.save(q);
  }

  public static void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
    UserServiceTest.clearData(userRepository, answerRepository, questionRepository);
  }

  private void clearData(){
    clearData(userRepository, answerRepository, questionRepository);
  }

  @Test
  @Transactional
  @Rollback(false)
  void 저장() {
    Question q = questionRepository.findById(2L).get();
    Answer a1 = new Answer();
    a1.setContent("네 자동으로 생성됩니다.");
    a1.setCreateDate(LocalDateTime.now());
    a1.setAuthor(new SiteUser(1L));
    q.addAnswer(a1);

    Answer a2 = new Answer();
    a2.setContent("네네~ 맞아요!");
    a2.setCreateDate(LocalDateTime.now());
    a2.setAuthor(new SiteUser(2L));
    q.addAnswer(a2);

    questionRepository.save(q);
  }

  @Test
  @Transactional
  @Rollback(false)
  void 조회() {
    Answer a = answerRepository.findById(1L).get();
    assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
  }

  @Test
  @Transactional
  @Rollback(false)
  void 관련된_question_조회() {
    Answer a = answerRepository.findById(1L).get();
    Question q = a.getQuestion();
    assertThat(q.getId()).isEqualTo(1);
  }

  @Test
  @Transactional
  @Rollback(false)
  void question_관련된_답변_조회() {
    Question q = questionRepository.findById(1L).get();

    List<Answer> answerList = q.getAnswerList();
    assertThat(answerList.size()).isEqualTo(2);
    assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
  }
}