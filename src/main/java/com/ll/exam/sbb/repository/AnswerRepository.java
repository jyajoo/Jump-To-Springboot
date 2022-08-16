package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.base.RepositoryUtil;
import com.ll.exam.sbb.domain.Answer;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer>, RepositoryUtil {

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE answer AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}
