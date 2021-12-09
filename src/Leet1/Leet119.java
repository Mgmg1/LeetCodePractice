package Leet1;

import java.util.Arrays;

/*
    杨辉三角II
    只输出第n层

    根据归纳：
    如果要避免内存覆盖，可以考虑 改变遍历方向（如背包问题！！）
 */
public class Leet119 {


    public static void main(String[] args) {
        System.out.println(Arrays.toString( triangle(5) ));
    }

    public static int[] triangle( int n ) {

        if( n < 1 ) return new int[0];
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n ; i++) {
            result[i] = 1;
            for (int j = i - 1; j >= 1 ; j--) {
                result[j] = result[j] + result[j-1];
            }
        }
        return result;
    }


}

