package com.example.activiti.test.ProcessDefinition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

//测试删除流程定义
public class TestDeleteProcessDefinition {

    public static void main(String[] args) {

        //获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取资源服务
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        //找到部署id deploymentId
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("demo")
                .orderByProcessDefinitionVersion()
                .desc()
                .singleResult();

        String deploymentId = processDefinition.getDeploymentId();

        //删除流程部署信息
        /**
         * 注意： 这种方式不能删除还未完成的流程， 会报错
         */
        //repositoryService.deleteDeployment(deploymentId);

        /**
         * 级联删除 还未完成的流程
         */
        repositoryService.deleteDeployment(deploymentId, true);

    }
}
