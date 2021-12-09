package Leet1;

/*
    对链表进行插入排序
    需要两个变量 prior1 和 prior2 临时存储上一个节点，以便能够进行插入删除操作
 */
public class Leet147 {

    private static class LNode {
        int data;
        LNode next;
    }

    public static void main(String[] args) {
        LNode lNode = generateLNodeList(new int[]{6,5, 4, 3, 2, 1});
        sortList( lNode );
        while ( lNode.next != null ) {
            lNode = lNode.next;
            System.out.println( lNode.data );
        }
    }

    public static void sortList(LNode head) {
        if( head == null || head.next == null || head.next.next == null ) {return;}
        LNode p = head.next;
        LNode q = null;
        LNode prior1 = null;
        LNode prior2 = null;
        while ( p.next!= null ) {
            prior1 = p;
            p = p.next;
            q = head;
            while ( q.next != p) {
                prior2 = q;
                q = q.next;
                if( q.data >= p.data ) {
                    prior1.next = p.next;
                    p.next = prior2.next;
                    prior2.next = p;
                    p = prior1;
                    break;
                }
            }
        }
    }

    private static LNode generateLNodeList( int[] values ) {
        LNode head = new LNode();
        if (values == null || values.length == 0) return head;
        LNode p = head;
        for (int value :
                values) {
            p.next = new LNode();
            p.next.data = value;
            p = p.next;
        }
        return head;
    }
}
