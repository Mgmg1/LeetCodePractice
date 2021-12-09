package Leet1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    组合总合II
    不可以重复使用 元素
    大体思路和  组合总合I 类似
    注意，使用递归时，先考虑 出口，再过渡到考虑递归定义的代码

    注意。。。本题不可重复使用元素，因此需要预排序，但是不需要去重
    在递归时，还要注意补上对应个数的 arr[n]

    预排序目的：避免子递归使用到了重复的元素。

 */
public class Leet40 {

    public static void main(String[] args) {
        int[] arr = {10,1,2,7,6,1,5};
        int target = 8;

        Arrays.sort( arr );
        List<LinkedList<Integer>> combination = combination(arr, target, arr.length - 1);

        System.out.println( combination );
    }


    public static List<LinkedList<Integer>> combination( int[]arr,int target,int n ) {

        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        //加上 target >= 0 可以提前结束，因为arr中都说正数， 假如target < 0 ，则 if 中的 递归结果都一定是空
        if ( n >= 0 && target >= 0) {
            int k = 1;
            for (int i = n-1; i >=0 ; i--) {
                if( arr[i + 1] == arr[i] ){
                    k++;
                }else {
                    break;
                }
            }
            int length = k ;
            result.addAll(  combination(arr, target, n - length) );
            while ( target >= arr[n] && k > 0 ) {
                target-=arr[n];
                k--;
                List<LinkedList<Integer>> combinations = combination(arr, target, n - length);
                for (LinkedList<Integer> list :
                        combinations) {
                    //要记得补上对应的个数的 元素
                    for (int i = 0; i < length - k; i++) {
                        list.add( arr[n] );
                    }
                }
                result.addAll( combinations );
            }
            return result;
        }
        if( target == 0 ) {
            result.add( new LinkedList<>() );
        }
        return result;
    }

}
