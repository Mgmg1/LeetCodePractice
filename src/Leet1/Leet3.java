package Leet1;

import java.util.Arrays;

/*
    无重复字符的最长字串
 */
public class Leet3 {

    public static void main(String[] args) {
//        System.out.println( Arrays.toString( notRepeatLongestStr( "abcabcbb".toCharArray() ) ) );
        System.out.println( Arrays.toString( notRepeatLongestStr( "bbbbb".toCharArray() ) ) );
    }

    /*
        涉及字符串操作，尝试使用char，而不用String
     */
    public static char[] notRepeatLongestStr( char[] str ) {

        int[] map = new int['z' + 1];
        Arrays.fill(map,-1);
        int nowEnd = 0;
        int nowBegin = 0;
        int maxBegin = 0,maxEnd = 0;
        for (int i = 0; i < str.length; i++) {
            if( map[str[i]] != -1 ) {
                // 注意是从 map[str[i]] + 1 开始 ，
                // 例如 abca，由于 map[str[i]] 映射a字母，而 a字母重复，因此不能把a字母映射改为 -1，而是更新为重复的新的位置
                for (int j = map[str[i]] + 1; j < i; j++) {
                    map[str[j]] = -1;
                }
                map[str[i]] = i;
                nowBegin = i;
                nowEnd = i;
            }else {
                map[str[i]] = i;
                nowEnd = i;
                if( nowEnd - nowBegin > maxEnd - maxBegin ) {
                    maxBegin = nowBegin;
                    maxEnd = nowEnd;
                }
            }
        }
        char[] result = new char[maxEnd - maxBegin + 1];
        for (int i = maxBegin; i <= maxEnd ; i++) {
            result[i-maxBegin] = str[i];
        }
        return result;
    }

}
