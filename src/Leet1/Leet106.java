package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    中序 和 后序 遍历 生成树
 */
public class Leet106 {

    private static class Node {

        int data;
        Node lChild;
        Node rChild;
        public Node(int data, Node lChild, Node rChild) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {
        int[] postOrder = {9,15,7,20,3};
        int[] inorder = {9,3,15,20,7};
        Node node = generateBTree(postOrder, inorder, 0, postOrder.length - 1,0, postOrder.length - 1);
//        levelTravel( node );
        System.out.println( levelTravelII(node) );
    }

    public static Node generateBTree( int[] postOrder,int[] inOrder,
                                      int pStart,int pEnd,
                                      int iStart,int iEnd) {

        if( pStart > pEnd ) return null;
        if( pStart == pEnd ) return new Node( postOrder[pStart],null,null );

        int i = iStart;
        for (; i <= iEnd && inOrder[i] != postOrder[pEnd]; i++) {}
        int count = i - iStart; // 左子树的结点个数

        //生成左子树
        Node left = generateBTree(postOrder, inOrder, pStart, pStart + count - 1, iStart , i - 1);
        Node right = generateBTree(postOrder, inOrder, pStart + count, pEnd - 1, i + 1, iEnd);

        return new Node( inOrder[i],left,right );
    }

    //遍历
    public static void levelTravel( Node root ) {

        if( root == null ) return;
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> next = new LinkedList<>();

        StringBuilder sb = new StringBuilder();

        queue.addLast( root );
        while ( !queue.isEmpty() ) {
            Node node = queue.removeFirst();
            sb.append( " " ).append(node.data);
            if( node.lChild != null ) {
                next.addLast( node.lChild );
            }
            if( node.rChild != null ) {
                next.addLast( node.rChild );
            }
            if( queue.isEmpty() ) {
                queue.addAll( next );
                next.clear();
            }
        }
        System.out.println( sb );
    }

    public static List<List<Integer>> levelTravelII( Node root ) {

        if( root == null ) return new LinkedList<>();
        LinkedList<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> before = new LinkedList<>();

        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> next = new LinkedList<>();

        queue.addLast( root );
        while ( !queue.isEmpty() ) {
            Node node = queue.removeFirst();
            before.addLast( node.data );
            if( node.lChild != null ) {
                next.addLast( node.lChild );
            }
            if( node.rChild != null ) {
                next.addLast( node.rChild );
            }
            if( queue.isEmpty() ) {
                queue.addAll( next );
                next.clear();
                result.addFirst( before );
                before = new LinkedList<>();
            }
        }
        return result;
    }
}
