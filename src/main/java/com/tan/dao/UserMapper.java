package com.tan.dao;

import com.tan.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    User queryUserByUsername(String username);
    int insertOne(String username,String password,String salt);
}
