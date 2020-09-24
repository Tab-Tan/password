package com.tan.service;

import com.tan.dao.UserMapper;
import com.tan.pojo.User;
import com.tan.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder pe;

    @Autowired(required=false)
    UserMapper userMapper;

    @Override
    public boolean check(String username, String password) {

        User user = userMapper.queryUserByUsername(username);
        String pwd = pe.passwordEncode(password,Integer.parseInt(user.getSalt()));
        return pwd.equals(user.getPassword());
    }

    @Override
    public boolean regist(String username, String password, String salt) {

        String psw = pe.passwordEncode(password, Integer.parseInt(salt));

        int i = userMapper.insertOne(username,psw, salt);
        return i != 0;
    }
}
