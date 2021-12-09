package Leet1;

import java.util.Arrays;
import java.util.LinkedList;

/*
    最接近的三个数之和

    可以由三个数之和改造,但是由于只有唯一的答案，可以简化掉循环检查去重的部分

 */
public class Leet16 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString( mostNearThreeNumSum( new int[]{-1,2,1,-4},1 ) ));

    }

    public static int[] mostNearThreeNumSum( int[] nums,int target) {

        if( nums.length < 3 ) return new int[0];
        //先进行排序
        Leet15.quickSort( nums,0,nums.length-1 );
        int[] result = new int[3];
        //int的最大值 ,注意是 31位，不是32位，否则
        //超过或者等于 左移位类型的范围时，编译器会 进行模运算后再位移
        int near = ~(1<<31);
        for (int i = 2; i < nums.length; i++) {
            int j = 0,k = i-1;
            while ( j < k ) {
                if( nums[j] + nums[k] + nums[i] <= target ) {
                    if( target - ( nums[j] + nums[k] + nums[i] ) < near) {
                        near = target - ( nums[j] + nums[k] + nums[i] );
                        result[0] = nums[k];
                        result[1] = nums[j];
                        result[2] = nums[i];
                    }
                    j++;
                }else {
                    if(  nums[j] + nums[k] + nums[i] - target  <  near) {
                        near = nums[j] + nums[k] + nums[i] - target;
                        result[0] = nums[k];
                        result[1] = nums[j];
                        result[2] = nums[i];
                    }
                    k--;
                }
            }
        }
        return result;
    }

}
