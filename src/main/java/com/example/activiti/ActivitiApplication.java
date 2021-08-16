package com.example.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * springboot和activiti7整合：
 *
 *      activiti7可以自动部署流程， 前提是需要在resources下面创建一个processes目录，用来放置bpmn文件
 */
@SpringBootApplication //集成acticiti不使用springsecurity
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
