package Leet1;

import java.util.*;

/*
    子集II ： 允许有重复元素
    总体和子集I类似，需要预先排序，当碰到重复的元素时，需要利用上次插入的生成的元素，而不是整个子集的元素

     result
    {}
    {},{1}   <-1
    {},{1},{2},{1,2}  <-2
    ....
    假如没有重复元素时，只需要复制一遍 result，再遍历被复制的result，加入 arr[i] ,然后将结果和result合并


    假如存在重复元素，需要进行预排序，
    引入before变量（代表上次次生成的新的子集），和temp变量代表此次遍历的变量的集合。
    result
    {}
    {},{1}   <-1
    {},{1},{2},{1,2}  <-2
    和上一个元素重复，此时需要复制一遍before的内容到temp，清空before，遍历temp，将新生成的子集加入 result
    否则 temp 赋值为 result，然后按照上一步继续。
    {},{1}, {2},{1,2}    ,   {2,2},{1,2,2}          <-2
    ....

     */
public class Leet90 {

    public static void main(String[] args) {

        int[] arr = { 1,2,2};
//        quicksort( arr,0 ,arr.length - 1 );

        System.out.println(  linkedLists( arr )  );
    }

    public static List<ArrayList<Integer>> linkedLists( int[] arr ) {

        combinesort(arr,new int[arr.length],0,arr.length - 1,true);
        ArrayList<ArrayList<Integer>> before = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add( new ArrayList<>() );

        for (int i = 0; i < arr.length; i++) {
            ArrayList<ArrayList<Integer>> temp;
            if( i != 0 && arr[i] == arr[i-1] ) {
                temp = (ArrayList<ArrayList<Integer>>) before.clone();
            }else {
                temp = result;
            }
            before.clear();
            for (ArrayList<Integer> list:
                    temp) {
                ArrayList<Integer> integers = (ArrayList<Integer>) list.clone();
                integers.add( arr[i] );
                before.add( integers );
            }
            result.addAll( before );
        }

        return result;

    }


    private static void quicksort( int[] arr, int start,int end ) {

        if( start >= end ) return;
        if( start == end - 1 ) {
            if( arr[start] > arr[end] ) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
            return;
        }

        int temp = 0;
        //选择中枢为 arr[start]
        int p = start,q = end;
        while ( start < end ) {
            if( arr[start + 1] <= arr[start] ) {
                temp = arr[start + 1];
                arr[ start + 1 ] = arr[start];
                arr[start++] = temp;
            }else {
                temp = arr[start + 1];
                arr[ start + 1 ] = arr[end];
                arr[end--] = temp;
            }
        }
        // 此时 start == end, arr[start] 的位置在排序后的数组上的位置是正确了
        quicksort( arr,p, start-1);
        quicksort( arr,end + 1,q );

    }

    /*
        flag 是标志 arr，和temp的左右标志
        当flag 为true时，说明 arr 是目标数组，temp 是临时数组，flag为false时反过来

        //循环时，程序固定向左边赋值。 flag 说明哪边是真正的 arr
        //递归时调整 temp 和 arr的位置，这样每次进入递归都会自动调整位置
     */
    public static void combinesort( int[]arr,int[] temp,int start,int end,boolean flag ) {

        if( start > end ) return;
        if( start == end ) {
            //递归出口，告诉程序是否需要像 左边数组赋值
            if( !flag ) {
                arr[start] = temp[start] ;
            }
            return;
        }
        int middle = ( start + end ) / 2;

        //这里会自动调整 temp 和 arr 的位置！！！
        combinesort( temp,arr,start,middle,!flag);
        combinesort(temp,arr,middle + 1, end,!flag);

        int i = start;
        int k = start,l = middle + 1;
        //程序固定向左边赋值
        while ( i <= end ) {
            if( l > end || ( k <= middle &&  temp[k] < temp[l]  )) {
                arr[i] = temp[k++];
            }else {
                arr[i] = temp[l++];
            }
            i++;
        }
    }

}
