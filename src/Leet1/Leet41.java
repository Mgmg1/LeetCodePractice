package Leet1;

/*
    缺失的第一个正数
    思路：
        提供的是无序的整数数组（有正数和负数，要求得到排序后的第一个缺失的正数），时间复杂度为n
        可能有重复的元素

        由题意可知， 缺失的第一个正数n 前，必定由 1~n-1 ,可以让它们对上数组下标
        循环不等式证明：arr[i] >= 0 && arr[i] < arr.length && arr[arr[i]] != arr[i]

        由于arr[arr[i]] != arr[i] ，则 arr[i] 替换 arr[arr[i]],从而使得 arr[arr[i]] = arr[i];
        但是arr[i] 更改成新值，此时 i 不变，问题规模-1！！！。继续重复上述过程

 */
public class Leet41 {

    public static void main(String[] args) {
//        int[] arr = {1,2,0};
//        int[] arr = {3,4,-1,1};
        int[] arr = {7,8,9,11,12};
        System.out.println( firstPositiveNumber(arr) );
    }

    public static int firstPositiveNumber( int[] arr ) {

        for (int i = 0; i < arr.length; ) {
            if( arr[i] >= 0 && arr[i] < arr.length && arr[arr[i]] != arr[i]  ) {
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;
            }else {
                i++;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if( i != arr[i] ) {
                return i;
            }
        }
        return arr.length;
    }

}
