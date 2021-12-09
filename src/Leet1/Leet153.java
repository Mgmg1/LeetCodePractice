package Leet1;

/*
    寻找旋转排序数组中的最小值
    数组中没有重复值
 */
public class Leet153 {

    public static void main(String[] args) {
//        int[] arr = {3,4,5,1,2};
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println( minInSpunSortedArray( arr ) );
    }

    /*
        假如数组为空，返回0
     */
    private static int minInSpunSortedArray( int[] arr ) {
        if( arr == null || arr.length == 0 ) { return 0; }
        if( arr.length == 1 )  { return arr[0]; }


        int start = 0,end = arr.length - 1;
        while ( start < end ) {
            int middle = ( start + end ) / 2;
            if( arr[start] > arr[end] ) {
                //在临界情况下当start ~ end 个数为 2 时，恰好不会越界
                if( arr[middle] > arr[middle + 1] ){
                    return middle + 1;
                }else if( arr[middle] > arr[start] ) {
                    start = middle + 1;
                }else {
                    end = middle - 1;
                }
            }else {
                return arr[start];
            }
        }
        //在不存在重复元素的情况下，不会执行到这一步
        return arr[start];
    }

}
