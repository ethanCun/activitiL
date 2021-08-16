package com.example.activiti.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetails us = User.builder()
                .username("czy")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("admin", "ROLE_ACTIVITI_USER", "GROUP_ACTIVITY_TEAM")
                .build();

        return us;
    }
}
