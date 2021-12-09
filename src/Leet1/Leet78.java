package Leet1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    子集
    有自底向上和自顶向下两种
    当程序包含多个递归无法看明白时，可以尝试一下运行观察第一个递归的结果,再推理出来
 */
public class Leet78 {

    public static void main(String[] args) {
//        System.out.println( way1(new int[]{1,2,3,4}) );
        LinkedList<List<Integer>> lists = new LinkedList<>();
        way2(lists,new int[]{1,2,3,4} ,3);
        System.out.println( lists ) ;
    }

    public static List<List<Integer>> way1( int[] arr ) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if( arr.length == 0 ) {
            return result;
        }
        result.add(new LinkedList<Integer>());
        LinkedList<Integer> list = new LinkedList<>();
        list.add(arr[0]);
        result.add( list );
        if( arr.length == 1 ) {
            return result;
        }
        for (int i = 1; i < arr.length; i++) {
            LinkedList<List<Integer>> temp = new LinkedList<>();
            for (List cList :
                    result) {
                LinkedList<Integer> ncList = new LinkedList<Integer>(cList);
                ncList.add(arr[i]);
                temp.add(ncList);
            }
            result.addAll(temp);
        }

        return result;
    }

    public static void way2( List<List<Integer>> result,int[] arr,int i ) {
        if( i == 0 ) {
            result.add(new LinkedList<>());
            LinkedList<Integer> list = new LinkedList<>();
            list.add( arr[0] );
            result.add(list);
            return;
        }

        way2(result,arr,i-1);
        List<List<Integer>> temp = new LinkedList<>();
        for (List cist :
                result) {
            List<Integer> ncList = new LinkedList<>( cist );
            ncList.add( arr[i] );
            temp.add( ncList );
        }
        result.addAll( temp );
    }
}
