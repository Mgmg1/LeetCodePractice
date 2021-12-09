package Leet1;

import java.util.Arrays;
import java.util.LinkedList;

/*
    将有序数组转换为二叉搜索树
    LeetCode式的二叉树答案可以先构造树，再层次遍历
 */
public class Leet108 {

    public static void main(String[] args) {

        int[] integers = {-10, -3, 0, 5, 9};
        Integer[] integers1 = sortArrToBSTree(integers);
        System.out.println(Arrays.toString( integers1 ));

    }

    /*
        一般层次遍历有两种写法 ： 是否提前判断子结点为空
     */
    public static Integer[] sortArrToBSTree( int[] arr ) {

        if( arr == null || arr.length == 0) return new Integer[0];

        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addFirst( new int[]{0, arr.length-1} );
        while ( !queue.isEmpty() ) {
            int[] ints = queue.removeLast();
            int middle = ( ints[0] + ints[1] )/2;
            //由于可能要打印null结点，因此不能 提前判断子节点是否为空
            if( ints[0] > ints[1] ) {
                result.add( null );
            }else {
                result.add( arr[middle] );
                //根据题目要求，叶子节点的两个子节点为 null，不打印null
                if( ints[0] <= middle - 1 || ( middle + 1 ) <= ints[1] ) {
                    // 注意 middle-1 和 middle + 1 !!!
                    queue.addFirst( new int[]{ints[0],middle-1});
                    queue.addFirst( new int[]{middle+1,ints[1]});
                }
            }
        }
        if( result.getLast() == null ) {
            result.removeLast();
        }
        return result.toArray(new Integer[0]);
    }



}
