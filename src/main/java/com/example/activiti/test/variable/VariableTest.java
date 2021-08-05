package com.example.activiti.test.variable;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.tomcat.jni.Proc;

import java.util.HashMap;

/**
 * 演示BPMN流程变量的使用
 *
 * 这个测试类演示启动流程实例的时候设置流程变量， 传一个map过去就可以了
 */
public class VariableTest {

    public static void main(String[] args) {

        /**
         * 设置流程变量的几种方式：
         *      1. 启动流程的时候设置：
         *      2. 办理任务的时候设置：
         *      3. 通过当前流程实例设置：
         *      4. 通过当前任务设置：
         *
         */
        delete();

    }

    /**
     * 这个方式演示启动的时候设置流程变量
     */
    public static void deployAndStart(){

        //部署
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("variable/variable.bpmn")
                .name("流程变量的作用")
                .deploy();
        System.out.println("部署id: " + deployment.getId());
        System.out.println("部署名称: " + deployment.getName());


        //启动的时候设置流程变量
        //如果同时使用UEL和监听器设置了流程变量， 则会以监听器的为准
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        HashMap<String, Object> map = new HashMap<>();

        //设置任务责任人
        map.put("name1", "zzzsss");
        map.put("name2", "lllsss");
        map.put("name3", "wwwwww");
        map.put("name4", "ggggg");

        //设置pojo流程变量  这些运行时的流程变量都可以在act_ru_variable表中查看
        User user = new User();
        user.setName("czy");
        user.setAge("29");
        user.setDays(20);

        map.put("user", user);

        ProcessInstance varidemo = runtimeService.startProcessInstanceByKey("varidemo", map);
        System.out.println("部署id： " + varidemo.getDeploymentId());
    }

    public static void delete(){

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment varidemo = repositoryService.createDeploymentQuery().deploymentName("流程变量的作用").singleResult();
        defaultProcessEngine.getRepositoryService().deleteDeployment(varidemo.getId(), true);
    }

    public static void complete(){

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("varidemo")
                .taskAssignee("ggggg")
                .singleResult();
        taskService.complete(task.getId());
    }
}
