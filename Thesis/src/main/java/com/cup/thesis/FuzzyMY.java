package com.cup.thesis;
public class FuzzyMY {
    public static void main(String[] args) {
        //double[] fuzzyInput = {0.2, 0.5, 0.8, 0.4, 0.6, 0.9, 0.3, 0.7};
        double[] fuzzyInput = {16000.0, 17000.0, 26000.0, 93000.0, 138000.0, 151000.0, 371000.0, 585000.0};
        int levels = 3; // 假设有3个评价等级
        double[][] fuzzyMatrix = buildFuzzyMatrix(fuzzyInput, levels);
        //printFuzzyMatrix(fuzzyMatrix);
    }
    public static double[][] buildFuzzyMatrix(double[] fuzzyInput, int levels) {
        int n = fuzzyInput.length;
        double[][] fuzzyMatrix = new double[n][levels];

        for (int i = 0; i < n; i++) {
            double[] fuzzyValues = calculateFuzzyValuesForLevel(fuzzyInput[i], levels);
            for (int j = 0; j < levels; j++) {
                fuzzyMatrix[i][j] = fuzzyValues[j];
            }
        }

        return fuzzyMatrix;
    }

    public static double[] calculateFuzzyValuesForLevel(double input, int levels) {
        double[] fuzzyValues = new double[levels];
        double sum = 0;

        // 根据具体需求和评价等级，为每个指标分配模糊值
        // 这里只是一个示例，您可以根据实际情况自定义分配方法
        for (int i = 0; i < levels; i++) {
            double levelValue = (double) (i + 1) / levels;
            fuzzyValues[i] = membershipFunction(input, levelValue);
            sum += fuzzyValues[i];
        }

        // 标准化模糊值，使每行之和为1
        for (int i = 0; i < levels; i++) {
            fuzzyValues[i] /= sum;
        }

        return fuzzyValues;
    }
    public static double membershipFunction(double input, double levelValue) {
        double maxInput = 600000.0;
        double minInput = 10000.0;
        double range = maxInput - minInput;
        double normalizedInput = (input - minInput) / range;

        // 使用线性隶属度函数
        return Math.max(1.0 - Math.abs(normalizedInput - levelValue), 0.0);
    }
    public static double[] calculateFuzzyScores(double[][] fuzzyMatrix, double[] weights) {
        int m = fuzzyMatrix.length;
        int n = fuzzyMatrix[0].length;
        double[] scores = new double[m];

        // 对每个决策选项进行加权平均，得到模糊得分
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += fuzzyMatrix[i][j] * weights[j];
            }
            scores[i] = sum;
        }

        return scores;
    }
    public static void printFuzzyMatrix(double[][] fuzzyMatrix){
        System.out.println("模糊评价矩阵");
        System.out.println("好       中       差");
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
    // 构造规则知识库
    Map<String, Double[]> rules = new HashMap<>();
        rules.put("优秀", new Double[]{0.8, 0.8, 0.8, 0.9, 0.9, 0.8, 0.3, 0.2});
        rules.put("良好", new Double[]{0.6, 0.6, 0.6, 0.7, 0.7, 0.6, 0.4, 0.3});
        rules.put("一般", new Double[]{0.4, 0.4, 0.4, 0.5, 0.5, 0.4, 0.6, 0.5});
        rules.put("差", new Double[]{0.2, 0.2, 0.2, 0.3, 0.3, 0.2, 0.8, 0.7});
        rules.put("很差", new Double[]{0.1, 0.1, 0.1, 0.2, 0.2, 0.1, 0.9, 0.8});

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

