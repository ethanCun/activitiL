package com.example.activiti.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.runtime.api.ProcessRuntime;
import org.activiti.runtime.api.TaskRuntime;
import org.activiti.runtime.api.model.ProcessDefinition;
import org.activiti.runtime.api.query.Page;
import org.activiti.runtime.api.query.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public void test1() {

        System.out.println("test1");

        //流程定义的分页对象
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("可用的流程总数： " + processDefinitionPage.getTotalItems());

        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {

            System.out.println("流程定义内容:{}" + processDefinition);
        }

    }

    @GetMapping(value = "/test2")
    public String test2() {

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .name("test1")
                .addClasspathResource("gateway/parallelgateway.bpmn")
                .deploy();

        System.out.println(deployment.getName());
        System.out.println(deployment.getKey());

        return "test2";
    }

    @GetMapping(value = "/test3")
    public String test3() {

        return "test3";
    }
}