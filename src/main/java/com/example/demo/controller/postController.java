package com.example.demo.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.postDetails;
import com.example.demo.service.postService;

import java.util.*;

@RestController
@RequestMapping("/post")
public class postController {


    @Autowired
    private postService postService;
//    @GetMapping
//    List<postDetails> getAllUser() {
//        return postService.getAll();
//    }
    @GetMapping
    List<postDetails> getPostByName(){
        return postService.getPostByUserName();
    }
    @GetMapping("id/{myId}")
    postDetails getSinglePost(@PathVariable ObjectId myId){
        return postService.getPostById(myId).orElseThrow(()->new RuntimeException("user Not Found" + HttpStatus.NOT_FOUND));
    }
    @PostMapping()
     public String createPost(@RequestBody postDetails postInfo){
        return postService.addPost(postInfo);

    }
    @PutMapping("/id/{Id}")
    public ResponseEntity<?> updateSingleUser(@PathVariable("Id") ObjectId Id , @RequestBody postDetails userDetail) {
           return postService.updatePost(Id,userDetail);
    }
    @DeleteMapping("/id/{userName}/{Id}")
    public String userDelete(@PathVariable("Id") ObjectId Id,@PathVariable("userName") String userName){
       postService.postDelete(Id,userName);
       return "user Delete successfully done!";
    }
}
