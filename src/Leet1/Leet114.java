package Leet1;

/*
    二叉树展开为链表
 */
public class Leet114 {

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

    private static class LNode {
        int data;
        LNode next;
    }

    public static void main(String[] args) {

        Node node5 = new Node(11, null, null);
        Node node6 = new Node(17,null,null);

        Node node4 = new Node(13, null, null);
        Node node3 = new Node(4, null, null);

        Node node2 = new Node(4, node5, node6);
        Node node1 = new Node(8, node4, node3);

        Node node = new Node(5, node2, node1);

        LNode head = new LNode();
        //        要保证  node 输入不为 null
        recursiveBSTree2List( head,node );



    }

    /*
        递归
        前面的 prior，后面的， next
        中间的 pivot

     */
    public static LNode recursiveBSTree2List(LNode prior,Node root) {

        LNode lNode = new LNode();
        lNode.data = root.data;
        prior.next = lNode;
        prior = lNode;

        //需要返回prior以得到链表的尾节点
        //另一个思路是设置成一个循环链表，然后不断进行尾插入 。。 这样即不需要返回尾节点了

        if( root.lChild != null ) {
            prior = recursiveBSTree2List(prior, root.lChild);
        }
        if( root.rChild != null ) {
            prior = recursiveBSTree2List(prior, root.rChild);
        }
        return prior;
    }
}
