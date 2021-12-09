package Leet1;

import java.util.Arrays;

/*
    寻找排序数组中的最小值
    数组中允许有重复元素
 */
public class Leet154 {

    public static void main(String[] args) {
//        int[] arr = {1,3,5};
        int[] arr = {2,2,2,0,2};
        System.out.println( findMinInSpunSortArray(arr) );
    }

    public static int findMinInSpunSortArray( int[] arr ) {
        if( arr == null || arr.length == 0 ) { return 0; }
        if( arr.length == 1 ) { return arr[0]; }
        int start = 0,end = arr.length - 1;
        while ( start < end ) {
            int middle = ( start + end ) / 2;
            if( arr[start] > arr[end] ) {
                if( arr[middle] > arr[middle + 1] ) {
                    return middle + 1;
                }else if( arr[start] <= arr[middle] ) {
                    start = middle+1;
                }else {
                    end = middle - 1;
                }
            }else if( arr[start] < arr[end] ) {
                return start;
            }else {
                return findMin( arr,start,end );
            }
        }
        //不会发生的情况
        return start;
    }
    private static int findMin( int[] arr,int start,int end) {
        if( start >= end ) {
            return start;
        }
        int min = arr[start];
        int minI = start;
        for (int i = start + 1; i <= end; i++) {
            if( min > arr[i] ) {
                min = arr[i];
                minI = i;
            }
        }
        return minI;
    }

}
