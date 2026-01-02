package com.socialsphere.controller;


import com.socialsphere.entity.User;



import com.socialsphere.entity.User;
import com.socialsphere.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = loginService.login(user);
        return ResponseEntity.ok(token);
    }
}
