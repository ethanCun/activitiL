package com.example.activiti.test.resourceDownload;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

//演示从数据库中下载BPMN或者PNG资源文件
public class TestResourcesDownload {

    /**
     *  常用两种方式：
     *
     *  1. 使用activiti提供的下载方案
     *
     *  2. 使用jdbc对blob类型 clob类型读取出来， 保存到目录, io操作使用commons-io操作
     *
     */
    public static void main(String[] args) throws IOException {

        //1. 使用activiti提供的下载方案
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        //2. 获取serivce
        RepositoryService repositoryService = engine.getRepositoryService();

        //获取流程定义
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //获取流程定义部署id
        List<ProcessDefinition> demo = processDefinitionQuery.processDefinitionKey("demo")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        for (int i = 0; i < demo.size(); i++) {

            if(demo.get(i).getName().equals("demo")){

                //流程部署id
                String deploymentId = demo.get(i).getDeploymentId();
                //资源名称
                String resourceName = demo.get(i).getResourceName();

                //根据部署id找到对应的BPMN资源信息
                InputStream bpmnStream = repositoryService.getResourceAsStream(deploymentId, resourceName);

                //构建保存位置
                File file = new File("C:/Users/RJET-开发1/Desktop/aa/bpmn");

                //构建流
                FileOutputStream fileOutputStream = new FileOutputStream(file);
//
//                int len = 0;
//
//                while ((len = bpmnStream.read()) != -1){
//                    fileOutputStream.write(len);
//                }
//
//                fileOutputStream.close();

                int copySize = IOUtils.copy(bpmnStream, fileOutputStream);
                System.out.println(copySize);

                fileOutputStream.close();
                bpmnStream.close();
            }
        }

    }
}
