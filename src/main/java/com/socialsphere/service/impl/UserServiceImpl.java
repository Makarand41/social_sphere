package com.socialsphere.service.impl;

import com.socialsphere.entity.Role;
import com.socialsphere.entity.User;
import com.socialsphere.repository.RoleRepository;
import com.socialsphere.repository.UserRepository;
import com.socialsphere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        // 1. Check email uniqueness
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Check username uniqueness
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // 3. Fetch USER role
        Role userRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // 4. Set system-controlled fields
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userRole);
        user.setAccountStatus("ACTIVE");
        user.setPrivate(false);

        // 5. Save user
        return userRepository.save(user);
    }
}
