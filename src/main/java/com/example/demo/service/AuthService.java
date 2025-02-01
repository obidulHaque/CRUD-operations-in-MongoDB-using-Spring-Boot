package com.example.demo.service;

import com.example.demo.entity.userDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.userRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public userDetails createUser(userDetails UserDetails){
        UserDetails.setPassword(passwordEncoder.encode(UserDetails.getPassword()));
        if (UserDetails.getRoles()==null || UserDetails.getRoles().isEmpty()){
            UserDetails.setRoles(List.of("User"));
        }
        return UserRepository.save(UserDetails);
    }
}
