package com.example.activiti.controller;

import com.example.activiti.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ActivitiController {

    @PostMapping(value = "/ttt")
    public void test(@RequestBody User user){

        System.out.println(user);
    }

}
