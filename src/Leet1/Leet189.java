package Leet1;

import java.util.Arrays;

/**
 * 旋转数组
 * 提供一个非负整数k和一个数组，要求让数组向右旋转k位
 * 要求O(1)的空间复杂度
 */
public class Leet189 {

    public static void main(String[] args) {
        int[] arr = { 1,2,3,4,5,6,7 };
        int k = 3;
//        spinArray1( arr,k );
//        spinArray2( arr,k );
        spinArray3( arr,k );
        System.out.println(Arrays.toString( arr ));
    }

    /**
     * 使用一个临时变量存储这样，数组就会空出一个位置
     * @param arr
     * @param k
     */
    private static void spinArray1( int[] arr,int k ) {
        if( k <= 0 ) { return; }

        int temp = arr[0];
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            arr[j] = arr[ (j - k + arr.length) % arr.length ];
            j = (j - k + arr.length) % arr.length;
        }
        arr[j] = temp;
    }

    /**
     * 和spinArray1思路相似，但是j是往前的
     * @param arr
     * @param k
     */
    private static void spinArray2( int[] arr,int k ) {
        if( k <= 0 ) { return; }

        int temp1;
        int temp2 = arr[0];
        int j = k % arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            temp1 = arr[j];
            arr[j] = temp2;
            temp2 = temp1;
            j = ( j + k ) % arr.length;
        }
        arr[j] = temp2;
    }

    /**
     * 基于剑指offer提出的巧妙的翻转方法
     * @param arr
     * @param k
     */
    private static void spinArray3( int[] arr,int k ) {
        if( k <= 0 ) { return; }

        //先翻转
        int temp;
        for (int i = 0,j = arr.length - 1; i < arr.length / 2; i++,j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        //再将前arr.length - k位 和 后 k 位翻转,就有数组向右旋转k位的效果了
        k = ( k - 1 )  % arr.length;
        int j = k;
        for (int i = 0; i <= k / 2 ; i++,j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        j = arr.length - 1;
        for (int i = k + 1; i <=( k + arr.length )/ 2 ; i++,j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
