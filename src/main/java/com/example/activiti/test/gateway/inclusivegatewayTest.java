package com.example.activiti.test.gateway;

import com.example.activiti.entity.User;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;

/**
 * 包含网关:
 *  集合了排他网关和并行网关， 因此具有排他网关和并行网关的特点
 */
public class inclusivegatewayTest {


    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    private static RuntimeService runtimeService = processEngine.getRuntimeService();
    private static TaskService taskService = processEngine.getTaskService();
    private static HistoryService historyService = processEngine.getHistoryService();


    public static void main(String[] args) {

        history();
    }

    public static void deploy(){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("gateway/inclusivegateway.bpmn")
                .name("包含网关")
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
                .taskAssignee("zongjingli")
                .processDefinitionKey("inclusivegateway")
                .singleResult();

        HashMap<String, Object> map = new HashMap<>();
        User user = new User("zzz", "10", 4);
        map.put("user", user);

        taskService.complete(task.getId(), map);

        System.out.println("任务完成");
    }

    public static void delete(){

        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey("inclusivegateway").singleResult();
        repositoryService.deleteDeployment(deployment.getId(), true);
    }

    public static void history(){

        List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey("inclusivegateway")
                .orderByTaskCreateTime()
                .asc()
                .list();

        for (HistoricTaskInstance taskInstance : taskInstanceList) {

            System.out.println("--------------------------");

            System.out.println(taskInstance.getOwner());
            System.out.println(taskInstance.getAssignee());
            System.out.println(taskInstance.getName());
            System.out.println(taskInstance.getClaimTime());
            System.out.println(taskInstance.getStartTime());
            System.out.println(taskInstance.getEndTime());
            System.out.println(taskInstance.getDurationInMillis());
            System.out.println(taskInstance.getWorkTimeInMillis());

            System.out.println("----------------------------");
        }
    }
}
