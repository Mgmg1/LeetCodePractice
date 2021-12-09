package Leet1;

/*
  不同的搜索树
  思路：             n-1
    递归: count(n) = Σ  ( count(i) * count(n-1-i) )
                   i=0
                                  n-1
    动态规划: 根据递归定义得到 dp[n] =  Σ (dp[i] * dp[n-1-i] )
                                  i=0
 */
public class Leet96 {

    public static void main(String[] args) {

//        System.out.println( recursiveDifferentSearchTreeCount( 3 ) );
        System.out.println( dpDifferentSearchTreeCount( 3 ) );

    }


    public static int recursiveDifferentSearchTreeCount( int n ) {

        if( n == 0 ) {
            return 1;
        }
//        if( n == 2 ) {
//            return 2;
//        }
        int count = 0;
        for (int i = 0; i <= n-1; i++) {
            count += recursiveDifferentSearchTreeCount( i ) * recursiveDifferentSearchTreeCount( n- 1 -i );
        }
        return count;
    }


    /*
        可以再优化一次，因为二叉是对称的。
     */
    public static int dpDifferentSearchTreeCount( int n ) {
        if( n <= 1 ) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            for (int j = 0; j <= i - 1 ; j++) {
               dp[i]+=dp[j] * dp[ i - 1 - j ];
            }
        }
        return dp[n];
    }

}
