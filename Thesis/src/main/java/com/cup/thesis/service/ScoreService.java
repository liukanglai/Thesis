package com.cup.thesis.service;

import com.cup.thesis.AHP;
import com.cup.thesis.FuzzyMY;
import com.cup.thesis.entity.Judge;
import com.cup.thesis.mapper.JudgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ScoreService {
    @Autowired
    InfoService infoService;
    @Autowired
    RuleService ruleService;
    static double[][][] judge;

    public double[][] getObjFuzzy(String userName, int levels, int objId) {
        double[] statusInfo = infoService.getStatus(userName);
        if(statusInfo[objId/4] == 0){
            return null;
        }
        double[][] objectInfo = infoService.getObj(userName);
        double[][] ruleInfo = ruleService.getObj(userName);

        double[][]  objectFuzzy = FuzzyMY.buildFuzzyMatrix(objectInfo[objId], ruleInfo[objId], levels);
//        System.out.println("objId: "+ objId+" objFuzzy: " + Arrays.deepToString(objectFuzzy));
        return objectFuzzy;
    }

    public double[][] getQualityFuzzy(String userName, int levels, int step) {
        double[] statusInfo = infoService.getStatus(userName);
        if(statusInfo[step] == 0){
            return null;
        }
        this.judge = ruleService.getJudge();
        double[][] qualityFuzzy = new double[4][];
        double[] objWeights = AHP.calculateWeights(judge[2]);
        int k = 0;
        for (int i = step * 4; i < step * 4 + 4; i++) {
            qualityFuzzy[k++] = FuzzyMY.calculateFuzzyScores(getObjFuzzy(userName, levels, i), objWeights);
        }

        //        FuzzyMY.printFuzzyMatrix(objectFuzzy1);
        //System.out.println("权重: " + Arrays.toString(weights));

//        System.out.println("step: "+step+"quaScore: "+ Arrays.deepToString(qualityFuzzy));

        return qualityFuzzy;
    }

    public double[][] getStepFuzzy(String userName, int levels, int step) {
        double[] statusInfo = infoService.getStatus(userName);
        if(statusInfo[step] == 0){
            return null;
        }
        this.judge = ruleService.getJudge();
        double[] timeInfo = infoService.getTime(userName);
        double[] distanceInfo = infoService.getDis(userName);
        double[][] stepRule = ruleService.getRule();

        double[][] stepFuzzy = new double[3][levels];
        stepFuzzy[0] = FuzzyMY.calculateFuzzyValuesForLevel(timeInfo[step], stepRule[step][0], stepRule[step][1], levels);
        stepFuzzy[1] = FuzzyMY.calculateFuzzyValuesForLevel(distanceInfo[step], stepRule[step][2], stepRule[step][3], levels);
        double[] qualityWeights = AHP.calculateWeights(judge[3]);
        stepFuzzy[2] = FuzzyMY.calculateFuzzyScores(getQualityFuzzy(userName, levels, step), qualityWeights);

//        System.out.println("step: "+step+"stepFuzzy: "+ Arrays.deepToString(stepFuzzy));
        return stepFuzzy;
    }

    public double[][] getFinallyFuzzy(String userName, int levels) {
        double[] statusInfo = infoService.getStatus(userName);
        this.judge = ruleService.getJudge();
        double[][] finallyFuzzy = new double[8][levels];
        for (int i = 0; i < 8; i++) {
            if(statusInfo[i] == 1) {
                double[] caoWeights = AHP.calculateWeights(judge[1]);
                finallyFuzzy[i] = FuzzyMY.calculateFuzzyScores(getStepFuzzy(userName, levels, i), caoWeights);
            }
        }
//        System.out.println("8fuzzy: "+Arrays.deepToString(finallyFuzzy));
        return finallyFuzzy;
    }

    public double[] getFinallyScore(String userName, int levels) {
        this.judge = ruleService.getJudge();
        double[] stepWeights = AHP.calculateWeights(judge[0]);
        double[] statusInfo = infoService.getStatus(userName);
        for (int i = 0; i < statusInfo.length; i++) {
            if(statusInfo[i] == 0){
                getFinallyFuzzy(userName, levels);
                return null;
            }
        }
        double[] finallyScore = FuzzyMY.calculateFuzzyScores(getFinallyFuzzy(userName, levels), stepWeights);
        return finallyScore;
    }
}