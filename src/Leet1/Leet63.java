package Leet1;

import java.util.Arrays;

/*
    不同路径II
    相比起1，本题引入了障碍物,也能做
    递归做法相似，动态规划做法也很相似。。。
 */
public class Leet63 {

    public static void main(String[] args) {
        int[][] map = new int[][]{
          new int[]{0,0,0},
          new int[]{0,1,0},
          new int[]{0,0,0},
        };
        System.out.println(uniquePaths(map,3,3));
    }

    public static int uniquePaths(int [][] map,int m,int n){
        int[][] dpArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            if( map[n-1][i] == 1 ){
                dpArr[n-1][i] = 0;
            }else {
                dpArr[n-1][i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if( map[i][m-1] == 1 ){
                dpArr[i][m-1] = 0;
            }else {
                dpArr[i][m-1] = 1;
            }
        }
        for (int i = n-2; i >= 0; i--) {
            for (int j = m-2; j >= 0; j--) {
                if( map[i][j] == 1 ){
                    dpArr[i][j] = 0;
                }else {
                    dpArr[i][j] = dpArr[i+1][j] + dpArr[i][j+1];
                }
            }
        }
        return dpArr[0][0];
    }

}
