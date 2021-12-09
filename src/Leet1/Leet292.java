package Leet1;

/*
    Nim游戏
 */
public class Leet292 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println( canWin1(40) );
        System.out.println(System.currentTimeMillis());
        System.out.println( canWin2(40) );
        System.out.println(System.currentTimeMillis());
    }

    public static boolean canWin1( int count ) {
        if( count <= 3 ) {
            return true;
        }
        return !(canWin1( count - 1 ) && canWin1( count - 2 ) && canWin1( count - 3 ));
    }

    public static boolean canWin2( int count ) {
        boolean[] dp = new boolean[count + 1];
        dp[1] = dp[2] = dp[3] = true;
        for (int i = 4; i <= count; i++) {
            dp[i] = !(dp[i-1] && dp[i-2] && dp[i-3] );
        }
        return dp[count];
    }

}
