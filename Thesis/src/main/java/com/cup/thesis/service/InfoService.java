package com.cup.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cup.thesis.entity.Info;
import com.cup.thesis.entity.Trail;
import com.cup.thesis.entity.User;
import com.cup.thesis.mapper.InfoMapper;
import com.cup.thesis.mapper.TrailMapper;
import com.cup.thesis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    @Autowired
    private InfoMapper infoMapper;

    public List<Info> getInfo(String userName) {
        //通过登录名查询用户
        QueryWrapper<Info> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<Info> infoList = infoMapper.selectList(queryWrapper);
//        infoList.forEach(System.out::println);
        return infoList;
    }
    public double[] getTime(String userName) {
        QueryWrapper<Info> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName).select("usetime");
        List<Info> infoList = infoMapper.selectList(queryWrapper);
        //infoList.forEach(System.out::println);
        // 将查询结果转换为数组
        // Object[] userArray = infoList.stream().map(User::toArray).toArray();
        return infoList.stream().mapToDouble(Info::getUsetime).toArray();
    }
    @Autowired
    private TrailMapper trailMapper;

    public List<Trail> getInfo1(String userName) {
        //通过登录名查询用户
        QueryWrapper<Trail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<Trail> trailList = trailMapper.selectList(queryWrapper);
        //trailList.forEach(System.out::println);
        return trailList;
    }
}
