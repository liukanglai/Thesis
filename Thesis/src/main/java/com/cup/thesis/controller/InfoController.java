package com.cup.thesis.controller;

import com.cup.thesis.entity.Info;
import com.cup.thesis.entity.Trail;
import com.cup.thesis.entity.User;
import com.cup.thesis.service.InfoService;
import com.cup.thesis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/api/info/{userName}")
    @CrossOrigin       //后端跨域
    public List<Info> getInfo(@PathVariable String userName) {
        //System.out.println(login.getLoginName());
        //System.out.println(login.getPassword());
        return infoService.getInfo(userName);
    }

    @RequestMapping(value = "/api/info1/{userName}")
    @CrossOrigin       //后端跨域
    public List<Trail> getInfo1(@PathVariable String userName) {
        //System.out.println(login.getLoginName());
        //System.out.println(login.getPassword());
        return infoService.getInfo1(userName);
    }

}
