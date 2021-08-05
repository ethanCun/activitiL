package com.example.activiti.test.groupTask;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.net.ResponseCache;
import java.util.List;

/**
 *  组任务：
 *      一个任务可以多个人来完成, 有多个负责人
 *      注意： 组任务候选人在BPMN的cadidateUsers中设置
 *
 *          <userTask activiti:assignee="zhang" activiti:exclusive="true" id="_3" name="主管审批"/>
 *          <userTask activiti:candidateUsers="li,wang" activiti:exclusive="true" id="_4" name="经理审批"/>
 *          <userTask activiti:assignee="liu" activiti:exclusive="true" id="_5" name="总经理审批"/>
 *
 *
 *   组任务的办理流程：
 *      候选人列表里面的人不能立即办理任务
 *      1. 查询任务
 *      2. 拾取任务： 不能完成可以归还任务， 可以完成将组任务变成个人任务
 *      3. 查询个人任务
 *      4. 完成个人任务
 *      5. 归还任务
 *      7. 更改任务候选人(任务交接)
 *
 *      候选人信息保存在act_ru_identitylink表中
 *
 */
public class GroupTaskTest {

    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    private static RuntimeService runtimeService =  processEngine.getRuntimeService();
    protected static TaskService taskService = processEngine.getTaskService();

    public static void main(String[] args) {

        changeTaskUserAssignee();
    }

    public static void deploy(){

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("groupTask/GroupTask.bpmn")
                .name("组任务")
                .deploy();

        System.out.println("部署id： " + deployment.getId());
    }

    public static void start(){

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("groupTask", "groupTask");
        System.out.println("流程实例id： " + processInstance.getId());
        System.out.println("流程部署id： " + processInstance.getDeploymentId());
    }

    /**
     * 查询组任务
     */
    public static void searchGroupTask(){

        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("groupTask")
                .taskCandidateUser("li") //传入候选人名称， 查询li所有的候选任务
                .list();

        for (Task task : taskList) {

            System.out.println("流程实例id: " + task.getProcessInstanceId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("流程定义id：" + task.getProcessDefinitionId());
        }
    }

    /**
     * 候选人拾取组任务
     *  归还之后act_ru_task表中的assignee会变味li
     *
     *  已经被拾取的任务不能再被其他人拾取， 不然会报错： Task '60002' is already claimed by someone else.
     */
    public static void getGroupTask(){

        String taskId = "60002";
        String user = "li";

        //找到组任务
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateUser(user)
                .singleResult();

        //拾取任务
        taskService.claim(taskId, user);

        System.out.println(user + " 拾取任务 " + taskId + " 完成");
    }

    /**
     * 查询个人待办业务
     */
    public static void searchUserTask(){

        Task task = taskService.createTaskQuery()
                .taskAssignee("li")
                .processDefinitionKey("groupTask")
                .singleResult();

        System.out.println("任务id： " + task.getId());
        System.out.println("任务名称： " + task.getName());
    }

    /**
     * 归还任务： 讲个人任务重新变为组任务
     *  归还任务就是把任务的assignee设置为null
     */
    public static void sendbackTask(){

        String user = "li";
        String taskId = "60002";

        Task task = taskService.createTaskQuery()
                .taskAssignee(user)
                .taskId(taskId)
                .singleResult();

        //归还任务就是将任务的assignee设置为null
        taskService.setAssignee(taskId, null);

        System.out.println(user + " 归还任务成功： " + taskId);
    }

    /**
     * 任务交接， 任务候选人更改
     */
    public static void changeTaskUserAssignee(){

        String taskId = "60002";
        String user = "li";

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .taskAssignee(user)
                .singleResult();

        //li将任务交接给wang, 可以交接给不在候选列表里面的候选人
        taskService.setAssignee(taskId, "wang");

        System.out.println(taskId + " 任务由li交接给:wang" );
    }

    /**
     * 完成个人任务
     */
    public static void complete(){

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("groupTask")
                .taskAssignee("zhang")
                .singleResult();

        taskService.complete(task.getId());
    }




    /**
     * 删除流程定义 会删除下面所有的流程实例
     */
    public static void delete(){

        Deployment deployment = repositoryService.createDeploymentQuery().deploymentName("组任务").singleResult();
        repositoryService.deleteDeployment(deployment.getId());
    }

}
