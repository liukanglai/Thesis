package com.cup.thesis;

import java.util.Arrays;

public class AHP1 {
    public static void main(String[] args) {
        double[][] judgementMatrix = {
                {1, 2, 5, 7, 1, 2, 6, 4},
                {1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2},
                {1 / 5.0, 1 / 3.0, 1, 3, 1 / 5.0, 1 / 3.0, 2, 1},
                {1 / 7.0, 1 / 5.0, 1 / 3.0, 1, 1 / 7.0, 1 / 5.0, 1 / 3.0, 1 / 2.0},
                {1, 3, 5, 7, 1, 3, 6, 4},
                {1 / 2.0, 1, 3, 5, 1 / 3.0, 1, 4, 2},
                {1 / 6.0, 1 / 4.0, 1 / 2.0, 3, 1 / 6.0, 1 / 4.0, 1, 1 / 2.0},
                {1 / 4.0, 1 / 2.0, 1, 2, 1 / 4.0, 1 / 2.0, 2, 1}
        };

        double[] weights = calculateWeights(judgementMatrix);
        //System.out.println("权重: " + Arrays.toString(weights));
    }

    /**
     * 计算判断矩阵的权重向量
     *
     * @param judgementMatrix 判断矩阵
     * @return 权重向量
     */
    public static double[] calculateWeights(double[][] judgementMatrix) {
        int n = judgementMatrix.length;
        double[] weights = new double[n];

        // 计算每个决策因素的加权平均值
        double[] rowSum = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += judgementMatrix[i][j];
            }
            rowSum[i] = sum;
        }

        // 计算每个决策因素的偏离程度
        double[][] deviationMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deviationMatrix[i][j] = judgementMatrix[i][j] / rowSum[i];
            }
        }
        // 对每列进行加权平均，得到每个决策因素的权重
        double[] colSum = new double[n];
        for (int j = 0; j < n; j++) {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += deviationMatrix[i][j];
            }
            colSum[j] = sum / n;
        }

        // 对每个决策因 素的偏离程度进行归一化，得到权重向量
        double deviationSum = 0;
        for (int i = 0; i < n; i++) {
            double deviationProduct = 1;
            for (int j = 0; j < n; j++) {
                deviationProduct *= deviationMatrix[i][j];
            }
            weights[i] = Math.pow(deviationProduct, 1.0 / n) / colSum[i];
            deviationSum += weights[i];
        }
        // 归一化权重向量
        for (int i = 0; i < n; i++) {
            weights[i] /= deviationSum;
        }

        return weights;
    }
}

/*
public class MultilevelEvaluationSystem {
    public static void main(String[] args) {
        // 层次分析法判断矩阵
        double[][] topLevelJudgementMatrix = {
                {1, 3},
                {1 / 3.0, 1}
        };

        double[][][] subLevelJudgementMatrices = {
                {
                        {1, 2},
                        {1 / 2.0, 1}
                },
                {
                        {1, 3, 5},
                        {1 / 3.0, 1, 3},
                        {1 / 5.0, 1 / 3.0, 1}
                },
                {
                        {1, 1 / 2.0},
                        {2, 1}
                },
                {
                        {1, 4, 7},
                        {1 / 4.0, 1, 4},
                        {1 / 7.0, 1 / 4.0, 1}
                }
        };

        double[] topLevelWeights = calculateWeights(topLevelJudgementMatrix);

        double[][] subLevelWeights = new double[subLevelJudgementMatrices.length][];
        for (int i = 0; i < subLevelJudgementMatrices.length; i++) {
            subLevelWeights[i] = calculateWeights(subLevelJudgementMatrices[i]);
        }

        // 计算总权重
        double[] overallWeights = new double[8];
        overallWeights[0] = topLevelWeights[0] * subLevelWeights[0][0];
        overallWeights[1] = topLevelWeights[0] * subLevelWeights[0][1];
        overallWeights[2] = topLevelWeights[1] * subLevelWeights[1][0];
        overallWeights[3] = topLevelWeights[1] * subLevelWeights[1][1];
        overallWeights[4] = topLevelWeights[1] * subLevelWeights[1][2];
        overallWeights[5] = topLevelWeights[2] * subLevelWeights[2][0];
        overallWeights[6] = topLevelWeights[2] * subLevelWeights[2][1];
        overallWeights[7] = topLevelWeights[3] * subLevelWeights[3][0];

    }

}


 */
