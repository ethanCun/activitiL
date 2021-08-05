package com.example.activiti.test.variable;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;

/**
 * 通过流程实例设置流程变量
 */
public class SetVariableWithProcessInstance {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    private static RuntimeService runtimeService = processEngine.getRuntimeService();
    private static TaskService taskService = processEngine.getTaskService();

    public static void main(String[] args) {

        complete();
    }

    public static void deploy() {

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("variable/variable.bpmn")
                .name("通过流程实例设置流程变量")
                .deploy();
        System.out.println("部署id： " + deployment.getId());
    }

    public static void start(){

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

        /**
         * 通过当前流程实例设置流程变量
         */
        //pojo
        User zzzzz = new User("zzzzz", "100", 4);
        //第一个参数： 当前执行流程的id, 这个是global变量
        runtimeService.setVariable(processInstance.getProcessInstanceId(), "user", zzzzz);
        //一次设置多个, 一个map里面放置多个流程变量
//        runtimeService.setVariables();

        //设置local变量
//        runtimeService.setVariableLocal();
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
        Task task = taskService.createTaskQuery()
                .taskAssignee("zl")
                .processDefinitionKey("varidemo")
                .singleResult();

        //完成任务的时候传入user对象
        taskService.complete(task.getId());

    }
}
