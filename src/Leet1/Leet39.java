package Leet1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    组合总合
    arr的元素可重复取用
    由于结果分为  包含 0 ~ n 个 arr[n]
    递归定义为： 0个 arr[n]的 list +
    if target >= arr[n]  +  1个 arr[n]的 list + 2个 arr[n]的 list +..... until target < arr[n]

    注意。。。本题由于可重复使用元素，必须先去除重复元素。。。

    去重的目的：由于arr[n] 可重复使用，去重  避免子递归使用到了重复的元素，可以使得结果不出现重复

 */
public class Leet39 {

    public static void main(String[] args) {

        int[] arr = {7,6,5,3,2};
        int target = 7;
        Arrays.sort( arr );
        ArrayList<Integer> collect = Arrays.stream(arr).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        int[] ints = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            ints[i] = collect.get(i);
        }
        List<LinkedList<Integer>> combination = combination(ints, target, arr.length - 1);
        System.out.println( combination );
    }

    /*
        注意。。。本题由于可重复使用元素，必须先去除重复元素。。。
     */
    public static List< LinkedList<Integer> > combination( int[] arr,int target,int n) {
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        if( n >= 0  ) {
//            List<LinkedList<Integer>> combination = combination(arr, target, n - 1);
//            for (LinkedList<Integer> linkedList:
//                    combination) {
//                linkedList.add( arr[n] );
//            }
//            list.addAll(combination) ;  错误 ！！！！
            //因为没有减去 target，故不用加上 arr[n];
            list.addAll(combination(arr, target, n - 1)) ;
            int k = 0;
            while ( target >= arr[n] ) {
                target-=arr[n];
                k++;
                List<LinkedList<Integer>> combination2 = combination(arr, target, n - 1);
                for (LinkedList<Integer> linkedList:
                        combination2) {
                    for (int i = 0; i < k; i++) {
                        linkedList.add( arr[n] );
                    }
                }
                list.addAll(combination2) ;
            }

            return list;
        }
        if( target == 0 ) {
            //添加一个空链表。表示此次递归结果有效！！！以供给递归的上一层添加 arr[n]
            LinkedList<Integer> integers = new LinkedList<>();
            list.add(integers);
        }
        return list;
    }
}
