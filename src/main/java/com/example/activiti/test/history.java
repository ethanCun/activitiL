package com.example.activiti.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

//演示历史记录查询
public class history {

    public static void main(String[] args) {


        //获取引擎
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        //获取serivice
        HistoryService historyService = defaultProcessEngine.getHistoryService();

        //act_hi_actinst:历史活动表
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        //可以根据多个条件查询
        historicActivityInstanceQuery.processInstanceId("7501");
        historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime().asc();

        //查询内容
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println("---------------------");
            HistoricActivityInstance historicActivityInstance = list.get(i);
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getAssignee());
            System.out.println(historicActivityInstance.getId());
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println("====================");
        }
    }
}
