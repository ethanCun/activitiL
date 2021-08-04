package com.example.activiti.test.BusinessKey;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

//演示activiti和自己业务通过BusinessKey关联起来
public class TestBindActivitiAndBusiness {

    public static void main(String[] args) {

        //演示添加Bussinesskey到activiti表, 在启动流程的时候添加businesskey
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        //操作的是runtimeservice
        RuntimeService runtimeService = engine.getRuntimeService();

        //启动流程, 创建的是一个流程实例, BusinessKey是用来和自身业务关联的key, BusinessKey会存入act_ru_execution表中
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo", "1001");

        //输出BusinessKey
        String businessKey = processInstance.getBusinessKey();
        System.out.println(businessKey);

    }
}
