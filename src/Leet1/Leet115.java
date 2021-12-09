package Leet1;

/*
    115不同子系列
    动态规划
    基本上是考虑 dp[i-1][j-1]  dp[i-1][j]  dp[i][j-1]  和  dp[i][j]的关系
    在 i 方向 或者 j 方向上 想象为减少问题规模，然后再思考和dp[i][j]的关系
    对于字符串，dp数组的 0行 和 0列 的逻辑意义为空串，最好进行引入
 */
public class Leet115 {

    public static void main(String[] args) {

        String s = "rabbbit";
        String t = "rabit";

        System.out.println( dp(s,t) );
    }


    /*
        @param s 字符串
        @param t 字符串序列
        //动态规划
     */
    public static int dp(String s,String t ) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length() ; j++) {
                dp[i][j] = (s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : 0) + dp[i - 1][j];
            }
        }

        return dp[s.length()][t.length()];
    }


}
