package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository questionRepository;

  public List<Question> getList() {
    return questionRepository.findAll();
  }
}
