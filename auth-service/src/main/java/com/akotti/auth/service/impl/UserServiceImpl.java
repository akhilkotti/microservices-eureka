package com.akotti.auth.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akotti.auth.entity.User;
import com.akotti.auth.repository.UserRepository;
import com.akotti.auth.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;

    private final SecretKey key;

    public UserServiceImpl(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    @Override
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public String login(String username, String password) {

    	System.out.println("================================================================================================");
    	System.out.println("username:"+username+"<>password:"+password+"==="+new BCryptPasswordEncoder().encode(password));
    	System.out.println("================================================================================================");
    	User user = repo.findByUsername(username)
    	        .orElseThrow(() -> {
    	        	System.out.println("User not found:"+ username);
    	            return new UsernameNotFoundException("User not found: " + username);
    	        });
    	System.out.println("User Found:"+user.getUsername()+"<>password:"+user.getPassword());
    	
        if (encoder.matches(password, user.getPassword())) {
            return Jwts.builder()
                    .subject(username)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(key, Jwts.SIG.HS256)
                    .compact();
        }
        throw new RuntimeException("Invalid credentials");
    }
}
