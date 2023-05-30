package com.cup.thesis.controller;

import com.cup.thesis.AHP;
import com.cup.thesis.FuzzyMY;
import com.cup.thesis.entity.Rule1;
import com.cup.thesis.entity.Rule2;
import com.cup.thesis.service.InfoService;
import com.cup.thesis.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RuleController {
    @Autowired
    private RuleService ruleService;
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/api/rule1")
    @CrossOrigin
    public List<Rule1> getRule1() {
        return ruleService.getRule1();
    }

    @RequestMapping(value = "/api/rule2")
    @CrossOrigin
    public List<Rule2> getRule2() {
        return ruleService.getRule2();
    }

    @RequestMapping(value = "/api/rule1/setTime/{id}/{value}")
    @CrossOrigin
    public void setTime(@PathVariable String id, @PathVariable String value) {
        ruleService.setTime(Integer.parseInt(id), Double.parseDouble(value));
    }

    @RequestMapping(value = "/api/rule1/setTimeSD/{id}/{value}")
    @CrossOrigin
    public void setTimeSD(@PathVariable String id, @PathVariable String value) {
        ruleService.setTimeSD(Integer.parseInt(id), Double.parseDouble(value));
    }

    @RequestMapping(value = "/api/rule1/setDis/{id}/{value}")
    @CrossOrigin
    public void setDis(@PathVariable int id, @PathVariable double value) {
        ruleService.setDis(id, value);
    }

    @RequestMapping(value = "/api/rule1/setDisSD/{id}/{value}")
    @CrossOrigin
    public void setDisSD(@PathVariable int id, @PathVariable double value) {
        ruleService.setDisSD(id, value);
    }

    @RequestMapping(value = "/api/rule1/ahp1")
    @CrossOrigin
    public double[][] getAHP1() {
        double[][][] judge = ruleService.getJudge();
        return judge[0];
    }

    @RequestMapping(value = "/api/rule1/ahp2")
    @CrossOrigin
    public double[][] getAHP2() {
        double[][][] judge = ruleService.getJudge();
        return judge[1];
    }

    @RequestMapping(value = "/api/rule1/ahp3")
    @CrossOrigin
    public double[][] getAHP3() {
        double[][][] judge = ruleService.getJudge();
        return judge[2];
    }

    @RequestMapping(value = "/api/rule1/ahp4")
    @CrossOrigin
    public double[][] getAHP4() {
        double[][][] judge = ruleService.getJudge();
        return judge[3];
    }

    @RequestMapping(value = "/api/rule1/weight1")
    @CrossOrigin
    public double[] getWeight1() {
        // 将3个一维数组合成一个二维数组
        /*
        double[] arr1 = AHP.calculateWeights(AHP.judgementMatrix1);
        double[] arr2 = AHP.calculateWeights(AHP.judgementMatrix2);
        double[] arr3 = AHP.calculateWeights(AHP.judgementMatrix3);
        return Stream.of(arr1, arr2, arr3)
                .map(Arrays::stream)
                .map(DoubleStream::toArray)
                .toArray(double[][]::new);

         */
        double[][][] judge = ruleService.getJudge();
        return AHP.calculateWeights(judge[0]);
    }

    @RequestMapping(value = "/api/rule1/weight2")
    @CrossOrigin
    public double[] getWeight2() {
        double[][][] judge = ruleService.getJudge();
        return AHP.calculateWeights(judge[1]);
    }

    @RequestMapping(value = "/api/rule1/weight3")
    @CrossOrigin
    public double[] getWeight3() {
        double[][][] judge = ruleService.getJudge();
        return AHP.calculateWeights(judge[2]);
    }

    @RequestMapping(value = "/api/rule1/weight4")
    @CrossOrigin
    public double[] getWeight4() {
        //return AHP.calculateWeights(AHP.judgementMatrix4);
        double[][][] judge = ruleService.getJudge();
        return AHP.calculateWeights(judge[3]);
    }

    @RequestMapping(value = "/api/rule1/fuzzy/{userName}")
    @CrossOrigin
    public double[][] getFuzzy(@PathVariable String userName) {
        double[] score = infoService.getTime(userName);
        double[] rule = infoService.getTime(userName);
        int levels = 4; // 假设有4个评价等级
        return FuzzyMY.buildFuzzyMatrix(score, rule, levels);
    }

    @RequestMapping(value = "/api/rule1/cr")
    @CrossOrigin
    public double[] getCR() {
        double[][][] judge = ruleService.getJudge();
        double[] CR = new double[4];
        CR[0] = AHP.calculateCR(judge[0]);
        CR[1] = AHP.calculateCR(judge[1]);
        CR[2] = AHP.calculateCR(judge[2]);
        CR[3] = AHP.calculateCR(judge[3]);
//        System.out.println(Arrays.toString(CR));
        return CR;
    }
    @RequestMapping(value = "/api/rule1/back/")
    @CrossOrigin
    public double backAHP() {
        return ruleService.backAHP();
    }

    @RequestMapping(value = "/api/rule1/change/{str}")
    @CrossOrigin
    public double changeAHP(@PathVariable String str) {
        System.out.println(str);
        String[] strArr = str.split(",");
        int l = 0;
        switch (strArr.length) {
            case 64 -> l = 8;
            case 9 -> l = 3;
            case 16 -> l = 4;
            case 81 -> l = 9;
            default -> {
                return -100;
            }
        }
        double[][] Arr = new double[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                Arr[i][j] = Double.parseDouble(strArr[i * l + j]);
            }
        }
        /*
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                Arr[i][j] = Double.parseDouble(strArr[i]);
            }
        }

         */

        System.out.println(Arrays.deepToString(Arr));
        double CR = AHP.calculateCR(Arr);
        if(CR > 0.1){
            return CR;
        }
        System.out.println(CR);
//        System.out.println(output);
//        System.out.println(Arrays.toString(intArr));
        return ruleService.changeAHP(Arr);
    }
}
