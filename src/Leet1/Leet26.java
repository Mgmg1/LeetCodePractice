package Leet1;

/*
    删除排序数组中的重复项
    较简单，用变量i指示 下一个放置的数组位置。
 */
public class Leet26 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 7};
        int newLength = removeDuplicateItem(arr);
        for (int i = 0; i < newLength; i++) {
            System.out.println( arr[i] );
        }
    }

    public static int removeDuplicateItem( int[] arr ) {

        int i = 0;
        if( arr.length >= 1) {
            i = 1;
        }else {
            return 0;
        }
        for (int j = 1; j < arr.length; j++) {
            if( arr[j] != arr[j-1] ) {
                arr[i++] = arr[j];
            }
        }
        return i;
    }

}
