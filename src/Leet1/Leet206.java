package Leet1;

/**
 * 反转链表
 */
public class Leet206 {

    private static class Node {
        int data;
        Node next;
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node node2 = new Node(2, null);
        Node node1 = new Node(1, node2);
        Node head = new Node(0, node1);
        // head->node1->node2->null

        reverseList( head );

//        head->node2->node1->null
        while ( head.next != null ) {
            head = head.next;
            System.out.println( head.data );
        }
    }

    private static void reverseList( Node head ) {

        Node prior = null;
        Node temp = null;
        while ( head.next != null ) {
            temp = head.next;
            head.next = head.next.next;
            temp.next = prior;
            prior = temp;
        }
        head.next = prior;
    }
}
