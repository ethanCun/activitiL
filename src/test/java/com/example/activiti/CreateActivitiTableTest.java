package com.example.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateActivitiTableTest {

    @Test
    public void test01(){

        //注意需要引入mybatis的包，activiti内部需要这个包
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);

//        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//        System.out.println(processEngineConfigurationFromResource);
    }

}
