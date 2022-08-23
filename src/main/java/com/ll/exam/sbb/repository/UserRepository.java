package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.base.RepositoryUtil;
import com.ll.exam.sbb.domain.SiteUser;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<SiteUser, Long>, RepositoryUtil {

  boolean existsByUsername(String username);

  Optional<SiteUser> findByUsername(String username);

  @Transactional
  @Modifying
  @Query(value = "ALTER TABLE site_user AUTO_INCREMENT = 1", nativeQuery = true)
  void truncate();
}
