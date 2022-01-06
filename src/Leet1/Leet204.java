package Leet1;

import java.util.Arrays;

/**
 * 计算质数
 */
public class Leet204 {

    public static void main(String[] args) {
//        System.out.println( count1(10) );
        System.out.println( count2(10) );
    }

    /**
     * 思路：假设有非质数k可表示位 m*n 则，m，n必然小于 n
     * 只需计算 m < k^(0.5) 的情况即可
     * @param n
     * @return
     */
    private static int count1( int n ) {
        int result = 0;
        if( n <= 1 ) { return result; }
        boolean flag = false;
        for (int i = 2; i < n; i++) {
            for (int j = 2; j*j <= i; j++) {
                if( i % j == 0 ) {
                    flag = true;
                    break;
                }
            }
            if( !flag ) {
                result++;
            }
            flag = false;
        }
        return result;
    }

    /**
     * 思路：以空间换时间的思路,并且是往前走的
     * 关键点：i <= Math.sqrt(n) 和 int j = i*i; j < n; j+=i
     * @param n
     * @return
     */
    private static int count2( int n ) {
        int result = 0;
        if( n <= 1 ) { return result; }

        boolean[] notPrime = new boolean[n];
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if( !notPrime[i] ) {
                for (int j = i*i; j < n; j+=i) {
                    notPrime[j] = true;
                }
            }
        }
        for (int i = 2; i < notPrime.length; i++) {
            if( !notPrime[i] ) {
                result++;
            }
        }
        return result;
    }
}
