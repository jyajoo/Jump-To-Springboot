package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.base.RepositoryUtil;
import com.ll.exam.sbb.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, RepositoryUtil {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String s);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate();

    // 제목에 포함
    Page<Question> findBySubjectContains(String kw, PageRequest pageable);

    // 제목, 내용 포함
    Page<Question> findBySubjectContainsOrContentContains(String kw, String kw1, PageRequest pageable);

    // 제목, 내용, 질문 작성자 포함
    Page<Question> findBySubjectContainsOrContentContainsOrAuthor_usernameContains(String kw, String kw1, String kw2,
      PageRequest pageable);

    // 제목, 내용, 질문 작성자, 답변 내용 포함
    Page<Question> findBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContains(
        String kw, String kw1, String kw2, String kw3, PageRequest pageable);

    // 제목, 내용, 질문 작성자, 답변 내용 포함 + 중복 제거
    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContains(
        String kw, String kw1, String kw2, String kw3, PageRequest pageable);

    // 제목, 내용, 질문 작성자, 답변 내용, 답변 작성자 포함 + 중복 제거
    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContainsOrAnswerList_author_usernameContains(
        String kw, String kw1, String kw2, String kw3, String kw4, PageRequest pageable);

    // 제목, 내용, 답변 내용 포함 + 질문 작성자, 답변작성자(일치) + 중복제거
    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameOrAnswerList_contentContainsOrAnswerList_author_username(
        String kw, String kw1, String kw2, String kw3, String kw4, PageRequest pageable);
}

