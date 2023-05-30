package com.cup.thesis;

import java.util.Arrays;

public class FuzzyMY {
    public static void main(String[] args) {
        double[] weights = AHP.calculateWeights(AHP.judgementMatrix1);
        //double[] fuzzyInput = {0.2, 0.5, 0.8, 0.4, 0.6, 0.9, 0.3, 0.7};
        double[] fuzzyInput = {16000.0, 17000.0, 26000.0, 93000.0, 138000.0, 151000.0, 371000.0, 585000.0};
        int levels = 4; // 假设有4个评价等级
//        double[][] fuzzyMatrix = buildFuzzyMatrix(fuzzyInput, levels);
//        double[] scores = calculateFuzzyScores(fuzzyMatrix, weights);
//        printFuzzyMatrix(fuzzyMatrix);
//        System.out.println(Arrays.toString(scores));
        System.out.println(Arrays.toString(FuzzyMY.calculateFuzzyValuesForLevel(16000, 16000, 1000, levels)));
    }

    public static double[][] buildFuzzyMatrix(double[] fuzzyInput, double[] rule, int levels) {
        int n = fuzzyInput.length;
        double[][] fuzzyMatrix = new double[n][levels];

        for (int i = 0; i < n; i++) {
            double[] fuzzyValues = calculateFuzzyValuesForLevel(fuzzyInput[i], rule[2 * (i + 1) - 2], rule[2 * (i + 1) - 1], levels);
            for (int j = 0; j < levels; j++) {
                fuzzyMatrix[i][j] = fuzzyValues[j];
            }
        }

        return fuzzyMatrix;
    }


    // 良好的均值为优秀的均值+标准差，一般类似，较差相反
    public static double[] calculateFuzzyValuesForLevel(double input, double mid, double width, int levels) {
        double[] fuzzyValues = new double[levels];
        double sum = 0;

        for (int i = 0; i < levels; i++) {
            switch (i) {
                case 0:
                    fuzzyValues[i] = gaussianMembership(input, mid, width);
                    break;
                case 1:
                    if (input > mid) {
                        fuzzyValues[i] = gaussianMembership(input, mid + width, width);
                    } else {
                        fuzzyValues[i] = gaussianMembership(input, mid - width, width);
                    }
                    break;
                case 2:
                    if (input > mid) {
                        fuzzyValues[i] = gaussianMembership(input, mid + width + width, width);
                    } else {
                        fuzzyValues[i] = gaussianMembership(input, mid - width - width, width);
                    }
                    fuzzyValues[i] = gaussianMembership(input, mid + width + width, width);
                    break;
                case 3:
                    fuzzyValues[i] = gaussianMembership(mid, mid, width) - gaussianMembership(input, mid, width);
                    break;
                default:
                    break;
            }
//            System.out.println("value=" + input + ", midpoint=" + mid + ", width=" + width + ", membership=" + fuzzyValues[i]);
            sum += fuzzyValues[i];
        }

        // 标准化模糊值，使每行之和为1
        for (int i = 0; i < levels; i++) {
            fuzzyValues[i] /= sum;
        }

        return fuzzyValues;
    }

    /*
    public static double membershipFunction(double input, double levelValue) {
        double maxInput = 600000.0;
        double minInput = 10000.0;
        double range = maxInput - minInput;
        double normalizedInput = (input - minInput) / range;

        // 使用线性隶属度函数
        return Math.max(1.0 - Math.abs(normalizedInput - levelValue), 0.0);
    }

     */

    //接受三个参数：x是要计算隶属度的值，midpoint是高斯分布的中间点，width是高斯分布的标准差。该方法返回给定值x的高斯隶属度函数值。
    public static double gaussianMembership(double x, double midpoint, double width) {
        double exponent = -0.5 * Math.pow((x - midpoint) / width, 2);
        return Math.exp(exponent);
    }

    //System.out.println("x=" + x + ", midpoint=" + midpoint + ", width=" + width + ", membership=" + membership);
    public static double[] calculateFuzzyScores(double[][] fuzzyMatrix, double[] weights) {
        int m = fuzzyMatrix.length;
        int n = fuzzyMatrix[0].length;
        double[] scores = new double[n];

        // 对每个决策选项进行加权平均，得到模糊得分
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < m; j++) {
                sum += fuzzyMatrix[j][i] * weights[j];
            }
            scores[i] = sum;
        }

        return scores;
    }

    public static void printFuzzyMatrix(double[][] fuzzyMatrix) {
        System.out.println("模糊评价矩阵");
        System.out.println("A           B           C           D");
        for (double[] row : fuzzyMatrix) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i != row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /*
    // 计算隶属度
    public double calculateMembership(double x, double a, double b, double c) {
        if (x <= a) {
            return 0;
        } else if (a < x && x <= b) {
            return (x - a) / (b - a);
        } else if (b < x && x <= c) {
            return (c - x) / (c - b);
        } else {
            return 0;
        }
    }
     */
}