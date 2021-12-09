package Leet1;

import java.util.Arrays;

/*
    螺旋矩阵II
    本题和螺旋矩阵1相似，都需要按照螺旋方式遍历
 */
public class Leet59 {

    public static void main(String[] args) {
        int[][] ints = generateMatrix(10);
        for (int[] arr :
                ints) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static int[][] generateMatrix(int n) {


        int wBegin = 0, wEnd = n - 1;
        int hBegin = 0, hEnd = n - 1;

        int[][] matrix = new int[n][n];
        int count = 1;
        while (  wBegin<=wEnd && hBegin <= hEnd  ){
            for (int i = wBegin; i <= wEnd; i++) {
                matrix[hBegin][i] = count++;
            }
            for (int i = hBegin + 1; i <= hEnd; i++) {
                matrix[i][wEnd] = count++;
            }
            if(  hBegin != hEnd  ) {
                for (int i = wEnd - 1; i >= wBegin; i--) {
                    matrix[hEnd][i] = count++;
                }
            }
            if( wBegin != wEnd ) {
                for (int i = hEnd -1; i >= (hBegin + 1) ; i--) {
                    matrix[i][wBegin] = count++;
                }
            }
            wBegin++;
            hBegin++;
            wEnd--;
            hEnd--;
        }
        return matrix;
    }

}
