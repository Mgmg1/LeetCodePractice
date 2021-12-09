package Leet1;

import java.util.Arrays;

/*
    通配符匹配
    * 匹配零个或多个任意字符
    ? 匹配任意一个字符

    注意，匹配空串只有  通配符 可能
    一列匹配时，要注意前后都是空串的情况！！！
 */
public class Leet44 {

    public static void main(String[] args) {

        char[] str = "acdcb".toCharArray();
        char[] match = "**??*".toCharArray();
        System.out.println( recurWildcardMatch(str,match,0,0) );
        System.out.println( dpWildcardMatch(str,match) );
    }

    /*
        递归写法。 开头先写边界条件
        递归发生在出现通用匹配符的时候
        以及最后处理 i == str.length || j == match.length 的时候，相当于再次进入程序入口
        注意 * 循环需要让 k<=str.length ,因为 * 可以匹配 str[str.length - 1]!!!
     */
    public static boolean recurWildcardMatch( char[] str,char[] match,int i,int j ) {

        if( i == str.length ) {
            if( j == match.length ) return true;
            for (int k = j; k < match.length; k++) {
                if( match[j] != '*' ) return false;
            }
            return true;
        }
        //此时 i != str.length
        if( j == match.length ) return false;

        while ( i != str.length && j != match.length ) {
            if( match[j] == '*' ) {
                //这里是  k<=str.length!!!语义上, * 可以匹配到最后一个
                for (int k = i; k <= str.length; k++) {
                    if( recurWildcardMatch( str,match,k , j + 1 ) ) {
                        return true;
                    }
                }
                return false;
            }
            if( match[j] != '?' &&  match[j] != str[i] ) {
                return false;
            }
            i++;
            j++;
        }
        //利用代码块刚开始的代码的代码
        return recurWildcardMatch(str,match,i,j);
    }

    /*
        动态规划的匹配方式
        重点是
         第一行的初始化
        第一列的初始化     注意前后都是空串的情况！！！
        dp过程遇到  * 时，dp[][]如何决定？

     */
    public static boolean dpWildcardMatch( char[] str,char[] reg ) {

        boolean[][] dp = new boolean[reg.length][str.length];
        //初始化第一行
        if( reg[0] == '*' ) {
            Arrays.fill( dp[0],true );
        }else if( reg[0] == '?' || reg[0] == str[0] ) {
            dp[0][0] = true;
        }

        //初始化第一列
        int i = 0;
        //匹配空串
        for (; i < reg.length && reg[i] == '*' ; i++) {
            dp[i][0] = true;
        }
        //匹配唯一的 可比较字符
        if( i < reg.length && ( reg[0] == '?' || reg[i] == str[0] ) ) dp[i++][0] =true;
        //匹配空串
        for (; i < reg.length && reg[i] == '*' ; i++) {
            dp[i][0] = true;
        }


        for (int j = 1; j < reg.length; j++) {
            for (int k = 1; k < str.length; k++) {
                //关键。因为顺序是从左到右，且从k = 1，j = 1开始 若遇到 dp[j-1][k] || dp[j-1][k-1] == true 必定是第一次
                // 因此不必让 k 倒回去查看是否有 dp[j-1][k] == true
                if( reg[j] == '*' && ( dp[j-1][k] || dp[j-1][k-1] ) ) {
                    for (int l = k; l < str.length; l++) {
                        dp[j][l] = true;
                    }
                    break;
                }
                if( ( reg[j] == '?' || reg[j] == str[k] ) && dp[j-1][k-1] ) {
                    dp[j][k] = true;
                }
            }
        }
        return dp[reg.length-1][str.length-1];
    }

}
