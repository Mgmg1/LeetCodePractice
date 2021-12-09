package Leet1;

/*
    爬楼梯
 */
public class Leet70 {

    public static void main(String[] args) {
        System.out.println(climbStairs1( 3 ));
        System.out.println(climbStairs2( 3 ));
    }


    //递归版
    public static int climbStairs1( int height ) {

        if( height == 1 ){
            return 1;
        }else if( height == 2 ) {
            return 2;
            //显然这是可以转动态规划的
        }else return climbStairs1( height -1 ) + climbStairs1( height - 2 );
    }

    //动态规划要求 新问题求解依赖子问题（即子问题不会因为新问题变量而改变），并且子问题返回一个值，
    //动态规划主要需要 一维数组 或者 二维数组
    //一维说明新问题依赖的子问题只受一个变量影响,二维说明新问题依赖的子问题受两个个变量影响
    public static int climbStairs2( int height ) {

        int[] dp = new int[height];
//        dp[height-2] = 1;
//        dp[height-3] = 2;   !! 下标  dp[i]  表示 从 高度i 到 hegiht 方式数 
        dp[height-1] = 1;
        dp[height-2] = 2;
        for (int i = height - 3; i >= 0 ; i--) {
            dp[i] = dp[i+1] + dp[i+2]  ;
        }
        return dp[0];
    }

}
