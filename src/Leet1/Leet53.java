package Leet1;

import java.util.Arrays;

/*
    最大子序号和
    思路1：
    时间复杂度为O(n)
    当前和为负数时，取为下一个值
    当前和为正数时，加上下一个值
    思路2：
    事件复杂度为O(nlogn) 分治法

 */
public class Leet53 {


    public static void main(String[] args) {
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] result = MaxSubArray(input);
        int result1 = 0;
        for (int i = result[0]; i <= result[1]; i++) {
            result1+=input[i];
        }
        int result2 = recur( input,0,input.length - 1 );

        System.out.println( result1 +"   " + result2 );
    }


    public static int[] MaxSubArray(int []arr){
        if( arr==null ) {
            return null;
        }
        int[] result = new int[2];
        if( arr.length == 1){
            return result;
        }

        int begin = 0,end = 0;
        int rightBegin = 0;
        int sum = arr[0],rightSum = arr[0];
        //通过不断维护一个以i末端的最大子序列来比较出数组中和最大的子序列
        for (int i = 1; i < arr.length; i++) {
            if( rightSum < 0 ) {
                rightSum = arr[i];
                rightBegin = i;
            }else {
                rightSum+=arr[i];
            }
            if( rightSum > sum ) {
                begin = rightBegin;
                end = i;
                sum = rightSum;
            }
        }
        result[0] = begin;
        result[1] = end;

        return result;
    }

    public static int recur(int[] arr,int start,int end ) {

        if( start == end ) {
            return arr[start];
        }
        //发现当 只有start == end 作为递归出口时，会发生 start > end 的情况
        //因此增加 start + 1 == end 作为递归出口
        if( start + 1 == end ) {
            if( arr[start] > 0 && arr[end] > 0 ) {
                return arr[start] + arr[end];
            }
            return Math.max( arr[start],arr[end] );
        }

        int middle = ( start + end ) /2;
        int maxLeft = arr[middle-1],maxRight = arr[middle + 1];
        int sum = arr[middle + 1];
        for (int i = middle + 2; i <= end ; i++) {
            if( sum + arr[i] > maxRight ) {
                maxRight = sum + arr[i] ;
            }
            sum+=arr[i];
        }
        sum = arr[middle-1];
        for (int i = middle - 2; i >= start ; i--) {
            if( sum + arr[i] > maxLeft ) {
                maxLeft = sum + arr[i];
            }
            sum+=arr[i];
        }
        int recurLeft = recur(arr, start, middle-1);
        int recurRight = recur(arr, middle + 1, end);
        int max =( maxLeft > 0 && maxRight > 0 ? maxLeft + maxRight  : Math.max( maxLeft,maxRight ) ) + arr[middle];
        return Math.max( max,Math.max( recurLeft,recurRight ) );
    }
}
