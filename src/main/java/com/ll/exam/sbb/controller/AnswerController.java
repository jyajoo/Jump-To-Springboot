package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.service.AnswerService;
import com.ll.exam.sbb.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {

  private final QuestionService questionService;
  private final AnswerService answerService;

  @PostMapping("/create/{id}")
  public String detail(@PathVariable int id, String content, Model model) {
    Question question = questionService.getQuestion(id);
    answerService.create(question, content);

    return "redirect:/question/detail/%d".formatted(id);
  }
}
