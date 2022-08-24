package com.ll.exam.sbb.service;

import com.ll.exam.sbb.domain.SiteUser;
import com.ll.exam.sbb.exception.DataNotFoundException;
import com.ll.exam.sbb.exception.SignupEmailDuplicatedException;
import com.ll.exam.sbb.exception.SignupUsernameDuplicatedException;
import com.ll.exam.sbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) throws SignupEmailDuplicatedException, SignupUsernameDuplicatedException {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (userRepository.existsByUsername(username)) {
                throw new SignupUsernameDuplicatedException("이미 사용 중인 username입니다.");
            } else {
                throw new SignupEmailDuplicatedException("이미 사용 중인 email입니다.");
            }
        }
        return user;
    }

    public SiteUser getUser(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new DataNotFoundException("siteuser not found"));
    }
}
