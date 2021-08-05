package com.example.activiti.test.Listener;


import org.activiti.engine.delegate.DelegateTask;

/**
 * Activiti任务监听器回调类， 需要在BPMN的listeners中指定
 */
public class TaskListener implements org.activiti.engine.delegate.TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("走了监听器方法： ----------------------");

        //指定负责人 根据任务名称和时间名称
        if (delegateTask.getName().equals("主管审批") && "create".equals(delegateTask.getEventName())) {
            delegateTask.setAssignee("zhangsan");
        }else if ("经理审批".equals(delegateTask.getName()) && "create".equals(delegateTask.getEventName())){
            delegateTask.setAssignee("lisi");
        }
    }
}
