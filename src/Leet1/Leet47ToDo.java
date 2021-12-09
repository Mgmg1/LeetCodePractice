package Leet1;

import java.util.*;

/*
    全排列II
    包含重复元素

    和全排列I的思路相近

    递归版：
        在选取元素时，去掉重复的已经选取的元素

    自底向上生成的方法会出现重复的情况！！！

    可以考虑 下一个排序生成的算法，无论是否存在重复元素，都是能使用用的！！！
 */
public class Leet47ToDo {

    public static void main(String[] args) {

        int[] nums = {1,1,2};
        System.out.println( arrangement( nums,0 ) );

    }

    /*
        一种策略是 使用hashmap记录在前些循环中被划分过数组值
        另一种是 进行预排序，再进行递归。（效率太差。。。）
     */
    public static List<ArrayList<Integer>> arrangement(int[] arr, int n) {

        List<ArrayList<Integer>> result = new ArrayList<>();

        //空数组的全排列是空!!
        //可以联系 arr.length - 1 == n 的情况
        if( arr.length == n ) {
            result.add( new ArrayList<>() );
            return result;
        }

        int temp = 0;
        Map<Integer,Boolean> flags = new HashMap<>();
        for (int i = n; i < arr.length; i++) {
            if( !flags.containsKey( arr[i] ) ) {
                flags.put( arr[i],true);
                temp = arr[n];
                arr[n] = arr[i];
                arr[i] = temp;
                //无法将map放到参数中让全局共享，因为会造成覆盖！！
                List<ArrayList<Integer>> arrangement = arrangement(arr, n + 1);
                for (ArrayList<Integer> list:
                        arrangement) {
                    list.add( arr[n] );
                    result.add( list );
                }
                temp = arr[n];
                arr[n] = arr[i];
                arr[i] = temp;
            }
        }
        flags.clear();
        flags = null;
        return result;
    }


    /*
        预先排序的情况
        由于预先排序后，数组元素会交换破坏原先的顺序。（子递归拿到的数组是乱序的！！）
        若要实现则必须在交换后重新进行排序，但是这样效率很低。
        该写法忽略
     */
//    public static List<ArrayList<Integer>> arrangement2(int[] arr, int n) {
//        //.............
//    }


    /*
        下一排列。。。todo
     */
}
