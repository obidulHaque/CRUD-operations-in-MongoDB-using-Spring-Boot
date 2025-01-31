package com.example.demo.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.postDetails;
import com.example.demo.service.postService;

import java.util.*;

@RestController
@RequestMapping("api")
public class postController {


    @Autowired
    private postService postService;
    @GetMapping
    List<postDetails> getAllUser() {
        return postService.getAll();
    }
    @GetMapping("{userName}")
    List<postDetails> getPostByName(@PathVariable String userName){
        return postService.getPostByUserName(userName);
    }
    @GetMapping("id/{myId}")
    postDetails getSinglePost(@PathVariable ObjectId myId){
        return postService.getPostById(myId).orElseThrow(()->new RuntimeException("user Not Found" + HttpStatus.NOT_FOUND));
    }
    @PostMapping("id/{userName}")
     String addSinglePost(@RequestBody postDetails userIfo,@PathVariable String userName){
        return postService.addPost(userIfo,userName);

    }
    @PutMapping("/id/{userName}/{Id}")
    public postDetails updateSingleUser(@PathVariable("Id") ObjectId Id ,@PathVariable("userName") String userName, @RequestBody postDetails userDetail) {
        postDetails old = postService.getPostById(Id).orElseThrow(()-> new RuntimeException("User not found for update"));
        if (old != null) {
            old.setTitle(userDetail.getTitle() != null && userDetail.getTitle().equals("") ? old.getTitle() : userDetail.getTitle());
            old.setDescription(userDetail.getDescription() != null && userDetail.getDescription().equals("") ? old.getDescription() : userDetail.getDescription());
            postService.addPost(old,userName);
        }

        return old;
    }
    @DeleteMapping("/id/{userName}/{Id}")
    public String userDelete(@PathVariable("Id") ObjectId Id,@PathVariable("userName") String userName){
       postService.postDelete(Id,userName);
       return "user Delete successfully done!";
    }
}
