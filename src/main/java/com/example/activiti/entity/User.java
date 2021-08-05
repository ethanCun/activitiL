package com.example.activiti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 注意：
 *  流程变量如果是pojo的话一定要实现序列化接口
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 8029067097563928989L;

    private String name;

    private String age;

    //请假天数
    private Integer days;
}
