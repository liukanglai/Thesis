package com.cup.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cup.thesis.entity.Login;
import com.cup.thesis.entity.User;
import com.cup.thesis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUser(String userName) {
        //通过登录名查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        return userList;
    }
}
