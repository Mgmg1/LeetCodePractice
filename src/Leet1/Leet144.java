package Leet1;

import java.util.Stack;

/*
    二叉树前序遍历
 */
public class Leet144 {

    static class BSNode {
        int data;
        BSNode lChild;
        BSNode rChild;
        public BSNode(int data, BSNode lChild, BSNode rChild) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {
        BSNode bsNode3 = new BSNode(3,null,null);
        BSNode bsNode4 = new BSNode(4,null,null);
        BSNode bsNode1 = new BSNode(1,bsNode3,bsNode4);
        BSNode bsNode2 = new BSNode(2,null,null);
        BSNode root = new BSNode(0,bsNode1,bsNode2);
//        recursiveInOrderTravel( root );
        inOrderTravel( root );
    }

    public static void recursiveInOrderTravel( BSNode root ) {
        if( root != null ) {
            System.out.println( root.data );
            recursiveInOrderTravel( root.lChild );
            recursiveInOrderTravel( root.rChild );
        }
    }

    public static void inOrderTravel(BSNode root ) {
        Stack<BSNode> stack = new Stack<>();
        BSNode p = root;
        while ( !stack.isEmpty() || p != null ) {
            if( p != null ) {
                System.out.println( p.data );
                stack.push( p );
                p = p.lChild;
            }else {
                p = stack.pop().rChild;
            }
        }
    }
}
