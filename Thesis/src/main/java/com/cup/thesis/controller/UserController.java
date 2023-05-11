package com.cup.thesis.controller;

import com.cup.thesis.entity.Login;
import com.cup.thesis.entity.User;
import com.cup.thesis.service.LoginService;
import com.cup.thesis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/user/{userName}")
    @CrossOrigin       //后端跨域
    public List<User> getUser(@PathVariable String userName) {
        //System.out.println(login.getLoginName());
        //System.out.println(login.getPassword());
        return userService.getUser(userName);
    }

}
