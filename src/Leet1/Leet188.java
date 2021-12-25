package Leet1;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机IV
 * 动态规划
 * 状态转移式 dp[i][j] = max { dp[i][j-1], dp[i-1][j], max{ dp[i-1][k]  nums[j] - nums[k] | k>= 0 && k< j  } }
 * 本题的难点是动态规划中时间的优化思路：针对 max{ dp[i-1][k] + nums[j] - nums[k] | k>= 0 && k< j  } 这一部分
 */
public class Leet188 {
    public static void main(String[] args) {
        int[] nums = { 3,2,6,5,0,3 };
//        int[] nums = { 2,4,1 };
        int k = 2;
//        System.out.println( buyAndSellStockBestOccasion1( nums,k ) );
//        System.out.println( buyAndSellStockBestOccasion2( nums,k ) );
        System.out.println( buyAndSellStockBestOccasion3( nums,k ) );
    }

    /**
     * 最一般的动态规划解法,没有进行优化
     * @param nums
     * @param k
     * @return
     */
    private static int buyAndSellStockBestOccasion1( int[] nums,int k ) {
        if( nums == null || nums.length <= 1 || k <= 0 ) { return 0; }

        int[][] dp = new int[k + 1][nums.length + 1];
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < nums.length + 1; j++) {
                int max = 0;
                //循环 l <= j 包含了 max{ dp[i-1][j], max{ dp[i-1][k] + nums[j] - nums[k] | k>= 0 && k< j }  }的情况
                for (int l = 1; l <= j; l++) {
                    max = Math.max( max,dp[i-1][l] + nums[j-1] - nums[l-1] );
                }
                dp[i][j] = Math.max( max, dp[i][j-1]);
            }
        }
        return dp[k][nums.length];
    }

    /**
     * 对buyAndSellStockBestOccasion1进行优化,
     * @param nums
     * @param k
     * @return
     */
    private static int buyAndSellStockBestOccasion2( int[] nums,int k ) {
        if( nums == null || nums.length <= 1 || k <= 0 ) { return 0; }
        int[] dp = new int[k + 1]; //
        int[] max = new int[k + 1];
        for (int i = 1; i < k + 1; i++) {
            max[i] = -nums[0];
        }
        for (int i = 2; i < nums.length + 1; i++) {
            int temp = dp[0];
            for (int j = 1; j < k + 1; j++) {
                max[j] = Math.max( max[j],temp - nums[i - 1] );
                temp = dp[j];
                dp[j] = Math.max( max[j] + nums[i-1],dp[j] );
            }
        }
        return dp[k];
    }

    /**
     * 相比buyAndSellStockBestOccasion2,改动了遍历方向，空间复杂度有所变化
     * @param nums
     * @param k
     * @return
     */
    private static int buyAndSellStockBestOccasion3( int[] nums,int k ) {
        if( nums == null || nums.length <= 1 || k <= 0 ) { return 0; }
        int[] dp = new int[nums.length + 1];

        int max;
        for (int i = 1; i < k + 1; i++) {
            max = -nums[0];
            int temp = dp[0]; //dp[0] 一直都是0
            for (int j = 2; j < nums.length + 1; j++) {
                max = Math.max( max,temp - nums[j - 1] );
                temp = dp[j];
                dp[j] = Math.max( dp[j-1],max + nums[j - 1] );
            }
        }
        return dp[nums.length];
    }
}
