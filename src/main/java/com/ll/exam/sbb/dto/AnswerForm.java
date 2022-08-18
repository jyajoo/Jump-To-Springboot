package com.ll.exam.sbb.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnswerForm {

  @NotEmpty(message = "내용은 필수항목입니다.")
  private String content;
}
