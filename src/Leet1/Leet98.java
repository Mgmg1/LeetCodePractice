package Leet1;

/*
    验证二叉搜索树
    思路：
        1.中序遍历比较
        2.递归比较，得到左子树的最小值，和右子树最大值

 */
public class Leet98 {

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

    private static Node prior;

    public static void main(String[] args) {

        Node node = new Node(1,null,null);
        Node node1 = new Node(2,node,null);

        Node node2 = new Node(4,null,null);
        Node node3 = new Node(6,null,null);
        Node node4 = new Node(5,node2,node3);

        Node node5 = new Node(3,node1,node4);
//        System.out.println( isBinarySearchTree1(node5) );
        System.out.println( isBinarySearchTree2(node5) );
//        System.out.println( recurIsBSTree(node5) );
    }

    /*
        中序遍历一个写法： 不会遍历到null
        需要比较 left 和 right 是否为null，很麻烦

     */
    public static boolean isBinarySearchTree1( Node node ) {

        if(  node == null  ) {
            prior = node;
            return true;
        }

        if( node.lChild != null ) {
            if(!isBinarySearchTree1(node.lChild)) {
                return false;
            }
        }
        if( prior !=null && node.data < prior.data ) {
            return false;
        }
        prior = node;
        if( node.rChild == null ) {
            if(!isBinarySearchTree1(node.rChild)) {
                return false;
            }
        }
        return true;
    }

    /*
        中序遍历另一个写法：一般写法，会遍历到null
     */
    public static boolean isBinarySearchTree2( Node node ) {

        if( node == null ) {
            return true;
        }
        if( !isBinarySearchTree2( node.lChild ) ) {
            return false;
        }
        if(  prior != null && node.data < prior.data ) {
            return false;
        }
        prior = node;
        if( !isBinarySearchTree2( node.rChild ) ) {
            return false;
        }
        return true;
    }

    /*
        递归定义的解法
     */
    public static boolean recurIsBSTree( Node node ) {

        if( node == null ) return true;
        Node max = findMax(node.lChild);
        if( max != null && max.data > node.data ) return false;
        Node min = findMin(node.rChild);
        if ( min != null && min.data < node.data ) return false;

        if( !recurIsBSTree( node.lChild ) ) return false;
        if( !recurIsBSTree( node.rChild ) ) return false;

        return true;
    }

    private static Node findMin ( Node root ) {
        if( root == null ) return null;
        while ( root.lChild != null ) {
             root = root.lChild;
        }
        return root;
    }

    private static Node findMax ( Node root ) {
        if( root == null ) return null;
        while ( root.rChild != null ) {
            root = root.rChild;
        }
        return root;
    }
}
