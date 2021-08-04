package com.example.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

//以zip的方式上传多个bpmn
public class TestZipDeploy {

    public static void main(String[] args) {

        //获取流程引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取部署服务
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        //以zip的方式批量部署bpmn
        InputStream resourceAsStream = TestZipDeploy.class.getClassLoader().getResourceAsStream("zip/zip.zip");
        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();

        //zip包的形式部署，因为包含了多个流程， 所以通常不会统一去设置名称
        System.out.println("部署id: " + deploy.getId() + " " + deploy.getName() + " " + deploy.getKey() + " " + deploy.getCategory() +
                " " + deploy.getTenantId() + " " + deploy.getDeploymentTime());

    }
}
