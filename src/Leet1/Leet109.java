package Leet1;

/*
    有序链表转换为二叉树
    递归的入口条件要考虑到递归 拆分数据规模的可能的情况( 也就是 递归里对 数据规模的拆分逻辑
     例如二分， 3/2 = 1  4/2 = 2  2/2 = 1,   因此极限情况下可能拆分为 1    )
     也可能出现 1 ， 2 ， 3 单独处理的情况，这要根据入口的处理基准 （例如，本题的基准受到 getPivot() 限制 ）
 */
public class Leet109 {

    static class LNode {
        int data;
        LNode next;
    }

    static class BNode {
        int data;
        BNode lChild;
        BNode rChild;
        public BNode(int data, BNode lChild, BNode rChild) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {

        LNode nodeList = getNodeList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        BNode bNode = way1(nodeList.next, null);
        inorderTravel( bNode );

    }

    public static void inorderTravel( BNode root) {
        if( root != null ) {
            inorderTravel( root.lChild );
            System.out.println( root.data );
            inorderTravel( root.rChild );
        }
    }

    public static BNode way1( LNode node,LNode end) {

        if( node == end ) {
            return null;
        }

        if( node.next == end ) {
            return new BNode(node.data, null, null);
        }


        LNode pivot = getPivot(node,end);
        BNode lNode = way1(node,pivot);
        BNode rNode = way1(pivot.next,end);
        return new BNode(pivot.data,lNode,rNode);

    }


    /*
        不包含头节点
     */
    public static LNode getPivot(LNode start,LNode end) {
        if( start == end  ) {
            return end;
        }

        if( start.next == end ) {
            return start;
        }

        LNode p = start.next;
        LNode q = start;
        while ( p.next != end && p.next.next !=end ) {
            p = p.next.next;
            q = q.next;
        }
        if( p.next == end ) return q;
        return q.next;
    }

    public static LNode getNodeList(int...values ) {
        LNode head = new LNode();
        LNode p = head;
        if( values != null ) {
            for (int value :
                    values) {
                LNode next = new LNode();
                next.data = value;
                p.next = next;
                p = next;
            }
        }
        return head;
    }

}
