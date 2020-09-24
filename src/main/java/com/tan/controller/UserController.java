package com.tan.controller;

import com.tan.utils.PasswordEncoder;
import com.tan.service.UserService;
import com.tan.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    PasswordEncoder pe;

    @PostMapping("/toRegist")
    public String toRegist(String username,String password){
        String salt = String.valueOf((int) (Math.random() * 1_0000_0000));

        if (userService.regist(username,password,salt)) {
            System.out.println("注册成功");
            return "login";
        }else {
            System.out.println("注册失败");
            return "regist";
        }
    }

    @PostMapping("/toMain")
    public String toMain(HttpServletRequest req, @RequestParam String username, @RequestParam String password){

        if (userService.check(username,password)) {
            SessionUtils.setAttribute(req,"authed",username);
            return "main";
        }

        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req){
        SessionUtils.removeAll(req);
        return "main";
    }
}
