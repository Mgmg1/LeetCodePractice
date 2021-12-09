package Leet1;

/*
    搜索插入位置
    思路：
    根据归纳总结，当找不到位置时，插入位置对应的下标就是 left
 */
public class Leet35 {

    public static void main(String[] args) {

        int[] arr = {1,3,5,6};
        int target = 2;
        System.out.println( searchInsertedPosition(arr,target) );

    }

    public static int searchInsertedPosition(int[] arr,int target ) {

        int left = 0;
        int right = arr.length - 1;
        while ( left <= right ) {
            int middle = ( left + right ) / 2;
            if( arr[middle] == target ) {
                return middle;
            }
            if( arr[middle] < target ) {
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        //根据归纳总结 ，假设 left == right 时，分类讨论得到
        return left;
    }

}
