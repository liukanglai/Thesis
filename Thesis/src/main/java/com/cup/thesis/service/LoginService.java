package com.cup.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.cup.thesis.entity.Result;
import com.cup.thesis.entity.Login;
import com.cup.thesis.entity.User;
import com.cup.thesis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public String login(Login login) {

        if (StringUtils.isEmpty(login.getLoginName())) {
            return ("账号不能为空");
        }
        if (StringUtils.isEmpty(login.getPassword())) {
            return ("密码不能为空");
        }
        //通过登录名查询用户
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("login_name", login.getLoginName());
        User uer = userMapper.selectOne(wrapper);
        //比较密码
        if (uer != null && uer.getPassword().equals(login.getPassword())) {
            return ("200");
        }
        return ("400");
    }
}
