package Leet1;

/*
    搜索旋转排序数组

    两种情况
        1 arr[left] < arr[right]

        2 arr[left] > arr[right]
            还可以细分为 arr[middle] 位于较长的有序部分还是较短的有序部分
 */
public class Leet33 {

    public static void main(String[] args) {
//        int[] arr = {4,5,6,7,0,1,2};
//        int target = 2;
        int[] arr = {4,5,6,7,0,1,2,3};
        int target = 5;
        System.out.println( sortedSpineArray(arr,target) );
    }

    public static int sortedSpineArray( int[] arr,int target ) {

        int left = 0;
        int right = arr.length - 1;
        while ( left <= right ) {
            int middle = ( left + right ) / 2;
            if( arr[middle] == target ) {
                return middle;
            }
            if( arr[middle] > target ) {
                if( arr[left] < arr[right] ) {
                    right = middle - 1;
                }else {
                    if( arr[left] < arr[middle] ) {
                        if( arr[left] <= target ) right = middle-1;
                        else left = middle+1;
                    }else {
                        right = middle - 1;
                    }
                }
            }else {
                if( arr[left] < arr[right] ) {
                    left = middle + 1;
                }else {
                    if( arr[left] < arr[middle]   ) {
                        left = middle + 1;
                    }else {
                        if( arr[left] >= target ) left = middle+1;
                        else right = middle-1;
                    }
                }

            }
        }
        return -1;
    }

}
