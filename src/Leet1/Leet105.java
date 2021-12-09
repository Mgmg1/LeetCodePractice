package Leet1;

import java.util.LinkedList;

/*
    从前序遍历序列和中序遍历序列生成二叉树
    思路：
        1.前序遍历的第一个节点为 树的根节点，找到改节点在中序数组的下标
        2. 得到左子树节点范围
        3.

 */
public class Leet105 {

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

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        Node node = generateBSTree(preorder, inorder, 0, preorder.length - 1,0, preorder.length - 1);
        levelTravel( node );
    }

    public static Node generateBSTree( int[] preorder,int[] inorder,
                                       int pStart,int pEnd,int iStart,int iEnd) {
        if( preorder == null || inorder == null || pStart > pEnd ) return null;
        if( pStart == pEnd ) {
            return new Node(preorder[pStart],null,null);
        }
        int i = iStart;
        for (; i <= iEnd && preorder[pStart] != inorder[i]; i++) {}
        int count = i - iStart;
        Node left = generateBSTree(preorder, inorder, pStart + 1, pStart + count,iStart,i-1);
        Node right = generateBSTree(preorder, inorder, pStart + count + 1,pEnd,i + 1,iEnd);
        return new Node(preorder[pStart], left, right);
    }

    public static void levelTravel( Node root ) {

        if( root == null ) return;

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast( root );
        while ( !queue.isEmpty() ) {
            Node node = queue.removeFirst();
            System.out.println( node.data );
            if( node.lChild != null ) {
                queue.addLast( node.lChild );
            }
            if( node.rChild != null ) {
                queue.addLast( node.rChild );
            }
        }

    }
}
