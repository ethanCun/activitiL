package com.example.activiti.test.gateway;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;

/**
 * 并行网关：
 *      1. 并行网关会走所有的分支， 哪怕是在BPMN线上设置了条件也不会起作用
 *      2. 并行网关包含fork分支和join, 分开和汇聚
 *      3. 必须等到所有的分支都执行完了才会继续执行
 */
public class ParallelGatewayTest {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    private static RuntimeService runtimeService = processEngine.getRuntimeService();
    private static TaskService taskService = processEngine.getTaskService();


    public static void main(String[] args) {

        complete();
    }

    public static void deploy(){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("gateway/parallelgateway.bpmn")
                .name("并行网关")
                .deploy();

        System.out.println("部署id： " + deployment.getId());
    }

    /**
     * 每启动一次就会新增一条任务数据
     */
    public static void start(){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("inclusivegateway", "");

        System.out.println("流程实例id:" + processInstance.getProcessInstanceId());
        System.out.println("流程定义key:" + processInstance.getProcessDefinitionKey());
    }

    /**
     * 测试并行网关必须等待所有的分支都执行完了才会执行接下来的流程, 所有分支的任务都会加入act_ru_task表中
     */
    public static void complete(){

        Task task = taskService.createTaskQuery()
                .taskAssignee("xiaoming")
                .processDefinitionKey("inclusivegateway")
                .singleResult();

        taskService.complete(task.getId());

        System.out.println("任务完成");
    }

    public static void delete(){

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey("inclusivegateway").singleResult();
        repositoryService.deleteDeployment(deployment.getId(), true);
    }
}
