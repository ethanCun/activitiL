package com.example.activiti.test.variable;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import javax.swing.*;
import java.util.HashMap;

/**
 * 这个测试类演示完成任务的时候设置流程变量
 */
public class SetVariableWhenCompleteTask {

    private static  ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {

        complete();
    }

    public static void createAndDeploy(){

        //部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("variable/variable.bpmn")
                .name("完成任务的时候设置环境变量")
                .key("setVariableWhenDoingTask")
                .deploy();
        System.out.println("部署id ： " + deployment.getId());
        System.out.println("部署名称： " + deployment.getName());

        //启动
        HashMap<String, Object> map = new HashMap<>();
        map.put("name1", "zs");
        map.put("name2", "ls");
        map.put("name3", "ww");
        map.put("name4", "zl");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("varidemo", map);
        System.out.println("流程实例key： " + processInstance.getProcessDefinitionKey());
        System.out.println("流程实例部署id：" + processInstance.getDeploymentId());
        System.out.println("流程实例id：" + processInstance.getId());
    }

    public static void delete(){

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeploymentQuery()
                .deploymentName("完成任务的时候设置环境变量")
                .singleResult();
        repositoryService.deleteDeployment(deployment.getId(), true);
    }

    public static void complete(){

        /**
         * 在完成任务的时候设置环境变量， 比如根据请假天数选择对应的审批人进行审批
         */
        User user = new User("xiaoming", "30", 2);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskAssignee("zl")
                .processDefinitionKey("varidemo")
                .singleResult();

        //完成任务的时候传入user对象
        taskService.complete(task.getId(), map);

    }
}
