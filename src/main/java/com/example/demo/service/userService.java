package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.userRepository;
import com.example.demo.entity.userDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    private userRepository UserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<userDetails> getAllUser(){
        return  UserRepository.findAll();
    }
    public userDetails createUser( userDetails UserDetails){
        UserDetails.setPassword(passwordEncoder.encode(UserDetails.getPassword()));
        return UserRepository.save(UserDetails);
    }
    public userDetails updateUser(String userName, userDetails updateUserInfo){
        userDetails UserInfo= UserRepository.findByUserName(userName);
        if (UserInfo != null){
            UserInfo.setUserName(updateUserInfo.getUserName());
            UserInfo.setPassword(updateUserInfo.getPassword());
            UserRepository.save(UserInfo);
        }

        return UserInfo;
    }
//    public Boolean deleteUser(String username){
//        UserRepository.delete
//    }
}
