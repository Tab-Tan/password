package com.tan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    boolean check(String username,String password);

    boolean regist(String username,String password,String salt);
}
