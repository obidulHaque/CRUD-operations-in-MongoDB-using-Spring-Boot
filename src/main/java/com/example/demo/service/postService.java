package com.example.demo.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.postRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.postDetails;
import com.example.demo.repository.userRepository;
import com.example.demo.entity.userDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class postService {
    @Autowired
    private postRepository PostRepository;
    @Autowired
    private userRepository UserRepository;
    public List<postDetails> getAll(){
       return PostRepository.findAll();
    }
    public String addPost(postDetails postDetails){
        userDetails user= (userDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userDetails user = UserRepository.findByUserName(userName);
        postDetails.setTime(LocalDate.now());
        postDetails saved = PostRepository.save(postDetails);
        user.getPostDetails().add(saved);
        UserRepository.save(user);
        return "post was upload successfully done ";
    }
    public List<postDetails> getPostByUserName(){
        userDetails user= (userDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         List<postDetails> getUserPost = user.getPostDetails();
        return getUserPost;
    }
    public Optional<postDetails> getPostById(ObjectId id){
       return PostRepository.findById(id);
    }
    public ResponseEntity<?> updatePost(ObjectId Id,postDetails PostDetails){
        userDetails user= (userDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postDetails old = PostRepository.findById(Id).orElseThrow(()-> new RuntimeException("Post not found for update"));
        if (old != null) {
            old.setTitle(PostDetails.getTitle() != null && PostDetails.getTitle().equals("") ? old.getTitle() : PostDetails.getTitle());
            old.setDescription(PostDetails.getDescription() != null && PostDetails.getDescription().equals("") ? old.getDescription() : PostDetails.getDescription());
            postDetails saved= PostRepository.save(old);
            user.getPostDetails().add(saved);
            return ResponseEntity.status(200).body("Post update successfully done");
        }else {
            return ResponseEntity.status(401).body(("Post update Fail"));
        }

    }
    public void postDelete(ObjectId id,String userName){
        userDetails user = UserRepository.findByUserName(userName);
        user.getPostDetails().removeIf(x -> x.getId() == id);
        UserRepository.save(user);
        PostRepository.deleteById(id);
    }
}


