package com.tan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String login(){ return "login"; }

    @RequestMapping("/regist")
    public String regist(){ return "regist"; }

    @RequestMapping("/main")
    public String main(){return "main";}

    @RequestMapping("/")
    public String a(){ return "main";}

    @RequestMapping("/auth/auth1")
    public String auth1(){return "auth/auth";}

    @RequestMapping("/auth/auth2")
    public String auth2(){return "auth/level1";}


}
