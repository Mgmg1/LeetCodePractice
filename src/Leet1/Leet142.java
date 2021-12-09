package Leet1;

/*
    环形链表
 */
public class Leet142 {

    static class Node {
        int val;
        Node next;
        public Node ( int val){ this.val = val; }
        public Node (){}
    }

    public static void main(String[] args) {
        Node head = new Node();
        int length = 20;
        int randomEntry = (int) (7 + Math.random() * 10);
        Node p = head;
        Node entry = null;
        for (int i = 0; i < length; i++) {
            Node node = new Node(i);
            p.next = node;
            p = node;
            if( randomEntry == i ) {
                entry = node;
            }
        }
        p.next = entry;
        System.out.println( randomEntry );
        System.out.println(isCircuitList(head));

    }

    public static int isCircuitList(Node head) {

        Node p = head,q = head;
        do {
            p = p.next;
            q = q.next.next;
        }while ( q != p );
        p = head;
        while ( p != q ) {
            p = p.next;
            q = q.next;
        }
        return q.val;
    }

}
