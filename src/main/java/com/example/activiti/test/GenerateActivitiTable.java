package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

//生成activiti的表
public class GenerateActivitiTable {


    public static void main(String[] args) {

        //注意需要引入mybatis包，因为activiti内部使用mybatis来进行数据库操作，不然报错：Caused by: java.lang.NoClassDefFoundError: org/apache/ibatis/transaction/TransactionFactory
        //需要使用activiti提供的工具类
        //默认从resources下读取名字为activiti.cfg.xml的文件
        /**
         *
         * 表的作用：
         * ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
         * ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
         * ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
         * ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
         * ACT_GE_*: 'GE'表示general。通用数据， 用于不同场景下，如存放资源文件。
         */
        //标准引擎
//        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        System.out.println(defaultProcessEngine);

        //ProcessEngine下面有很多服务， 比如拿到流程定义和流程静态资源的服务
//        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();


        //一般方式创建 配置文件名字可以自定义， bean id不能随便写
//        ProcessEngineConfiguration processEngineConfigurationFromResource
//                = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//        System.out.println(processEngineConfigurationFromResource);

        //bean id都可以随便写
        ProcessEngineConfiguration processEngineConfiguration1 = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml",
                "processEngineConfiguration1");
        System.out.println(processEngineConfiguration1);

//        processEngineConfiguration1.getRuntimeService()

    }
}
