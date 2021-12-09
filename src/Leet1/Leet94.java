package Leet1;

import java.util.Stack;

/*
    中序遍历
    思路：
    递归 和 栈
 */
public class Leet94 {

    private static class Node {
        int data;
        Node lChild;
        Node rChild;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        node1.data = 1;
        node1.rChild = node2;
        node2.data = 2;
        node2.lChild = node3;
        node3.data = 3;

        inorderTravel( node1 );
        inorderTravelByStack( node1 );
    }

    /*
        递归
     */
    public static void inorderTravel( Node root ) {
        if( root != null ) {
            inorderTravel( root.lChild );
            System.out.println( root.data );
            inorderTravel( root.rChild );
        }
    }

    public static void inorderTravelByStack( Node root ) {
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while ( !stack.isEmpty() || p != null ) {
            if( p != null ) {
                stack.push( p );
                p = p.lChild;
            }else {
                Node parent = stack.pop();
                System.out.println(parent.data);
                p = parent.rChild;
            }
        }
    }
}
