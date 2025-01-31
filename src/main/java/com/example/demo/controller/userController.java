package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.userDetails;

import java.util.List;

@RestController
@RequestMapping("user")
public class userController {
    @Autowired
    private userService UserService;
    @GetMapping
    public List<userDetails> getAllUser(){
        return UserService.getAllUser();
    }
    @PostMapping
    public userDetails createUser(@RequestBody userDetails UserDetails){
        return UserService.createUser(UserDetails);
    }
    @PutMapping("{userName}")
    public userDetails updateUser(@PathVariable String userName,@RequestBody userDetails updateUserInfo){
        return UserService.updateUser(userName,updateUserInfo);
    }
}
