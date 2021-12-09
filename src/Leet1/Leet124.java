package Leet1;

import java.util.ArrayList;

/*
    二叉树钟的最大路径和
 */
public class Leet124 {

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
        Node node4 = new Node(7, null,null );
        Node node3 = new Node(15, null,null );
        Node node2 = new Node(9,null ,null );
        Node node1 = new Node(20,node3 ,node4 );
        Node node = new Node(-10,node1,node2 );
        System.out.println( maxPath( node,true) );
    }

    /*
        作为该递归的全局变量
     */
    private static Integer leftMax;
    private static Integer rightMax;
    /*
        @return 递归返回的二叉树钟的最大路径和
     */
    public static int maxPath( Node root,boolean direction ) {

        if( root == null ) return leftMax = rightMax = 0;

        int left = maxPath(root.lChild,false);
        //必须存储leftmax，否则会被覆盖
        int maxLeft = leftMax;
        int right = maxPath( root.rChild,true);
        //必须存储maxright，否则会被覆盖
        int maxRight = rightMax;
        int max = Math.max( maxLeft + maxRight + root.data,Math.max( left,right ) );
        if( direction ) {
            rightMax+= root.data;
        }else {
            leftMax+=root.data;
        }
        return max;
    }
}
