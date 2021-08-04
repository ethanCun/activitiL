package com.example.activiti.test.suspendProcessInstance;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

//演示挂起流程： 比如月末的最后一天不处理任何人的出差申请
public class TestSuspendProcessInstance {

    public static void main(String[] args) {

        //获取流程引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        //获取serivce
        RepositoryService repositoryService = engine.getRepositoryService();

        //查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("demo")
                .singleResult();

        //判断是否是挂起状态 1:激活状态 2：挂起状态  操作的表有：ACT_RU_TASK ACT_RU_EXECUTION  ACT_RE_PROCDEF
        boolean suspended = processDefinition.isSuspended();

        //批量挂起流程实例
        if (suspended) {

            //挂起状态改为激活
            //参数2: 是否激活 参数3： 激活的时间
            repositoryService.activateProcessDefinitionById(processDefinition.getId(), true, null);

            System.out.println("已激活");
        }else {

            //激活状态改为挂起
            //参数2: 是否挂起 参数3： 挂起的时间
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);

            System.out.println("已挂起");
        }

    }
}
