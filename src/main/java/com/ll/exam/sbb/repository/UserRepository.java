package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.domain.SiteUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

  boolean existsByUsername(String username);

  Optional<SiteUser> findByUsername(String username);
}
