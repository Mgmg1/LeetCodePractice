package Leet1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
    买卖股票的最佳时机III
    配合Leet121的贪心算法思路 遍历两次
    时间复杂度为 O(n)
    原理：在更新i的同时，更新后序段的

    技巧：
    问题规模变大的趋势上， 连续找 最大最小值时可以时间复杂度为O(n)
    问题规模变小的趋势上， 找 最大最小值时可以时间复杂度为O(nlogn)。要配合堆
 */
public class Leet123 {

    public static void main(String[] args) {

        int[] arr = {3,3,5,0,0,3,1,4};
//        int[] arr = {7,6,4,3,1};
//        System.out.println( bestTimeForBuyStock(arr) );
//        System.out.println( bestTimeForBuyStock1(arr) );
//        System.out.println( bestTimeForBuyStock2(arr) );
//        System.out.println( bestTimeForBuyStock3(arr) );
        System.out.println( bestTimeForBuyStock4(arr) );

    }

    /*
        时间复杂度O(N) 空间复杂度O(N)
     */
    public static int bestTimeForBuyStock(int[] values ) {

        if( values == null || values.length <= 1) return 0;

        int min = values[0];
        int[] result = new int[values.length];
        for (int i = 1; i < values.length; i++) {
            result[i] = Math.max( values[i] - min,result[i-1] );
            min = Math.min( values[i],min );
        }

        if( values.length <= 3 ) return result[result.length - 1];

        int max = values[values.length-1];
        //初始值为 不买或者只买一次 的情况

        int maxProfit = result[result.length-1];
        for (int i = values.length - 2; i >= 2 ; i--) {
            maxProfit = Math.max(result[i-1] + max - values[i],maxProfit );
            max = Math.max( max,values[i] );
        }
        return maxProfit;
    }

    /*
        时间复杂度O(N^2) 空间复杂度O(1)
     */
    public static int bestTimeForBuyStock1(int[] values ) {

        if( values == null || values.length == 0 ) return 0;
        int maxProfit = 0;
        int leftProfit = 0,rightProfit = 0;
        for (int i = 0; i < values.length; i++) {
            int min = values[0];
            leftProfit = 0;
            for (int j = 0; j <= i; j++) {
                //只能放在里层,放在外层则表示为固定在第i天卖股票
                leftProfit = Math.max( leftProfit, values[j] - min );
                min = Math.min( min,values[j] );
            }
            int max = values[i];
            for (int j = i; j < values.length; j++) {
                max = Math.max( max,values[j] );
            }
            rightProfit = Math.max( 0, max - values[i]);
            maxProfit = Math.max( maxProfit,leftProfit + rightProfit );
        }
        return maxProfit;
    }

    /*
        动态规划雏形:
            f(0,i) = value[i] - min{value[j]}
            f(1,i) = max{ f(1,j-1), value[i] - min{ value[j] - f(0,j-1) } }
            0 < j <= i
        时间复杂度O(kN^2)  空间复杂度O(kN)
     */
    public static int bestTimeForBuyStock2(int[] values ) {
        if( values == null || values.length == 0 ) return 0;
        int[][] dp = new int[2][values.length];

        for (int k = 0; k < 2; k++) {
            for (int i = 1 ; i < values.length; i++) {
                int min = values[0];
                // j = 0 时，则dp[k][0] = 0
                for (int j = 1; j <= i; j++) {
                    if( k == 0 ) {
                        //挑最小
                        min = Math.min( min,values[j] );
                        dp[k][i] = values[i] - min;
                    }else {
                        min = Math.min(min,values[j] - dp[k-1][j-1]);
                        dp[k][i] = Math.max( dp[k][i-1],values[i] - min );
                    }
                }
            }
        }
        return dp[1][values.length- 1];
    }

    /*
        动态规划改进:min可以被合并，减少循环
        时间复杂度O(kN)  空间复杂度O(kN)
     */
    public static int bestTimeForBuyStock3(int[] values ) {
        if( values == null || values.length == 0 ) return 0;

        int[][] dp = new int[3][values.length];  // 2 -> 3 ,i = 0 代表第0笔交易

        for (int k = 1; k < 3; k++) {
            int min = values[0];
            for (int i = 1 ; i < values.length; i++) {
                dp[k][i] = Math.max( dp[k][i-1],values[i] - min );
                min = Math.min( min,values[i] - dp[k-1][i-1] );
            }
        }
        return dp[2][values.length-1];
    }

    /*
        动态规划3的另一种形式，交换内外循环
        需要为 min 设置k长的数组（竖向存储min）
     */
    public static int bestTimeForBuyStock4(int[] values ) {
        if( values == null || values.length == 0 ) return 0;

        int[] dp = new int[3];  // 2 -> 3 ,i = 0 代表第0笔交易
        int[] mins = new int[3];
        Arrays.fill(mins,values[0]);

        for (int i = 1; i < values.length; i++) {
            //mins 依赖 k-1 因此 k从大到小循环
            for (int k = 2; k > 0; k--) {
                dp[k] = Math.max( dp[k], values[i] - mins[k]);
                mins[k] = Math.min( mins[k],values[i] - dp[k-1] );
            }
        }
       return dp[dp.length-1];
    }


//    public static int bestTimeForBuyStock4(int[] values[] ) {
//
//    }
}
