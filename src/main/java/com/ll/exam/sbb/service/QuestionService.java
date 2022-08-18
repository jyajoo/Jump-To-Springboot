package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.Question;
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

    public Question getQuestion(int id) throws DataNotFoundException{
      return questionRepository.findById(id)
          .orElseThrow(() -> new DataNotFoundException("no %d question not found.".formatted(id)));
    }

  public void create(String subject, String content) {
    Question question = new Question();
    question.setSubject(subject);
    question.setContent(content);
    question.setCreateDate(LocalDateTime.now());
    questionRepository.save(question);
  }
}
