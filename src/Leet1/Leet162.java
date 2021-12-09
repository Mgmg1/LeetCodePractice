package Leet1;

/*
    寻找峰值
    找出一个即可
    要求时间复杂度未O(logn) !!
 */
public class Leet162 {
    public static void main(String[] args) {
//        int[] nums= { 1,2,3,1 };
        int[] nums= { 1,2,1,3,5,6,4 };
        System.out.println( searchPeak( nums ) );
    }

    /*
        nums[-1]=nums[n]=-∞ ; nums[i] != nums[i+1]  !!!!
        说起来时间复杂度限制为O(logn)就应该认为存在一种和二分相关的解法
                    。   。
           。。。。。   。   。。。。。
          。        。   。        。
         。                        。
     */
    private static int searchPeak(int[] nums ) {
        if( nums == null || nums.length == 0 ){ return -1; }
        if( nums.length == 1 ) { return 0; }

        int start = 0,end = nums.length -1;

        int middle;
        while ( start + 1 < end ) {
            middle = ( start + end ) / 2;
            if( nums[middle] < nums[middle + 1] ) {
                start = middle; //当 start + 1 = middle 时 回导致死循环
            }else {
                end = middle;
            }
        }
        return nums[start] > nums[start + 1] ? start : start + 1;
    }

}
