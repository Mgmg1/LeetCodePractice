package Leet1;

import java.util.LinkedList;

/*
    二叉树的最小深度
 */
public class Leet111 {

    public static void main(String[] args) {

        Integer[] tree = {3, 9, 20, 1, 3, 15, 7};
        minDepth( tree );

    }


    /*
        非递归版
        给定完全二叉树
     */
    public static void minDepth( Integer[] arr ) {

        if( arr == null || arr.length == 0 ) return;

        LinkedList<Integer> queue = new LinkedList<>();
        int currentCount = 0;
        int levelCount = 1;
        int nextLevelCount = 0;
        int height = 1;
        queue.addFirst( 0 );
        while ( !queue.isEmpty() ) {
            Integer i = queue.removeLast();
            currentCount++;
            if( 2 * i < arr.length && arr[2 * i] != null  ) {
                queue.addFirst( 2*i );
                nextLevelCount++;
            }else {
                break;
            }
            if( 2 * i + 1 < arr.length && arr[2 * i + 1] != null ) {
                queue.addFirst( 2*i + 1 );
                nextLevelCount++;
            }else {
                break;
            }
            if( currentCount == levelCount ) {
                if( nextLevelCount == 0 ) break;
                levelCount = nextLevelCount;
                currentCount = 0;
                nextLevelCount = 0;
                height++;
            }
        }
        System.out.println( height );
    }

}
