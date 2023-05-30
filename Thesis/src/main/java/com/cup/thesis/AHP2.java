package com.cup.thesis;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class AHP2 {

    /*
        RealMatrix judgmentMatrix = MatrixUtils.createRealMatrix(judgementMatrix);
        double[] weights = new double[judgmentMatrix.getColumnDimension()];

        for (int i = 0; i < judgmentMatrix.getColumnDimension(); i++) {
            double columnSum = 0;
            for (int j = 0; j < judgmentMatrix.getRowDimension(); j++) {
                columnSum += judgmentMatrix.getEntry(j, i);
            }
            weights[i] = columnSum / judgmentMatrix.getRowDimension();
        }
        System.out.println(Arrays.toString(weights));

     */
    public static void main(String[] args) {

        // Create a real matrix with two rows and three columns, using a factory
        // method that selects the implementation class for us.
        double[][] matrixData = {   {1d,    1d,     1d,     4d,     1d,     1d/2d},
                {1d,    1d,     2d,     4d,     1d,     1d/2d},
                {1d,    1d/2d,  1d,     5d,     3d,     1d/2d },
                {1d/4d, 1d/4d,  1d/5d,  1d,     1d/3d,  1d/3d },
                {1d,   1d,     1d/3d,  3d,     1d,     1d },
                {2d,    2d,     2d,     3d,     3d,     1d },
        };
        RealMatrix m = MatrixUtils.createRealMatrix(matrixData);



        // One more with three rows, two columns, this time instantiating the
        // RealMatrix implementation class directly.
        double[][] matrixData2 = {{1d, 2d}, {2d, 5d}, {1d, 7d}};
        RealMatrix n = new Array2DRowRealMatrix(matrixData2);

        // Note: The constructor copies  the input double[][] array in both cases.
        // Now multiply m by n
//        RealMatrix p = m.multiply(n);
//        System.out.println(p.getRowDimension());    // 2
//        System.out.println(p.getColumnDimension()); // 2
//
//        // Invert p, using LU decomposition
//        RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();

        RealMatrix D = new EigenDecomposition(m).getD();
        RealMatrix V = new EigenDecomposition(m).getV();

        for(int i=0; i<D.getRowDimension();i++)
        {
            System.out.println(D.getRowMatrix(i));
        }

        for(int i=0; i<V.getRowDimension();i++)
        {
            System.out.println(V.getRowMatrix(i));
        }

        // 特征值
        double maxLamda;
        int columIndexForMaxLamda=0;
        maxLamda=D.getEntry(0,0);

        for(int i =0, j=0; i<D.getRowDimension()&&j<D.getColumnDimension();i++,j=i)
        {
            double lamda = D.getEntry(i,j);
            if(maxLamda<lamda)
            {
                maxLamda=lamda;
                columIndexForMaxLamda = j;
            }
            System.out.println(lamda);
        }

        // 输出尚未做归一化 w1, w2, w3, w4, w5, w6 ，
        System.out.println(V.getColumnMatrix(columIndexForMaxLamda));

    }
}