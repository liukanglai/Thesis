package com.cup.thesis.controller;

import com.cup.thesis.FuzzyMY;
import com.cup.thesis.AHP;
import com.cup.thesis.service.InfoService;
import com.cup.thesis.service.RuleService;
import com.cup.thesis.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @RequestMapping(value = "/api/score/calculate/{userName}")
    @CrossOrigin       //后端跨域
    public double[] caculateScore(@PathVariable String userName) {
        int levels = 4; // 假设有4个评价等级
        return scoreService.getFinallyScore(userName, levels);
    }
    @RequestMapping(value = "/api/score/finallyFuzzy/{userName}")
    @CrossOrigin       //后端跨域
    public double[][] getFinallyFuzzy(@PathVariable String userName) {
        int levels = 4; // 假设有4个评价等级
        return scoreService.getFinallyFuzzy(userName, levels);
    }
    @RequestMapping(value = "/api/score/stepFuzzy/{userName}/{step}")
    @CrossOrigin       //后端跨域
    public double[][] getStepFuzzy(@PathVariable String userName, @PathVariable int step) {
        int levels = 4; // 假设有4个评价等级
        return scoreService.getStepFuzzy(userName, levels, step);
    }
    @RequestMapping(value = "/api/score/qualityFuzzy/{userName}/{step}")
    @CrossOrigin       //后端跨域
    public double[][] getQualityFuzzy(@PathVariable String userName, @PathVariable int step) {
        int levels = 4; // 假设有4个评价等级
        return scoreService.getQualityFuzzy(userName, levels, step);
    }
    @RequestMapping(value = "/api/score/objectFuzzy/{userName}/{objId}")
    @CrossOrigin       //后端跨域
    public double[][] getObjectFuzzy(@PathVariable String userName, @PathVariable int objId) {
        int levels = 4; // 假设有4个评价等级
        return scoreService.getObjFuzzy(userName, levels, objId);
    }
}
