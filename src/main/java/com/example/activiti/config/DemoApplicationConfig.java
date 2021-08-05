package com.example.activiti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

/**
 * 构造springsecurity用户信息
 */
@Configuration
public class DemoApplicationConfig {

    //用户信息bean
    @Bean
    public UserDetailsService userDetailsService(){

        //将用户信息保存在内存中
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        //构造用户信息 角色需要加前缀ROLE_ 组需要加前缀GROUP_
        String[][] userInfo = {
                {"jack", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"jack", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"jack", "123456", "ROLE_ACTIVITY_ADMIN", "GROUP_ACTIVITY_TEAM"}
        };

        //放入内存
        for (String[] users : userInfo) {

            Arrays.copyOfRange(users, 2, users.length);
        }

        return null;
    }

    public static void main(String[] args) {

        //构造用户信息 角色需要加前缀ROLE_ 组需要加前缀GROUP_
        String[][] userInfo = {
                {"jack", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"jack", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"jack", "123456", "ROLE_ACTIVITY_ADMIN", "GROUP_ACTIVITY_TEAM"}
        };

        //放入内存
        for (String[] users : userInfo) {

            String[] authrites = Arrays.copyOfRange(users, 2, users.length);
            Arrays.stream(authrites).forEach(System.out::println);
        }

    }



}
