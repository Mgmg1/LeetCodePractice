package Leet1;

import java.util.Arrays;

/*
    最长公共前缀  由两个字符串匹配的算法扩展得到
    另一个思路：找出最短的字符串，得到长度，在该长度范围内进行二分查找(不好！！)
 */
public class Leet14 {

    public static void main(String[] args) {
        char[][] strs = new char[][]{
          "fawfr".toCharArray(),
          "fawfafa".toCharArray(),
          "fawfafe".toCharArray(),
          "fawfafw".toCharArray(),
        };
        System.out.println( longestPrefix( strs ) );
    }

    private static String longestPrefix( char[][] strs ) {

        int k = 0;

        while ( true ) {
            if( strs[0].length == k ) break;
            int i = 1;
            char c = strs[0][k];
            for (; i < strs.length && k < strs[i].length && c == strs[i][k] ; i++);
            if( i == strs.length ) k++;
            else break;
        }
        char[] chars = Arrays.copyOfRange(strs[0], 0, k);
        return Arrays.toString(chars);
    }

}
