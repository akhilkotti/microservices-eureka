package com.akotti.auth.service;

import com.akotti.auth.entity.User;

public interface UserService {
    User register(User user);
    String login(String username, String password);
}