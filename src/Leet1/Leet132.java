package Leet1;

/*
    分割回文串II
    返回最少的分割次数
    至少需要分割一次
 */
public class Leet132 {

    public static void main(String[] args) {

        System.out.println( minDivideTimes( "a" ) );

    }


    /*
        动态规划
        状态表达式：
        dp[i][j] =
         min{ dp[i][k] + dp[k+1][j] } + 1  满足i<=k<j时
         0   满足i == j 时 或 i<j 并且 str.subtring(i,j+1) 是 回文串
         i > j 不考虑
     */
    public static int minDivideTimes( String str ) {

        char[] chars = str.toCharArray();
        int[][] dp = new int[chars.length][chars.length];
        for (int i = 1; i < chars.length; i++) {
            for (int j = 0; j < chars.length - i; j++) {
                int l = i + j;
                if( Leet131.isPalindrome( str.substring( j,l+1 ) ) ) {
                    dp[j][l] = 0;
                }else {
                    dp[j][l] = Integer.MAX_VALUE;
                    for (int k = j; k < l; k++) {
                        dp[j][l] = Math.min( dp[j][k] + dp[k+1][l],dp[j][l] );
                    }
                    dp[j][l]++; // 最后加一
                }
            }
        }
        //一次也没有分割
        if( dp[0][chars.length-1] == 0 ) {
            int min = dp[0][0];
            for (int i = 0; i < chars.length - 1; i++) {
                min = Math.min( min,dp[0][i] + dp[i+1][chars.length-1] );
            }
            return min + 1;
        }
        //至少分割了一次
        return dp[0][chars.length-1];
    }
}
