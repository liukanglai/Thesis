package com.cup.thesis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cup.thesis.AHP;
import com.cup.thesis.entity.Judge;
import com.cup.thesis.entity.Rule1;
import com.cup.thesis.entity.Rule2;
import com.cup.thesis.entity.Trail;
import com.cup.thesis.mapper.JudgeMapper;
import com.cup.thesis.mapper.Rule1Mapper;
import com.cup.thesis.mapper.Rule2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

@Service
public class RuleService {
    @Autowired
    private Rule1Mapper rule1Mapper;
    @Autowired
    private Rule2Mapper rule2Mapper;
    @Autowired
    private JudgeMapper judgeMapper;

    public double[][][] getJudge() {
        double[][][] judgeMatrix = new double[4][][];
        List<Judge> judgementMatrixList = judgeMapper.selectList(null);
//        String str = "[[1, 2, 5, 7, 1, 2, 6, 4], [1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2], [1 / 5.0, 1 / 3.0, 1, 3, 1 / 5.0, 1 / 3.0, 2, 1], [1 / 7.0, 1 / 5.0, 1 / 3.0, 1, 1 / 7.0, 1 / 5.0, 1 / 3.0, 1 / 2.0], [1, 3, 5, 7, 1, 3, 6, 4], [1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2], [1 / 6.0, 1 / 4.0, 1 / 2.0, 3, 1 / 6.0, 1 / 4.0, 1, 1 / 2.0], [1 / 4.0, 1 / 2.0, 1, 2, 1 / 4.0, 1 / 2.0, 2, 1]]";
        if (judgementMatrixList != null && !judgementMatrixList.isEmpty()) {
            Judge judgementMatrix = judgementMatrixList.get(0);
//            System.out.println(judgementMatrix.getArray1());
            // 将array1、array2、array3转换为对应的二维数组
            double[][] array1 = JSON.parseObject(judgementMatrix.getArray1(), double[][].class);
            double[][] array2 = JSON.parseObject(judgementMatrix.getArray2(), double[][].class);
            double[][] array3 = JSON.parseObject(judgementMatrix.getArray3(), double[][].class);
            double[][] array4 = JSON.parseObject(judgementMatrix.getArray4(), double[][].class);

            // 将三个二维数组存入judge数组中
            judgeMatrix[0] = array1;
            judgeMatrix[1] = array2;
            judgeMatrix[2] = array3;
            judgeMatrix[3] = array4;

        }
//        System.out.println(Arrays.deepToString(judgeMatrix));
        return judgeMatrix;
    }
    public double backAHP() {
        String str1 = Arrays.deepToString(AHP.judgementMatrix1);
        String str2 = Arrays.deepToString(AHP.judgementMatrix2);
        String str3 = Arrays.deepToString(AHP.judgementMatrix3);
        String str4 = Arrays.deepToString(AHP.judgementMatrix4);
        Judge judge = new Judge();
        judge.setId(1); // 设置需要更新的记录的主键值
        judge.setArray1(str1);
        judge.setArray2(str2);
        judge.setArray3(str3);
        judge.setArray4(str4);
        return judgeMapper.updateById(judge);
    }

    public int changeAHP(double[][] str) {
//        List<Judge> judgementMatrixList = judgeMapper.selectList(null);
        String str1 = Arrays.deepToString(str);
        Judge judge = new Judge();
        judge.setId(1); // 设置需要更新的记录的主键值
        System.out.println(str.length);
        switch (str.length) {
            case 8 -> judge.setArray1(str1);
            case 3 -> judge.setArray2(str1);
            case 9 -> judge.setArray3(str1);
            case 4 -> judge.setArray4(str1);
            default -> {
            }
        }
        return judgeMapper.updateById(judge);
    }

    public List<Rule2> getRule2() {
        return rule2Mapper.selectList(null);
    }

    public List<Rule1> getRule1() {
        //通过登录名查询用户
        QueryWrapper<Rule1> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "usetime", "usetimeSD", "distance", "distanceSD");
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

    public void setTimeSD(int id, double time) {
        LambdaUpdateWrapper<Rule1> updateWrapper = new LambdaUpdateWrapper<Rule1>()
                .eq(Rule1::getId, id);
        updateWrapper.set(Rule1::getUsetimesd, time);
        int result = rule1Mapper.update(null, updateWrapper);
    }

    public void setDis(int id, double dis) {
        LambdaUpdateWrapper<Rule1> updateWrapper = new LambdaUpdateWrapper<Rule1>()
                .eq(Rule1::getId, id);
        updateWrapper.set(Rule1::getDistance, dis);
        int result = rule1Mapper.update(null, updateWrapper);
    }

    public void setDisSD(int id, double dis) {
        LambdaUpdateWrapper<Rule1> updateWrapper = new LambdaUpdateWrapper<Rule1>()
                .eq(Rule1::getId, id);
        updateWrapper.set(Rule1::getDistancesd, dis);
        int result = rule1Mapper.update(null, updateWrapper);
    }

    public double[][] getObj(String userName) {
//        QueryWrapper<Rule2> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name",userName).select("objectx", "objecty","objectz","anglex","angley","anglez", "speedx", "speedy", "speedz");
        List<Rule2> objectList = rule2Mapper.selectList(null);
        double[][] objectArray = new double[objectList.size()][18];
        for (int i = 0; i < objectList.size(); i++) {
            Rule2 rule2 = objectList.get(i);
            objectArray[i][0] = rule2.getObjectx();
            objectArray[i][1] = rule2.getObjectxsd();
            objectArray[i][2] = rule2.getObjecty();
            objectArray[i][3] = rule2.getObjectysd();
            objectArray[i][4] = rule2.getObjectz();
            objectArray[i][5] = rule2.getObjectzsd();
            objectArray[i][6] = rule2.getAnglex();
            objectArray[i][7] = rule2.getAnglexsd();
            objectArray[i][8] = rule2.getAngley();
            objectArray[i][9] = rule2.getAngleysd();
            objectArray[i][10] = rule2.getAnglez();
            objectArray[i][11] = rule2.getAnglezsd();
            objectArray[i][12] = rule2.getSpeedx();
            objectArray[i][13] = rule2.getSpeedxsd();
            objectArray[i][14] = rule2.getSpeedy();
            objectArray[i][15] = rule2.getSpeedysd();
            objectArray[i][16] = rule2.getSpeedz();
            objectArray[i][17] = rule2.getSpeedzsd();
        }
        return objectArray;
    }

    public double[][] getRule() {
        List<Rule1> infoList = rule1Mapper.selectList(null);
        double[][] objectArray = new double[infoList.size()][4];
        for (int i = 0; i < infoList.size(); i++) {
            Rule1 rule1 = infoList.get(i);
            objectArray[i][0] = rule1.getUsetime();
            objectArray[i][1] = rule1.getUsetimesd();
            objectArray[i][2] = rule1.getDistance();
            objectArray[i][3] = rule1.getDistancesd();
        }
        return objectArray;
    }
}