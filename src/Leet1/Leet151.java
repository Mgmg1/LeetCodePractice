package Leet1;

import java.util.Arrays;

/*
    翻转字符串中的单词
    思路：
        翻两次： 先完全翻转，再翻转单词
        注意要处理多余的空格
 */
public class Leet151 {

    public static void main(String[] args) {
        System.out.println( reverseWord( "    the sky " ) );
    }

    private static String reverseWord( String str ) {
        if( str == null || str.length() == 0 ){ return ""; }

        //建议使用用c的方式来处理该问题，减少使用既有的api
        char[] chars = str.toCharArray();
        int i = 0,j = 0;
        //去除多余的空格
        while ( j < chars.length ) {
            //先去除空格
            while ( j < chars.length && chars[j] == ' ' ) {
                j++;
            }
            while ( j < chars.length && chars[j] != ' ' ) {
                chars[i++] = chars[j];
                chars[j++] = ' '; //置为空，否则会被重复复制！！
            }
            //补充一个空格，要注意原字符串长度不够填充的情况
            if( i < chars.length ) {
                chars[i++] = ' ';
            }
        }
        //去掉尾部多余的空格
        while ( chars[i - 1] == ' ' ) {
            i--;
        }
        int length = i;
        //第一次翻转
        i = 0;
        j = length / 2;
        char temp; //注意使用前必须初始化
        while ( i < j ) {
            temp = chars[i];
            chars[i] = chars[length - 1 - i];
            chars[length - 1 - i] = temp;
            i++;
        }
        //第二次翻转
        for (i = 0; i <length ; ) {
            j = i;
            while ( j < length && chars[j] != ' '  ) {
                j++;
            }

            //进行双向遍历时，使用两个指针比单个指针配合length方便,也不容易出错！！！

//  错误的使用！！！
//            int k = i,length2 = j-i;
//            while ( k < i + length2 / 2 ) {
//                temp = chars[k];
//                chars[k] = chars[i + length2 - 1 - k ];
//                chars[i + length2 - 1 - k] = temp;
//                k++;
//            }
            //两个指针
            int k = i,l = j - 1;
            while ( k < l ) {
                temp = chars[k];
                chars[k] = chars[l];
                chars[l] = temp;
                k++;
                l--;
            }
            //跳过一个空格
            i = j + 1;
        }
        return new String(Arrays.copyOfRange( chars,0,length ));
    }
}
