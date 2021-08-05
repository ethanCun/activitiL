package com.example.activiti.controller;

import com.example.activiti.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class ActivitiController {

    @PostMapping(value = "/ttt")
    public void test(@RequestBody User user, HttpServletRequest request){

//        request.getServletContext().getRealPath()
//        ActivitiController.class.getClassLoader().
        System.out.println(user);
    }

}
