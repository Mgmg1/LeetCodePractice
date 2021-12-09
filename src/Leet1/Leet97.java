package Leet1;

import java.util.Arrays;

/*
    交错字符串
    思路：
    递归：遇到str[i] 同时等于  l[i] 和 r[j] 时，使用递归。若都不相等，直接返回false
        若只有一个相等，则这是唯一的匹配的可能，开始下一步循环
        当两个条件  A&&B 时一种写法， A&&!B 一种写法，  !A&&B 一种写法， !A&&!B 一种写法，则
        条件顺序为   A&&B =>  A&&!B => !A&&B =>(else) !A&&!B 条件逐步放宽
        或者反过来   !A&&!B =>  A&&!B => !A&&B =>(else) A&&B

        常见条件顺序有为  ( A || B ) =>(else)  !A&&!B
                       A && B => A||B =>(else) !A&&!B
                       A&&B =>  A&&!B => !A&&B =>(else) !A&&!B


 */
public class Leet97 {

    public static void main(String[] args) {

//        char[] l = "aabcc".toCharArray();
//        char[] r = "dbbca".toCharArray();
//        char[] str = "aadbbcbcac".toCharArray();
        char[] l = "aabcc".toCharArray();
        char[] r = "dbbca".toCharArray();
        char[] str = "aadbbbbaccc".toCharArray();
        System.out.println( isMatch(l,r,str,0,0,0) );
        System.out.println( dpIsMatch(l,r,str) );

    }

    /*
        条件范围由小到大 l[i] == str[m] && r[j] == str[m] => || l[i] == str[m] || r[j] == str[m]
     */
    public static boolean isMatch(char[] l,char[] r,char[] str,
                                  int i,int j,int k) {
        for (int m = k; m < str.length; m++) {
            if( i < l.length && j < r.length && l[i] == str[m] && r[j] == str[m]) {
                return isMatch(l, r, str, i+1, j, m + 1) || isMatch(l, r, str, i, j + 1, m + 1);
            }
            if(i < l.length && l[i] == str[m]  ) {
                i++;
            }else if( j < r.length && r[j] == str[m] ) {
                j++;
            }else {
                return false;
            }
        }
        return true;
    }

    /*
        动态规划
        状态转移： dp[i][j] = dp[i-1][j] || dp[i][j-1]
     */
    public static boolean dpIsMatch( char[] l,char[] r,char[] str ) {

        if( l == null || l.length == 0 ) {
            return Arrays.equals( r,str );
        }
        if( r == null || r.length == 0 ) {
            return Arrays.equals( l,str );
        }

        boolean[][] dp = new boolean[l.length][r.length];
        dp[0][0] = l[0] == str[0] ;
        for (int i = 1; i < r.length; i++) {
            dp[0][i] =  dp[0][i-1] && str[i] == r[i];
            if( dp[0][i]  ) {
                break;
            }
        }
        for (int i = 1; i < l.length; i++) {
            dp[i][0] =  dp[i-1][0] && str[i] == l[i];
            if( dp[i][0] ) {
                //boolean默认数组元素为 false
                break;
            }
        }
        for (int i = 1; i < l.length; i++) {
            for (int j = 1; j < r.length; j++) {
                dp[i][j] = ( dp[i-1][j] && str[i + j - 1] == l[i] ) || ( dp[i][j-1] && str[i + j - 1] == r[j] ) ;
            }
        }
        return dp[l.length-1][r.length-1];
    }
}
