package Leet1;

import java.util.Arrays;

/*
    两数之和 ||  - 输入有序数组
    思路：其实就是 两数之和
 */
public class Leet167 {
    public static void main(String[] args) {
        int[] values = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString( twoNumberSum(values,target) ));
    }

    private static int[] twoNumberSum( int[] values,int target) {
        if( values == null || values.length < 2 ) { return null; }

        int i = 0,j = values.length - 1;
        while ( i < j ) {
            if( values[i] + values[j] == target ) {
                return new int[]{i,j};
            }else if( values[i] + values[j] < target ) {
                i++;
            }else {
                j--;
            }
        }
        return null;
    }
}
