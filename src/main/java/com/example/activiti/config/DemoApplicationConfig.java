package com.example.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 构造springsecurity用户信息
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class DemoApplicationConfig {


    //用户信息bean
    @Bean
    public UserDetailsService userDetailsService(){

        //将用户信息保存在内存中
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        //构造用户信息 角色需要加前缀ROLE_ 组需要加前缀GROUP_  下划线后面的才是用户角色或者用户组名
        //这个组名配合BPMN中设置的cadidateGroup可以设置候选组
        //设置任务组的好处是当不确定将来谁来处理这个任务的时候，只要在任务组里面的人都可以来拾取这个任务
        String[][] userInfo = {
                {"jack", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"lili", "123456", "ROLE_ACTIVITY_USER", "GROUP_ACTIVITY_TEAM"},
                {"rose", "123456", "ROLE_ACTIVITY_ADMIN", "GROUP_ACTIVITY_TEAM"}
        };

        //放入内存
        for (String[] users : userInfo) {

            //角色和权限
            String[] authStrList = Arrays.copyOfRange(users, 2, users.length);
            List<String> authList = Arrays.asList(authStrList);

            log.info("用户信息： {}， {}, {}, {}", users[0], users[1], users[2], users[3]);

            //参数1： 用户名
            //参数2： 密码
            //参数3： 角色和权限信息 string转为SimpleGrantedAuthority
            inMemoryUserDetailsManager.createUser(new User(users[0],
                    passwordEncoder().encode(users[1]),
                    authList.stream().map(str -> new SimpleGrantedAuthority(str)).collect(Collectors.toList())));

        }

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
