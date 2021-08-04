package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

//测试启动流程
public class TestStartProcess {

    public static void main(String[] args) {

        test2();
    }

    public static void test2(){

        //创建流程
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取运行时查询服务
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        //根据流程id启动流程
        ProcessInstance askForLeave = runtimeService.startProcessInstanceByKey("demo");

        //输出流程实例
        System.out.println(askForLeave);
    }

    public static void test1(){

        //创建流程
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取处理服务
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        //根据流程id启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo");

        System.out.println("流程定义id： " + processInstance.getProcessInstanceId());
        System.out.println("流程实例id： " + processInstance.getId());
        System.out.println("当前活动id： " + processInstance.getActivityId());
    }
}
