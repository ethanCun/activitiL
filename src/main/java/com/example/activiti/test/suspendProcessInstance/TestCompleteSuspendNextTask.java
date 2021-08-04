package com.example.activiti.test.suspendProcessInstance;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

//演示是否能完成挂起的流程
public class TestCompleteSuspendNextTask {

    public static void main(String[] args) {

        //获取引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        //获取service
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        //获取流程实例, 该流程实例被挂起
        Task task = taskService.createTaskQuery()
                .processInstanceId("27501")
                .taskAssignee("zs")
                .singleResult();


        //完成
        taskService.complete(task.getId());
    }
}
