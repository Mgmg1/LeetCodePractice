package Leet1;

/**
 * 删除链表中给定val的所有节点
 */
public class Leet203 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {
        Node node = initiateNodeList(new int[]{1, 2, 6, 3, 4, 5, 6});
        deleteNode( node,6 );
        while ( node.next != null ) {
            node = node.next;
            System.out.println( node.data );
        }
    }

    private static void deleteNode( Node head,int val ) {
        if( head == null || head.next == null ) { return; }

        Node p = head;
        while ( p.next != null ) {
            if( p.next.data == val ) {
                p.next = p.next.next;
                continue;
            }
            p = p.next;
        }
    }
    private static Node initiateNodeList( int[] vals) {
        Node head = new Node();
        Node p = head;
        for (int val :
             vals) {
            p.next = new Node();
            p.next.data = val;
            p = p.next;
        }
        return head;
    }
}
