package com.ll.exam.sbb.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ll.exam.sbb.repository.AnswerRepository;
import com.ll.exam.sbb.repository.AnswerRepositoryTest;
import com.ll.exam.sbb.repository.QuestionRepository;
import com.ll.exam.sbb.repository.QuestionRepositoryTest;
import com.ll.exam.sbb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampeleData();
    }

    public static void createSampeleData(UserService userService) {
        userService.create("admin", "admin@test.com", "1234");
        userService.create("user1", "user1@test.com", "1234");
    }

    private void createSampeleData() {
        createSampeleData(userService);
    }

    /*
    답변, 질문, 회원 순으로 삭제
     */
    public static void clearData(UserRepository userRepository, AnswerRepository answerRepository,
        QuestionRepository questionRepository) {
        answerRepository.deleteAll();
        answerRepository.truncateTable();

        questionRepository.deleteAll();
        questionRepository.truncateTable();

        userRepository.deleteAll();
        userRepository.truncateTable();
    }

    private void clearData() {
        clearData(userRepository, answerRepository, questionRepository);
    }

    @Test
    @DisplayName("회원 가입")
    public void t1() {
        userService.create("user2", "user2@email.com", "1234");
    }
}