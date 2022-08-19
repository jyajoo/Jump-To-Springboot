package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.config.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

  boolean existsByUsername(String username);
}
