package com.cup.thesis.controller;

import com.cup.thesis.entity.Result;
import com.cup.thesis.entity.Login;
import com.cup.thesis.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/api/login")
    @CrossOrigin       //后端跨域
    public String login(@RequestBody Login login){
        //System.out.println(login.getLoginName());
        //System.out.println(login.getPassword());
        return loginService.login(login);
    }

}
