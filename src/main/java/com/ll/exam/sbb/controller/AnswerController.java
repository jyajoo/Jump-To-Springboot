package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.SiteUser;
import com.ll.exam.sbb.dto.AnswerForm;
import com.ll.exam.sbb.exception.DataNotFoundException;
import com.ll.exam.sbb.service.AnswerService;
import com.ll.exam.sbb.service.QuestionService;
import com.ll.exam.sbb.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {

  private final QuestionService questionService;
  private final AnswerService answerService;
  private final UserService userService;

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create/{id}")
  public String detail(Principal principal, @PathVariable long id, @Valid AnswerForm answerForm,
      BindingResult bindingResult, Model model) {
    Question question = questionService.getQuestion(id);

    if (bindingResult.hasErrors()) {
      model.addAttribute("question", question);
      return "question_detail";
    }

    SiteUser siteUser = userService.getUser(principal.getName());

    Answer answer = answerService.create(question, answerForm.getContent(), siteUser);

    return "redirect:/question/detail/%d#answer_%d".formatted(id, answer.getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String answerModify(AnswerForm answerForm, @PathVariable Long id, Principal principal) {
    Answer answer = answerService.getAnswer(id);

    if (!answer.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }
    answerForm.setContent(answer.getContent());
    return "answer_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/modify/{id}")
  public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
      @PathVariable Long id, Principal principal) {
    if (bindingResult.hasErrors()) {
      return "answer_form";
    }
    Answer answer = answerService.getAnswer(id);

    if (!answer.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }
    answerService.modify(answer, answerForm.getContent());
    return "redirect:/question/detail/%d#answer_%d".formatted(answer.getQuestion().getId(), answer.getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String answerDelete(@PathVariable Long id, Principal principal) {
    Answer answer = answerService.getAnswer(id);

    if (!answer.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
    }

    answerService.delete(answer);
    return "redirect:/question/detail/%d".formatted(answer.getQuestion().getId());
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/vote/{id}")
  public String answerVote(Principal principal, @PathVariable Long id) {
    Answer answer = answerService.getAnswer(id);
    SiteUser siteUser = userService.getUser(principal.getName());

    answerService.vote(answer, siteUser);
    return "redirect:/question/detail/%d#answer_%d".formatted(answer.getQuestion().getId(), answer.getId());
  }
}
