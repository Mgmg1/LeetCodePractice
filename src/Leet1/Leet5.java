package Leet1;

import java.util.Arrays;

/*
    判断回文子串
 */
public class Leet5 {

    public static void main(String[] args) {

//        System.out.println( Arrays.toString( way1( "babad".toCharArray() ) ) );
        System.out.println( Arrays.toString( way3( "abababab".toCharArray() ) ) );
//        System.out.println( Arrays.toString( way2( "b".toCharArray() ) ) );
//        System.out.println( Arrays.toString( way2( "b".toCharArray() ) ) );

    }

    /*
        两次循环
     */
    public static char[] way1( char[] str ) {

        int maxL = 0, maxR = 0;
        for (int i = 0; i < str.length; i++) {
            int j = i , k =i;
            while( j >=0 && k <str.length &&  str[j] == str[k] ) {
                j--;
                k++;
            }
            j++;
            k--;
            if(  k - j > maxR - maxL) {
                maxR = k ;
                maxL = j ;
            }
            j = i ; k = i + 1;
            while( j >=0 && k <str.length && str[j] == str[k]) {
                j--;
                k++;
            }
            j++;
            k--;
            if(  k - j > maxR - maxL) {
                maxR = k ;
                maxL = j ;
            }
        }

        return Arrays.copyOfRange( str,maxL,maxR + 1);

    }

    /*
        动态规划,先逆转字符串
        用dp中的 dp[i][j], i = j 的部分(共享一个字符) 以及  i = j + 1部分（不共享字符）
     */
    public static char[] way2( char[] str ) {
        int[][] dp = new int[str.length][str.length];
        //记录从  start 往左的长度 max
        char[] str2 = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            str2[str.length - i -1 ] = str[i];
        }
        int max = 0;
        int start = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            dp[i][0] = str2[i] == str[0] ? 1 : 0;
            dp[0][i] = str[i] == str2[0] ? 1 : 0;
            if(  dp[i][0] > max ) {
                max = dp[i][0];
                start = 0;
            }
            if(  dp[0][i] > max ) {
                max = dp[0][i];
                start = i;
            }
        }
        for (int i = 1; i < str.length ; i++) {
            for (int j = 1; j < str2.length ; j++) {
                dp[i][j] = str[i] == str2[j] ? dp[i-1][j-1] + 1 : 0;
            }
        }
        for (int i = 0; i < str.length; i++) {
            if( dp[i][str.length - 1 - i] > max ) {
                start = str.length - 1 - i;
                max = dp[i][str.length - 1 - i];
            }
        }
        for (int i = 0; i < str.length - 1; i++) {
            if( dp[i][str.length - 2 - i] > max ) {
                start = str.length - 2 - i;
                max = dp[i][str.length - 2 - i];
            }
        }
        char[] chars = new char[max];
        for (int i = 0; i < max; i++) {
            chars[i] = str[start-i];
        }
        return chars;

    }

    /*
        曼切斯分算法。。。
        先插入 # 使得形成的回文串长度是奇数，则可以通过比较 right - left 来比较长度
        当 i点在 范围 l ~ r 中，并且对称点的回文串范围在l ~ r 之内时，可以直接赋值返回
        否则需要裁剪得到 k 和 j的起始点循环验证 或者 直接循环验证
     */
    public static char[] way3( char[] str ) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('#');
        for (char c :
                str) {
            stringBuilder.append(c);
            stringBuilder.append('#');
        }
        String nStr = stringBuilder.toString();
        int[] left = new int[nStr.length()];
        int[] right = new int[nStr.length()];
        int maxLeft = 0,maxRight = 0;
        int r = 0, l = 0, id = 0;
        for (int i = 0; i < nStr.length(); i++) {
            int k = i+1,j = i-1;
            if( i < r ) {
                int ii = r - i + l;
                //检查
                if( left[ii] > l ) {
                    left[i] = left[ii];
                    right[i] = right[ii];
                    continue;
                }
                k = r + 1;
                j = 2*i - r -1 ;
            }
            while ( j >= 0 && k < nStr.length() && nStr.charAt(k) == nStr.charAt(j) ) {
                k++;
                j--;
            }
            left[i] = ++j;
            right[i] = --k;
            if( k > r ) {
                r = k;
                l = j;
                id = i;
            }
            if( k - j > maxRight - maxLeft ) {
                maxRight = k;
                maxLeft = j;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxLeft ; i <= ( maxLeft + maxRight ) / 2 ; i++) {
               if( nStr.charAt(i) != '#' ) {
                   sb.append(nStr.charAt(i));
               }
        }
        return sb.toString().toCharArray();

    }

}
