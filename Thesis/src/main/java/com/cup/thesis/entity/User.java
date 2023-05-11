package com.cup.thesis.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String loginName;
    private String password;
    private String age;
    private String sex;
    private String phone;
    //省略getter、setter等
}
