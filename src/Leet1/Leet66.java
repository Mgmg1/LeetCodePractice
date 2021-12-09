package Leet1;

import java.util.Arrays;

/*
    加一
    和利用链表进行 加减法运算 相似，比较简单
 */
public class Leet66 {

    public static void main(String[] args) {
        int[] ints1 = plusOne(new int[]{1, 2, 3}, 3);
        int[] ints2 = plusOne(new int[]{4, 3, 2,1}, 4);
        int[] ints3 = plusOne(new int[]{9, 9, 9,9}, 4);
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.toString(ints2));
        System.out.println(Arrays.toString(ints3));
    }

    public static int[] plusOne(int[] arr, int n ){

        int[] result = new int[n];

        int carry = 1;
        for (int i = n -1 ; i >= 0 ; i--) {
            result[i] = ( carry + arr[i] ) % 10;
            carry = ( carry + arr[i] ) / 10 ;
        }
        if( carry != 0 ){
            int[] newResult = new int[n + 1];
            for (int i = 1; i < n+1; i++) {
                newResult[i] = result[i - 1];
            }
            newResult[0] = carry;
            result = newResult;
        }
        return result;
    }


}
