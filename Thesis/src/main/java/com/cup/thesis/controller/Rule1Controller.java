package com.cup.thesis.controller;

import com.cup.thesis.AHP;
import com.cup.thesis.FuzzyMY;
import com.cup.thesis.entity.Rule1;
import com.cup.thesis.mapper.Rule1Mapper;
import com.cup.thesis.service.InfoService;
import com.cup.thesis.service.Rule1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Rule1Controller {
    @Autowired
    private Rule1Service rule1Service;
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/api/rule1")
    @CrossOrigin
    public List<Rule1> getRule(){
        return rule1Service.getRule1();
    }

    @RequestMapping(value = "/api/rule1/setTime/{id}/{value}")
    @CrossOrigin
    public void setTime(@PathVariable String id, @PathVariable String value){
        rule1Service.setTime(Integer.parseInt(id), Double.parseDouble(value));
    }

    @RequestMapping(value = "/api/rule1/setDis/{id}/{value}")
    @CrossOrigin
    public void setDis(@PathVariable int id, @PathVariable double value){
        rule1Service.setDis(id, value);
    }

    @RequestMapping(value = "/api/rule1/weight")
    @CrossOrigin
    public double[] getWeight(){
        return AHP.calculateWeights(Rule1Service.judgementMatrix);
    }

    @RequestMapping(value = "/api/rule1/membership")
    @CrossOrigin
    public void setMembership(@PathVariable int id, @PathVariable double value){
    }

    @RequestMapping(value = "/api/rule1/fuzzy/{userName}")
    @CrossOrigin
    public double[][] getFuzzy(@PathVariable String userName){
        double[] score = infoService.getTime(userName);
        int levels = 3; // 假设有3个评价等级
        return FuzzyMY.buildFuzzyMatrix(score, levels);
    }
}
