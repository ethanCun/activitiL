package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

//测试配置流程 根据BPMN图导入25张表
public class TestDevelopment {

//    public static void main(String[] args) {
//
//        //1. 创建ProcessEngine
//        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//
//        //2. 获取RepositoryService
//        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//
//        //3. 使用service进行流程的部署， 定义一个流程的名字， 把BPMN和png部署到数据中
//        Deployment deploy = repositoryService.createDeployment().name("出差申请")
//                .addClasspathResource("BPMN/evection.bpmn")
//                .addClasspathResource("BPMN/evection.png")
//                .deploy();
//
//        //4. 输出部署信息
//        System.out.println("流程部署id ： " + deploy.getId());
//        System.out.println("流程部署name: " + deploy.getName());
//
//    }

    public static void main(String[] args) {

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .name("demo")
                .addClasspathResource("BPMN/demo.bpmn")
//                .addClasspathResource("BPMN/leave.png")
                .deploy();
        System.out.println(deploy);
    }

}
