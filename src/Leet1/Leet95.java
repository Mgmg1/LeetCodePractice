package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    不同的二叉搜索树II
    思路：一边递归构建树，一边构造队列
 */
public class Leet95 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {

        int n = 4;
        LinkedList<List<Integer>> lists = differentBinarySearchTree(n);
        System.out.println( lists );
    }

    public static LinkedList<List<Integer>> differentBinarySearchTree( int n ) {

        LinkedList<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> record = new LinkedList<>();
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.addLast( new int[]{1 , i - 1} );
            queue.addLast( new int[]{ i + 1, n } );
            record.addLast( i );
            differentBinarySearchTree( queue,record,result,n - 1,1);
            record.removeLast( );
        }
        return result;
    }

    /*
        错。。。。
     */
    public static void differentBinarySearchTree(LinkedList<int[]> queue,
                                                 LinkedList<Integer> record,
                                                 LinkedList<List<Integer>> result,int n,int count) {
        if(  n == 0  ) {
            //!!!
            if( !queue.isEmpty() ) {
                int[] last = queue.getFirst();
                if( last[0] > last[1] ) {
                    queue.removeFirst();
                    record.addLast(null);
                }
            }
            result.add((List) record.clone());
            if( record.getLast() == null ) {
                record.removeLast();
            }
            return;
        }
        if( !queue.isEmpty() ) {
            int[] pop = queue.removeFirst();
            if( pop[0] > pop[1] ) {
                record.addLast( null );
                differentBinarySearchTree( queue,record,result,n,count + 1);
                record.removeLast( );
                return;
            }
            if( pop[0] == pop[1] ) {
                record.addLast( pop[0] );
                differentBinarySearchTree( queue,record,result, n - 1,count + 1 );
                record.removeLast( );
                return;
            }
            for (int i = pop[0]; i <= pop[1]; i++) {
                queue.addLast( new int[]{pop[0] , i - 1} );
                queue.addLast( new int[]{ i + 1, pop[1] } );
                record.addLast( i );
                //!!!! 当此时是左子树时，后造成右子树的record的值丢失
                int[] peek = queue.getFirst();
                differentBinarySearchTree( queue,record,result, n - 1,count + 1 );
                record.removeLast( );
                //!!!!
                if( count % 2 == 1 ) {
                    queue.addFirst( peek );
                }
            }
            ///!!!! 去除多余的
            if( count % 2 == 1 ) {
                queue.removeFirst();
            }

        }

    }

}
