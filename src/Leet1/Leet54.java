package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    螺旋矩阵
 */
public class Leet54 {

    public static void main(String[] args) {
//        int [][] matrix = new int[][] {
//                {1,2,3},
//                {4,5,6},
//                {7,8,9}
//        };
//        int [][] matrix2 = new int[][] {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//        };
        int [][] matrix3 = new int[][] {
                {1,2}
        };
        List<Integer> result = spiralOrder(matrix3);
        for (int num: result
             ) {
            System.out.print(num);
            System.out.print(' ');
        }
    }

    //默认输入不为null
    public static List<Integer> spiralOrder(int [][] input){
        List<Integer> result = new LinkedList<>();
        int wbegin=0,wend=input[0].length - 1;
        int hbegin=0,hend=input.length - 1;

        while (  wbegin<= wend && hbegin <= hend ) {
            //转圈圈遍历
            for (int i = wbegin; i <= wend; i++) {
                result.add(input[wbegin][i]);
            }
            for (int i = hbegin + 1 ; i <= hend; i++) {
                result.add(input[i][wend]);
            }
            //单行时，避免重复遍历
            if ( hbegin != hend ) {
                for (int i = wend - 1 ; i >= wbegin ; i--) {
                    result.add(input[hend][i]);
                }
                for (int i = hend - 1; i >= hbegin + 1 ; i--) {
                    result.add(input[i][wbegin]);
                }
            }
            wbegin++;
            wend--;
            hbegin++;
            hend--;
        }

    return result;
    }

}
