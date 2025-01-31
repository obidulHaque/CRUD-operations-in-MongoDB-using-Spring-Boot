package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class test {
    @GetMapping("test")
    public String sayHello(){
        return "hello java i am joy";
    }
    @GetMapping("delete")
    public String deteleting(){
        return "i want to delete";
    }
}
