package com.ll.exam.sbb.exception;

public class SignupUsernameDuplicatedException extends RuntimeException{

  public SignupUsernameDuplicatedException(String message) {
    super(message);
  }
}
