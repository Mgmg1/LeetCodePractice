package Leet1;

/*
    移除元素。要求空间复杂度为常熟。。。。
    只需要移除数组中所有等于target的值，较简单。思路和Leet26类似
 */
public class Leet27 {

    public static void main(String[] args) {

        int[] arr = {0,1,2,2,3,0,4,2};
        int newLength = deleteTargetVal(arr, 2);
        for (int i = 0; i < newLength; i++) {
            System.out.println( arr[i] );
        }

    }

    private static int deleteTargetVal( int[] arr,int target ) {

        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if( arr[j] != target ) {
                arr[i++] = arr[j];
            }
        }
        return i;
    }

}
