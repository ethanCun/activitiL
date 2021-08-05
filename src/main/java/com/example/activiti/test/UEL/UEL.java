package com.example.activiti.test.UEL;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;

/**
 * 演示使用UEL的方式动态分配任务负责人
 */
public class UEL {

    public static void main(String[] args) {


        /**
         *
         * 个人任务分配的三种方式:
         *      1. 固定分配: 画BPMN的时候分配, 实际中不可能写死
         *      2. 表达式分配: 使用UEL表示式 ${assignee} 在BPMN中占位， 然后在代码中写活，里面可以写表达式， 方法等
         *      3. 监听器分配：
         */

        //部署uel方式的bpmn
        //testUel();

        //启动引擎
        testStartUelEngine();
    }

    /**
     *  演示通过uel动态指定任务负责人名称
     */
    public static void testUel(){

        //获取引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取service
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        //部署
        Deployment deployment = repositoryService.createDeployment()
                .name("部署资源")
                .addClasspathResource("taskAssign/demo.bpmn")
                .deploy();

        System.out.println(deployment.getId());
    }

    /**
     * 演示使用UEL动态设置任务负责人
     */
    public static void testStartUelEngine(){

        /**
         * 这种方式启动uel的bpmn会报错： Caused by: javax.el.PropertyNotFoundException: Cannot resolve identifier 'name1'
         *
         * 必须在启动的时候动态设置任务负责人的名称
         */
//        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo", "45001");
//        System.out.println(processInstance.getDeploymentId());


        //将任务负责人放入hashmap中
        //启动成功后， 可以在act_ru_variable表中看到这些数据
        HashMap<String, Object> assignees = new HashMap<>();
        assignees.put("name1", "zhangsan");
        assignees.put("name2", "lisi");
        assignees.put("name3", "wangwu");

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo", assignees);
        System.out.println(processInstance.getDeploymentId());
    }

}
