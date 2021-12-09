package Leet1;

import java.util.LinkedList;

/*
    平衡二叉树
    题目给定的层次遍历，都是完全二叉树。可以按照下标访问。从下网上遍历
 */
public class Leet110 {

    public static void main(String[] args) {

//        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        Integer[] arr = {1,2,2,3,3,null,null,4,4};
        System.out.println( recurIsBalancedBTree( arr,0 ) );
        System.out.println( dpIsBalanceBTree( arr ) );

    }

    /*
        返回高度，当高度等于-1时，说明不平衡。
     */
    public static int recurIsBalancedBTree(Integer[] arr, int i) {


        if( i >= arr.length || arr[i] == null ) return 0;

        int lHeight = recurIsBalancedBTree(arr, 2 * i + 1);
        if (lHeight == -1) return -1;
        int rHeight = recurIsBalancedBTree(arr, 2 * i + 2);
        if (rHeight == -1) return -1;

        return Math.abs(lHeight - rHeight) <= 1 ? Math.max(lHeight + 1, rHeight + 1) : -1;

    }

    /*
        动态规划版，空间复杂度为 O(N)
     */
    public static boolean dpIsBalanceBTree( Integer[] arr ) {

        int[] heights = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if( arr[i] == null ) {
                heights[i] = 0;
                continue;
            }
            int lHeight = i * 2 + 1 >= arr.length || arr[i] == null ? 0 : heights[2*i + 1];
            int rHeight = i * 2 + 2 >= arr.length || arr[i] == null ? 0 : heights[2*i + 2];
            if( Math.abs( lHeight - rHeight ) > 1 ) return false;
            heights[i] = Math.max( lHeight , rHeight ) + 1;
        }
        return true;
    }
}