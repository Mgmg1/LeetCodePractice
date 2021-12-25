package Leet1;

/*
    阶乘后的0
    返回结果尾数中的0的个数（即 最尾部连续了的0的个数）
 */
public class Leet172 {

    public static void main(String[] args) {
        System.out.println( zeroCountInFactorial(10) );
    }

    /*
        n比较大时，变量装不下，因此不该直接计算阶乘
        思路：计算阶乘中，因数中 5 的个数 和 2的个数，取最小值
     */
    public static int zeroCountInFactorial( int n ) {
        int twoCount = 0;
        int fiveCount = 0;
        for (int i = 1; i <= n; i++) {
            int j = i;
            while ( j != 0 && (j & 1) == 0 )  {
                twoCount++;
                j >>=1;
            }
            while ( j != 0 && ( j % 5 == 0 ) ) {
                fiveCount++;
                j %= 5;
            }
        }
        return Math.min( twoCount,fiveCount );
    }

}
