package com.akotti.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akotti.auth.entity.User;
import com.akotti.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> req) {
        return ResponseEntity.ok(service.login(req.get("username"), req.get("password")));
    }
}
