package com.socialsphere.service.impl;


import com.socialsphere.entity.User;
import com.socialsphere.repository.UserRepository;
import com.socialsphere.security.JwtUtil;
import com.socialsphere.service.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String login(User requestUser) {

        // 1. Fetch user
        User dbUser = userRepository.findByUsername(requestUser.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate password
        if (!passwordEncoder.matches(requestUser.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3. Generate JWT
        String token = jwtUtil.generateToken(
                dbUser.getUsername(),
                dbUser.getRole().getRoleName()
        );

        // 4. Update last login time
        dbUser.setLastLoginAt(LocalDateTime.now());
        userRepository.save(dbUser);

        return token;
    }
}
