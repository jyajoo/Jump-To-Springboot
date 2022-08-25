package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.SiteUser;
import com.ll.exam.sbb.exception.DataNotFoundException;
import com.ll.exam.sbb.repository.QuestionRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
      List<Sort.Order> sorts = new ArrayList<>();
      sorts.add(Sort.Order.desc("createDate"));
      PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));
      return questionRepository.findAll(pageable);
    }

    public Question getQuestion(long id) throws DataNotFoundException{
      return questionRepository.findById(id)
          .orElseThrow(() -> new DataNotFoundException("no %d question not found.".formatted(id)));
    }

  public Question create(String subject, String content, SiteUser siteUser) {
    Question question = new Question();
    question.setSubject(subject);
    question.setContent(content);
    question.setAuthor(siteUser);
    question.setCreateDate(LocalDateTime.now());
    return questionRepository.save(question);
  }

  public void modify(Question question, String subject, String content) {
    question.setSubject(subject);
    question.setContent(content);
    question.setModifyDate(LocalDateTime.now());
    questionRepository.save(question);
  }

  public void delete(Question question) {
    questionRepository.delete(question);
  }

  public void vote(Question question, SiteUser siteUser) {
    question.getVoter().add(siteUser);
    questionRepository.save(question);
  }
}
