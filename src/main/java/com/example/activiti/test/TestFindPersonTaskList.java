package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

//测试查看流程负责人下面的任务
public class TestFindPersonTaskList {

    public static void main(String[] args) {

        //查询用户可以处理的任务
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取任务service
        TaskService taskService = defaultProcessEngine.getTaskService();

        //根据流程key和负责人查询
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("demo")
                .taskAssignee("zs")
//                .singleResult()   //获取指定流程 指定负责人的单个任务
                .list();  //获取指定流程 指定负责人的所有任务列表

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("------------");
            System.out.println(task.getAssignee());
            System.out.println(task.getName());
            System.out.println(task.getId()); //任务id
            System.out.println(task.getProcessInstanceId());
        }
    }
}
