package Leet1;

/*
    最小路径和
    动态规划基础题
 */
public class Leet64 {
    public static void main(String[] args) {
        int[][] ints = new int[][]{
                new int[]{1, 3, 1},
                new int[]{1, 5, 1},
                new int[]{4, 2, 1}
        };
        System.out.println(minPathSum(ints, 3, 3));
    }

    //动态规划问题
    public static int minPathSum(int[][] map, int m, int n) {

        int[][] dpArr = new int[n][m];
        dpArr[n - 1][m - 1] = map[n - 1][m - 1];
        for (int i = m - 2; i >= 0; i--) {
            dpArr[n - 1][i] = map[n - 1][i] + dpArr[n - 1][i + 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            dpArr[i][m - 1] = map[i][m - 1] + dpArr[i + 1][m - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dpArr[i][j] = map[i][j] + Math.min(dpArr[i + 1][j], dpArr[i][j + 1]);
            }
        }
        return dpArr[0][0];
    }

}
