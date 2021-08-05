package com.example.activiti.test.gateway;

/**
 * BPMN中的网关：
 *
 *  网关相比BPMN中的连线， 可以更加灵活地控制流程的走向
 *
 *  网关的种类：
 *      1. 排他网关：exclusivegateway 只有一个分支可以同时执行，选择满足条件的一条执行， 选择id较小的执行，  如果没有满足条件的就会抛出异常
 *      2. 并行网关：parallelgateway 可以多个分支同时执行， 所有分支执行完成才接受
 *      3. 包含网关：inclusivegateway
 *      4. 事件网关: eventgateway
 *
 */
public class GatewayTest {

    public static void main(String[] args) {

        testActivitiExclusiveGateway();
    }

    /**
     * activiti的排他网关
     */
    public static void testActivitiExclusiveGateway(){


    }
}
