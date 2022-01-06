package Leet1;

import java.util.Map;

/**
 * 打家劫舍
 */
public class Leet198 {

    public static void main(String[] args) {
//        int[] nums = { 1,2,3,1 };
        int[] nums = { 2,7,9,3,1 };
        System.out.println( robbery( nums ) );
    }

    /**
     * 思路：显然要使用动态规划,空间复杂度可以优化为O（1）
     * @param nums
     * @return
     */
    private static int robbery( int[] nums ) {
        if( nums == null || nums.length == 0 ) {return 0;}

        int res1 = 0;
        int res2 = nums[0];
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp = Math.max( res1 + nums[i],res2 );
            res1 = res2;
            res2 = temp;
        }
        return res2;
    }
}
