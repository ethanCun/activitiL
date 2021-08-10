package com.example.activiti;

import com.example.activiti.utils.SecurityUtil;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.runtime.api.ProcessRuntime;
import org.activiti.runtime.api.model.ProcessInstance;
import org.activiti.runtime.api.query.Page;
import org.activiti.runtime.api.query.Pageable;
import org.apache.tomcat.jni.Proc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootactivitiTest {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void test1(){

        //使用账户登录
        securityUtil.loginInAs("lili");

        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(Pageable.of(1, 10));

        System.out.println(processInstancePage.getTotalItems());
    }

    @Test
    public void test2(){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/demo.bpmn")
                .name("springboot整合activiti")
                .deploy();

        System.out.println(deployment.getId());
    }
}
