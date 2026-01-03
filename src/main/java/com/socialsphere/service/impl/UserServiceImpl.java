package com.socialsphere.controller;

import com.socialsphere.entity.User;
import com.socialsphere.entity.UserInfo;
import com.socialsphere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        // Build UserInfo from incoming request
        UserInfo userInfo = UserInfo.builder()
                .fullName(user.getUserInfo() != null ? user.getUserInfo().getFullName() : null)
                .build();

        user.setUserInfo(userInfo);

        User savedUser = userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
