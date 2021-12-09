package Leet1;

import java.util.Arrays;

/*
    生命游戏
    用了复合态思路，比较经典，思路与 孤立岛屿 差不多。多了编码的部分
    编码设计： 根据需要的码的部分构造掩码， 使用 & 来筛选出信息
 */
public class Leet289 {

    public static void main(String[] args) {
        int[][] lives = {
                {1,0,1,1,0,1},
                {1,0,1,0,0,1},
                {1,0,1,0,0,1},
                {1,0,1,0,0,1}
        };
        lifeGame( lives );
        for (int[] ints:
             lives) {
            System.out.println(Arrays.toString( ints ));
        }
    }

    /*
        活细胞： 周围 活细胞数量 <2 或  >3 变死细胞
        死细胞:  周围恰好有三个活细胞，复活
     */
    public static void lifeGame(int[][] lives) {
        //方向数组 x,y
        int[][] dire = {
                {-1,0}, {-1,1}, {0,1}, {1,1},
                {1,0}, {1,-1}, {0,-1}, {-1,-1}
        };
        for (int i = 0; i < lives.length; i++) {
            for (int j = 0; j < lives[0].length; j++) {
                int x = i,y = j;
                int alive = 0;
                for (int k = 0; k < dire.length; k++) {
                    x+=dire[k][0];
                    y+=dire[k][1];
                    if( x < 0 || x >= lives.length || y < 0 || y >= lives[0].length ) {
                        x = i;
                        y = j;
                        continue;
                    }
                    //复合态筛选编码 x3x2x1:
                    // x3x2(改变后的状态)  00:未改变 01:死亡 10:存活
                    // x1（改变前的状态）   0: 死亡 1:存活
                    // 1 = 001b
                    if( (lives[x][y] & 1) == 1 ) {
                        alive++;
                    }
                    x = i;
                    y = j;
                }
                if( lives[i][j] == 1 && ( alive < 2 || alive > 3 ) ) {
                    lives[i][j] = 3;
                }else if( lives[i][j] == 0 && alive == 3 ) {
                    lives[i][j] = 4;
                }
            }
        }

        for (int i = 0; i < lives.length; i++) {
            for (int j = 0; j < lives[0].length; j++) {
                if( (lives[i][j] & 6 ) == 2 ) {
                    lives[i][j] = 0;
                }else if( (lives[i][j] & 6 ) == 4 ) {
                    lives[i][j] = 1;
                }
            }
        }
    }
}
