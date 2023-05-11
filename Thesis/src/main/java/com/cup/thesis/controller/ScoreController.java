package com.cup.thesis.controller;

import com.cup.thesis.FuzzyMY;
import com.cup.thesis.AHP;
import com.cup.thesis.service.InfoService;
import com.cup.thesis.service.Rule1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ScoreController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/api/score/calculate/{userName}")
    @CrossOrigin       //后端跨域
    public double[] caculateScore(@PathVariable String userName) {
        double[] score = infoService.getTime(userName);
        /*
        for (double v : score) {
            System.out.println(v);
        }

         */

        int levels = 3; // 假设有3个评价等级
        double[][] fuzzyMatrix = FuzzyMY.buildFuzzyMatrix(score, levels);
        FuzzyMY.printFuzzyMatrix(fuzzyMatrix);
        double[] weights = AHP.calculateWeights(Rule1Service.judgementMatrix);
        System.out.println("权重: " + Arrays.toString(weights));
        double finalScore[] = FuzzyMY.calculateFuzzyScores(fuzzyMatrix, weights);
        for (double v : finalScore) {
            System.out.println(v);
        }
        return finalScore;
    }
}
