package Leet1;

import java.util.Arrays;

/**
 * 最大数
 * 根据剑指offer最小数算法的思路，需要设计一套比较规则，并通过反证法证明
 */
public class Leet179 {

    public static void main(String[] args) {
        String[] nums = {
                "3","30","34","5","9"
        };
        System.out.println( maxNum( nums ) );
    }

    /**
     *
     * @param nums 预先设定nums中没有null
     * @return
     */
    private static String maxNum(String[] nums ) {
        if( nums == null || nums.length == 0 ) { return "";}
        if( nums.length == 1 ) { return nums[0]; }

        //先按照自定义规则进行排序进行排序，这里使用快排
        quickSort( nums,0,nums.length - 1 );
        StringBuilder sb = new StringBuilder();
        for (String num :
                nums) {
            sb.append( num );
        }
        return sb.toString();
    }

    /**
     * 快速排序，采用逆序的方式排序
     * @param nums
     * @param start
     * @param end
     */
    private static void quickSort( String[] nums,int start,int end ) {
        if( nums == null || nums.length <= 1 || start >= end ) { return; }

        int pivot = start + (int) (( end - start ) * Math.random());
        String temp = nums[pivot];
        nums[pivot] = nums[start];
        nums[start] = temp;

        int begin = start + 1,last = end;
        while ( begin <= last ) {
            while ( begin <= last && compare( nums[begin],nums[start] ) >= 0 ) { begin++; }
            while ( begin <= last && compare( nums[last],nums[start] ) < 0 ) { last--; }
            if( begin < last ) {
                temp = nums[begin];
                nums[begin] = nums[last];
                nums[last] = temp;
            }
        }
        temp = nums[start];
        nums[start] = nums[begin-1];
        nums[begin - 1] = temp;
        quickSort( nums,start, begin - 2);
        quickSort( nums,begin,end );
    }

    /**
     * num1拼接num2 大于 num2拼接num1 则返回正数，相等则返回0，小于则返回负数
     * @param num1
     * @param num2
     * @return
     */
    private static int compare( String num1,String num2 ) {
        return  ( num1 + num2 ).compareTo( num2 + num1 );
    }
}
