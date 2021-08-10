package com.example.activiti.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 因为activiti默认继承了springsecurity， 所以需要配置一下相关的信息
 */
@Slf4j
@Component
public class SecurityUtil extends WebSecurityConfigurerAdapter {

    //获取springsecurity里面的用户信息
    @Autowired
    private UserDetailsService userDetailsService;

    public void loginInAs(String username){

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        log.info("当前用户名： " + username);

        SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public Object getCredentials() {
                return user.getPassword();
            }

            @Override
            public Object getDetails() {
                return user;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return user.getUsername();
            }
        }));

        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers()
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    //配置用户权限信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(mypasswordEncoder());
    }

    public PasswordEncoder mypasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
