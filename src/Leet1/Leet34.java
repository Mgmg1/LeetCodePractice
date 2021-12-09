package Leet1;

import java.util.Arrays;

/*
    搜索范围
    找出target的开始位置和结束位置，要求log(n)
    拓宽思路！！：用两次二分查找，
        第一遍要求等于目标值   !!!且目标值左边小于target || 无
        第一遍要求等于目标值   !!!且目标值左边大于target || 无

        需要注意条件判断的等号!!! 第一遍相等或target在左边时，right = middle -1；
        第二遍相等或target在右边时， left = middle + 1；

 */
public class Leet34 {

    public static void main(String[] args) {
        int[] arr = {5,7,7,8,8,8,8,8,8,8,8,8,8};
        int target = 8;
//        int target = 6;
        System.out.println(Arrays.toString( searchRange(arr,target) ) );
    }

    public static int[] searchRange( int[] arr,int target  ) {

        int left = 0;
        int right = arr.length - 1;
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        while ( left <= right ) {
            int middle = ( left + right ) / 2;
            if( arr[middle] == target && ( middle - 1 < 0 || arr[middle - 1] < target ) ) {
                result[0] = middle;
                break;
            }
            if( arr[middle] >= target ) {
                right = middle-1;
            }else {
                left = middle + 1;
            }
        }
        left = 0;
        right = arr.length - 1;
        while ( left <= right ) {
            int middle = ( left + right ) / 2;
            if( arr[middle] == target && ( middle + 1 >= arr.length || arr[middle + 1] > target ) ) {
                result[1] = middle;
                break;
            }
            if( arr[middle] <= target ) {
                left = middle + 1;
            }else {
                right = middle - 1 ;
            }
        }
        return result;
    }

}
