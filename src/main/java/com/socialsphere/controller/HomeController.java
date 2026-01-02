package com.socialsphere.controller;


import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> home(Principal principal) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to SocialSphere");
        response.put("username", principal.getName());

        return ResponseEntity.ok(response);
    }
}
