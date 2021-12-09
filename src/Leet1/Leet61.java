package Leet1;

/*
    旋转链表
 */
public class Leet61 {

    static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {
        Node head = generateNodes(0,1,2);
        rotateNode(head,4);
        while (head.next!=null){
            head = head.next;
            System.out.println(head.data);
        }
    }

    public static void rotateNode(Node head,int k){
        Node n1 = head;
        Node n2 = head;
        int count = 0;
        int num = 0;
        while (n1.next != null){
            n1 = n1.next;
            num++;
        }
        n1 = head;
        k%=num;
        if(  k==0 ){
            return;
        }
        while( n1.next != null ){
            n1=n1.next;
            if( count < k ){
                count++;
            }else {
                n2=n2.next;
            }
        }
        n1.next = head.next;
        head.next = n2.next;
        n2.next = null;
    }

    public static Node generateNodes( int...nodes ){
        Node head = new Node();
        Node n = head;
        for (int data:
        nodes){
            n.next = new Node();
            n=n.next;
            n.data = data;
        }
        return head;
    }

}
