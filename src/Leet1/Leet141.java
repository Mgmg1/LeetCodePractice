package Leet1;

public class Leet141 {

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
        System.out.println(  isCircuitListExits( head ) );

    }

    public static boolean isCircuitListExits( Node head ) {

        Node p = head,q = head;
        do {
            if( q == null || p == null ) {
                return false;
            }
            p = p.next;
            if( q.next != null ) {
                q = q.next.next;
            }else {
                return false;
            }
        }while ( q != p  );
        return true;
    }
}
