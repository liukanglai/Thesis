package com.cup.thesis.entity;

import lombok.Data;

@Data
public class Trail {
    private Integer id;
    private Integer itemid;
    private String userName;
    private Integer objectid;
    private double objectx;
    private double objecty;
    private double objectz;
    private double anglex;
    private double angley;
    private double anglez;
    private double intime;
    private double speedx;
    private double speedy;
    private double speedz;
}
