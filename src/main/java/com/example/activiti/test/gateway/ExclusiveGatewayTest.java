package com.example.activiti.test.gateway;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;

/**
 * 排他网关测试类
 */
public class ExclusiveGatewayTest {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    private static RuntimeService runtimeService = processEngine.getRuntimeService();
    private static TaskService taskService = processEngine.getTaskService();


    public static void main(String[] args) {

        complete();
    }

    public static void deploy(){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("gateway/exclusivegateway.bpmn")
                .name("排他网关")
                .deploy();

        System.out.println("部署id： " + deployment.getId());
    }

    public static void start(){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1", "");

        System.out.println("流程实例id:" + processInstance.getProcessInstanceId());
        System.out.println("流程定义key:" + processInstance.getProcessDefinitionKey());
    }

    /**
     *
     * 不满足排他网关的任何一条路线式，会报错：
     * No outgoing sequence flow of the exclusive gateway '_6' could be selected for continuing the process
     *
     *  同时满足多条线路， 只会选择id较小的线路执行, 这里的id是BPMN图中任务的id
     *
     */
    public static void complete(){

        //测试完成都不满足排他网关的情况
        Task task = taskService.createTaskQuery()
                .taskAssignee("zhuguan")
                .processDefinitionKey("myProcess_1")
                .singleResult();

        //添加流程变量user, 需要实现序列化接口
        //-1天不满足排他网关任何一条路线
        User zzz = new User("zzz", "88", 4);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", zzz);
        taskService.complete(task.getId(), map);

        System.out.println("任务完成");
    }

    public static void delete(){

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey("myProcess_1").singleResult();
        repositoryService.deleteDeployment(deployment.getId(), true);
    }
}
