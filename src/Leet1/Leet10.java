package Leet1;

import java.util.Scanner;

/*
    正则表达式匹配
 */
public class Leet10 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        String str = scanner.next();
//        String reg = scanner.next();
        String str = "aeddffr";
        String reg = "a*.*df*r";
//        System.out.println( "is match " +reg+": "+isRegMatch("",reg) );
        System.out.println( "is match " +reg+": "+dpIsRegMatch(str,reg) );
    }

    /*
        递归的方式递归
        需要考虑输入的多个可能性：单个输入考虑两种，两个输入考虑四种
        过于复杂
        使用substring，递归则不需要记录下标i
     */
    public static boolean isRegMatch( String str,String reg) {

        int j = 0;
//        for (int i = 0; i < reg.length() && j < str.length() ; i++) {  错误的
        for (int i = 0; i < reg.length() ; i++) {  //  防止 reg不为0而str为0时判错误的情况
            if( i == reg.length() - 1 ) {
                return j == str.length() - 1 && ( str.charAt(j) == reg.charAt( i ) || reg.charAt(i) == '.' ) ;
            }
            if( reg.charAt( i + 1 ) == '*' ) {
                if( isRegMatch( str.substring(j),reg.substring(i + 2)) ) return true;
                while ( j < str.length() &&  ( str.charAt(j) == reg.charAt( i ) || reg.charAt(i) == '.' ) ) {
                    j++;
                    if( isRegMatch( str.substring(j),reg.substring(i + 2) ) ) return true;
                }
                return false;
            }
            //防止reg不为0而str为0 产生越界
            if( j == str.length() ) return false;
            if( str.charAt(j) == reg.charAt( i ) || reg.charAt(i) == '.' ) {
                j++;
                //这里不需要 i++
                if( j == str.length() ) return false;
            }else {
                return false;
            }
        }
        //只要 reg.length（） 不为 0，则不会跳出循环,必定在循环中进行返回
        return  str.length() == 0;
    }


    /*
        动态规划考虑的点：
        输入为空，使得dp数组无法建立，需提前处理并返回
        dp初始化：假设
        dp循环，考虑 (i-1,j-1),(i-1,j) (i,j-1)   和 (i,j)的关系
        正则中*考虑的是 (i-1,j-1) 和 (i,j)，因为若 XXXXa* 匹配XXXXa则必有 XXXX匹配XXXX
        大部分dp考虑的是 (i-1,j-1) 和 (i,j);   (i-1,j) (i,j-1) 作为补充
     */
    public static boolean dpIsRegMatch( String str,String reg ) {
        if( reg.length() == 0 ) {
            return str.length() == 0;
        }
        if( str.length() == 0 ) {
            for (int i = 0; i < reg.length(); i+=2) {
                if(!( i + 1 < reg.length() && reg.charAt(i+1) == '*' ) ) return false;
            }
            return true;
        }
        //由于是动态规划，必须保证有str，和reg的长度都不为0
        boolean[][] dp= new boolean[reg.length()][str.length()];
        dp[0][0] = reg.charAt(0) == str.charAt(0);
        //第一个肯定不会是*,因此从1开始   patternCount检查reg前面是否是 x*x*...模式
        int patternCount = 1;
        for( int i = 0;i < reg.length(); i++ ) {
            if( reg.charAt(i) == '*' ) {
                patternCount++;
//                dp[i][0] = patternCount == 1 ? reg.charAt( i - 1 ) == str.charAt(0) : false ;
                dp[i][0] =  ( patternCount == 1 && ( reg.charAt(i - 1) == str.charAt(0) || reg.charAt(i-1) == '.' )  );
            }else {
                patternCount--;
                dp[i][0] = patternCount == 0 && ( reg.charAt(i) == str.charAt(0) || reg.charAt(i) == '.' );
            }
        }
        for (int i = 1; i < reg.length(); i++) {
            for (int j = 1; j < str.length(); j++) {
                if( reg.charAt(i) == '*' ) {
                    if( i - 2 < 0 || dp[i - 2][j - 1] ) {
                        //假如进入过循环，让j-1，否则不用
                        boolean isJPlus = false;
                        //凡有 i++,j++ 条件  和  循环  都应该考虑是否有 数组越界
                        while ( j < str.length() && ( reg.charAt( i - 1 ) == str.charAt( j ) || reg.charAt(i - 1) == '.' ) ) {
                            dp[i][j] = true;
                            isJPlus = true;
                            j++;
                        }
                        if ( isJPlus ) j--;
                    }else {
                        dp[i][j] = false;
                    }
                }else {
                   dp[i][j] = ( str.charAt(j) == reg.charAt(i) || reg.charAt(i) == '.' ) && dp[i-1][j-1] ;
                }
            }
        }
        return dp[reg.length()-1][str.length()-1];
    }
}
