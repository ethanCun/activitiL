package com.example.activiti.test.ProcessDefinition;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

//查询流程定义
public class TestQueryProcessDefinition {

    public static void main(String[] args) {

        //获取引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取RepositoryService
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        //获取ProcessDefinitionQuery
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //查询流程定义 根据act_re_procdef表中的key2进行查询所有的流程定义 并且按照版本号降序
        List<ProcessDefinition> demo1 = processDefinitionQuery
                .processDefinitionKey("demo")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (int i = 0; i < demo1.size(); i++) {
            ProcessDefinition processDefinition = demo1.get(i);
            //流程定义id
            System.out.println("id: " + processDefinition.getId());
            //流程定义key
            System.out.println("key: " + processDefinition.getKey());
            //流程定义资源名称bpmn文件
            System.out.println("resource name : " + processDefinition.getResourceName());
        }

    }
}
