package com.example.activiti.test.Listener;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 演示使用监听器的方式动态设置任务负责人
 */
public class Listener {

    public static void main(String[] args) {

        /**
         * 使用监听器的方式不用在BPMN创建的时候制动UEL表达式
         *  需要在BPMN中指定listener的监听类
         */
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
//        //先删除
//        repositoryService.deleteDeployment("52501");
//        Deployment deployment = repositoryService.createDeployment().addClasspathResource("taskAssign/listener.bpmn")
//                .name("监听器方式动态分配任务").deploy();
//        System.out.println("id： " + deployment.getId());
//        System.out.println("key: " + deployment.getKey());
//
//        //启动
//        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("listenerdemo");
//        System.out.println(processInstance.getDeploymentId());

        //完成任务
        TaskService taskService = defaultProcessEngine.getTaskService();
        Task zhangsan = taskService.createTaskQuery().taskAssignee("lisi").taskId("55004").singleResult();
        taskService.complete(zhangsan.getId());
    }
}
