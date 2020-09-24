package com.tan.webconfig;

import com.tan.filter.Filter;
import com.tan.utils.PasswordEncoder;
import com.tan.service.UserService;
import com.tan.service.UserServiceImpl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Filter());
        //过滤所有请求
        registration.addUrlPatterns("/**");
        registration.setName("Filter");
        //设置优先级，值越小优先级越高
        registration.setOrder(6);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
}
