package Leet1;

import java.util.Arrays;

/*
    扰乱字符串
 */
public class Leet87 {

    public static void main(String[] args) {

//        String s1 = "great";
//        String s2 = "rgeat";
        String s1 = "abcde";
        String s2 = "caebd";
        System.out.println( isMatch( s1,s2 ) );
    }

    /*
        递归的形式
        每个扰乱字符串都是递归定义的，即单词被拆分后，也是个扰乱字符串

        因此要递归地验证是否为扰乱字符串

        由于扰乱字符串的子节点可交换也可不交换，因此统计方式需要两种
        当统计个数count相同时，由于事先有整体字符串的统计个数相同，
        则当两边符合扰乱字符串的定义时，即可为true，一边不符合即false
     */
    public static boolean isMatch( String str,String disorder ) {
        if( disorder == null || str == null ) return true;
        if( disorder.length() == 0 && str.length() == 0 ) return true;
        if ( str.equals( disorder ) ) return true;

        int[] cCount1 = new int[26];
        int[] cCount2 = new int[26];
        //必须设置i < str.length() - 1,否则会分隔出空字符串，导致无限递归栈溢出
        //当分割到不可再分割时，也就是单个字符的情况，要求验证是否相等
        for (int i = 0; i < str.length() - 1; i++) {
            cCount1[str.charAt(i) - 'a']++;
            cCount2[disorder.charAt(i) - 'a']++;
            if( Arrays.equals( cCount1,cCount2 ) ) {
                if( !isMatch(str.substring(0, i + 1), disorder.substring(0, i + 1)) ) {
                    continue;
                }
                if( !isMatch(str.substring( i + 1 ), disorder.substring(i + 1)) ) {
                    continue;
                }
                return true;
            }
        }
        Arrays.fill(cCount1,0);
        Arrays.fill(cCount2,0);
        int j = str.length() - 1;
        for (int i = 0; i < str.length() - 1; i++) {
            cCount1[str.charAt(i) - 'a']++;
            cCount2[disorder.charAt(j) - 'a']++;
            if( Arrays.equals( cCount1,cCount2 ) ) {
                if( !isMatch(str.substring(0, i + 1), disorder.substring(j)) ) {
                    continue;
                }
                if( !isMatch(str.substring( i + 1 ), disorder.substring(0,j)) ) {
                    continue;
                }
                return true;
            }
            j--;
        }
        return false;
    }


}
