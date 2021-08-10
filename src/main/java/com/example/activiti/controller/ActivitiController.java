package com.example.activiti.controller;

import com.example.activiti.entity.User;
import org.activiti.runtime.api.ProcessRuntime;
import org.activiti.runtime.api.TaskRuntime;
import org.activiti.runtime.api.model.ProcessInstance;
import org.activiti.runtime.api.query.Page;
import org.activiti.runtime.api.query.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * activiti7可以自动部署流程 ， 前提是在resources下有processes下面用来放置bpmn文件
 */
@CrossOrigin
@RestController
public class ActivitiController {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @GetMapping(value = "/test1")
    public void test1(){

        //流程定义的分页对象
        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(Pageable.of(0, 10));
        System.out.println("可用的流程总数： " + processInstancePage.getTotalItems());

        for (ProcessInstance processInstance : processInstancePage.getContent()) {

            System.out.println("流程定义内容:{}" + processInstance);
        }

    }
}
