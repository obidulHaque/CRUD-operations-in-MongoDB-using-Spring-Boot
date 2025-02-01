package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.userDetails;
import com.example.demo.service.AuthService;
import com.example.demo.repository.userRepository;
import com.example.demo.config.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("register")
    public ResponseEntity<userDetails> createUser(@RequestBody userDetails user){
         return ResponseEntity.ok(authService.createUser(user));
    }
    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody userDetails LogInDetails){
        userDetails user = UserRepository.findByUserName(LogInDetails.getUserName());
        if (user ==null){
            return ResponseEntity.status(405).body("plz give username and password");
        }
        if (passwordEncoder.matches(LogInDetails.getPassword(),user.getPassword())){
            String Token = jwtUtil.generateToken(user.getUserName());
            return ResponseEntity.ok(Map.of("token",Token));
        }else {
            return ResponseEntity.status(401).body("Invalid username and password");
        }

    }
}
