package Leet1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    全排列

 */
public class Leet46 {

    public static void main(String[] args) {

        int[] ints = { 1,2,3 };
        System.out.println( arrangement(ints,0) );
        List<int[]> ints1 = toUpArrangement(ints);
        for (int[] ints2:
             ints1) {
            System.out.println( Arrays.toString( ints2 ) );
        }

    }

    /*
        递归形式

        发现  List<ArrayList<Integer>> 无法转为  List<List<Integer>>

        将一个全排列分解为  以arr[i] 开头 ，以arr[i+1] 开头 。。。。。

        假如是n个组合，也同样可以划分，划分规则为包含和不包含 直到最后剩下n个时，

     */
    public static List<ArrayList<Integer>> arrangement( int[] arr,int i ) {

        ArrayList<ArrayList<Integer>> integers = new ArrayList<>();
        if( i == arr.length ) {
            //根据 i == arr.length 和  i == arr.length - 1 的关系可以得到需要放置一个空集合
            integers.add( new ArrayList<>() );
            return integers;
        }

        for (int j = i; j < arr.length; j++) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            List<ArrayList<Integer>> arrangement = arrangement(arr, i + 1);
            for (ArrayList<Integer> list:
                 arrangement) {
                list.add( arr[i] );
                integers.add( list );
            }
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return integers;
    }

    /*
        存在一个自底向上的生成全排列的方法！！！
        组合似乎不存在

        思路：自底向上 n的全排列等于
        n-1的全排的每个排列的间隙插进去的所有可能
      n-1:  12 , 21
      n:    123  132  312  ,   213   231   321
     */
    public static List<int[]>  toUpArrangement( int[] arr ) {

        List<int[]> before = new ArrayList<>();
        List<int[]> now = new ArrayList<>();
        before.add( new int[arr.length] );
        for (int i = 0; i < arr.length; i++) {
            now.clear();
            for (int[] arrangement:
                 before) {
                arrangement[i] = arr[i];
                now.add( Arrays.copyOf(arrangement, arrangement.length) );
                int temp = 0;
                for (int j = i-1; j >= 0 ; j--) {
                    temp = arrangement[j];
                    arrangement[j] = arrangement[j+1];
                    arrangement[j + 1] = temp;
                    now.add( Arrays.copyOf(arrangement, arrangement.length)  );
                }
            }
            before.clear();
            before.addAll( now );
        }
        return now;
    }

    /*
        另一种想法:
        利用字典顺序，根据生成下一个大于它的序列的算法，
        来得到全排列！！
        这要求先按从小到达 排序
     */
}
