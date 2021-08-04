package com.example.activiti.test.suspendProcessInstance;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

//演示单个流程实例挂起
public class SingalProcessInstanceSuspend {

    public static void main(String[] args) {

        //获取引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        //获取serivice: 获取流程实例需要使用运行时服务
        RuntimeService runtimeService = engine.getRuntimeService();

        //获取流程实例
        ProcessInstance demo = runtimeService.createProcessInstanceQuery()
                .processInstanceId("27501")
                .singleResult();

        //1: 激活 2： 挂起,   操作的表：ACT_RU_EXECUTION ACT_RU_TASK
        boolean suspended = demo.isSuspended();

        System.out.println(suspended);

        if (suspended){
            runtimeService.activateProcessInstanceById(demo.getId());
            System.out.println("流程已激活");
        }else {
            runtimeService.suspendProcessInstanceById(demo.getId());
            System.out.println("流程已挂起");
        }

    }
}
