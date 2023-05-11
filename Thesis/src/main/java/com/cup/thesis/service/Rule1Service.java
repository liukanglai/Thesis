package com.cup.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cup.thesis.entity.Rule1;
import com.cup.thesis.mapper.Rule1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Rule1Service {
    public static double[][] judgementMatrix = {
            {1, 2, 5, 7, 1, 2, 6, 4},
            {1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2},
            {1 / 5.0, 1 / 3.0, 1, 3, 1 / 5.0, 1 / 3.0, 2, 1},
            {1 / 7.0, 1 / 5.0, 1 / 3.0, 1, 1 / 7.0, 1 / 5.0, 1 / 3.0, 1 / 2.0},
            {1, 3, 5, 7, 1, 3, 6, 4},
            {1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2},
            {1 / 6.0, 1 / 4.0, 1 / 2.0, 3, 1 / 6.0, 1 / 4.0, 1, 1 / 2.0},
            {1 / 4.0, 1 / 2.0, 1, 2, 1 / 4.0, 1 / 2.0, 2, 1}
    };
    @Autowired
    private Rule1Mapper rule1Mapper;

    public List<Rule1> getRule1() {
        //通过登录名查询用户
        QueryWrapper<Rule1> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "usetime", "distance");
        List<Rule1> infoList = rule1Mapper.selectList(queryWrapper);
//        infoList.forEach(System.out::println);
        return infoList;
    }

    public void setTime(int id, double time) {
        System.out.println(time);
        /*
        Rule1 rule1 = new Rule1();
        rule1.setId(id);
        rule1.setUsetime(time);
        rule1Mapper.updateById(rule1);

         */
        LambdaUpdateWrapper<Rule1> updateWrapper = new LambdaUpdateWrapper<Rule1>()
                .eq(Rule1::getId, id);
        updateWrapper.set(Rule1::getUsetime, time);
        int result = rule1Mapper.update(null, updateWrapper);

    }

    public void setDis(int id, double dis) {
        LambdaUpdateWrapper<Rule1> updateWrapper = new LambdaUpdateWrapper<Rule1>()
                .eq(Rule1::getId, id);
        updateWrapper.set(Rule1::getDistance, dis);
        int result = rule1Mapper.update(null, updateWrapper);
    }
}