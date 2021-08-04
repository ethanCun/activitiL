package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;

//测试完成任务 让流程继续执行  完成了的任务会进入act_hi历史表中
public class TestFinishTask {

    public static void main(String[] args) {

        //获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取完成任务的service
        TaskService taskService = defaultProcessEngine.getTaskService();

        //根据任务id完成任务
        taskService.complete("20005");
    }
}
