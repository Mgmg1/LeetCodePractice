package Leet1;

import java.lang.reflect.Array;
import java.util.*;

/*
    分割字符串
 */
public class Leet131 {

    public static void main(String[] args) {
        LinkedList<List<String>> xxxx = divideStr("aab");
        System.out.println( xxxx );
    }

    public static LinkedList<List<String>> divideStr( String str ) {

        if( str == null ) return new LinkedList<>();;

        LinkedList<List<String>>[] dp = (LinkedList<List<String>>[]) Array.newInstance(LinkedList.class, str.length());
        dp[0] = new LinkedList<>();
        LinkedList<String> strings = new LinkedList<>();
        strings.add( str.substring(0,1) );
        dp[0].add( strings );

        for (int i = 1; i < str.length(); i++) {
            dp[i] = new LinkedList<>();
            for (int j = i; j >= 0 ; j--) {
                if( isPalindrome( str.substring( j,i+1 ) ) ){
                    if( j == 0  ) {
                        //因为至少需要分割一次，所以最后要去掉串本身是回文串的情况
                        //不可以和 条件 j==0 合并,否则会有空指针异常
                        if( i + 1 != str.length() ) {
                            LinkedList<String> list = new LinkedList<>();
                            list.add( str.substring( j,i+1 ) );
                            dp[i].add(list );
                        }
                    }else {
                        for (List<String> list :
                                dp[j-1]) {
                            LinkedList<String> clone = (LinkedList<String>) ((LinkedList<String>) list).clone();
                            clone.add( str.substring( j,i+1 ) );
                            dp[i].add( clone );
                        }
                    }
                }
            }
        }

        return dp[str.length()-1];
    }

    public static boolean isPalindrome( String str ) {

        int i = 0,j = str.length() - 1;
        while ( i <= j ) {
            if( str.charAt( i ) != str.charAt( j ) ) return false;
            i++;
            j--;
        }
        return true;
    }
}
