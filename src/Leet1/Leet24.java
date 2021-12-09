package Leet1;

/*
    两两交换链表中的节点
    需要用到两个临时变量,注意要画图，不然容易乱
    两种思路：
        1.两个分别交换 （ 本题思路 ）
        2.以长度为2的幅度翻转指向 指向也很乱。。。需要画图
    教训：不要吝惜使用临时交换变量，否则会被绕晕，即使画图也是

    想象每一次循环后链表或者数据的变化。这样就是推出每次循环的效果，不至于被绕晕
    head -> 1->2->3->4   p = head   =>
    head -> 2->1->3->4   p = 1节点

 */
public class Leet24 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {
        Node nodeList = createNodeList(new int[]{1, 2, 3, 4, 5, 6, 7,8,9});
//        interchangeNode( nodeList );
        interchangeNode2( nodeList );
        while ( nodeList.next != null ) {
            nodeList = nodeList.next;
            System.out.println( nodeList.data );
        }
    }


    public static void interchangeNode( Node head ) {

        Node p = head;
        Node temp = null;
        Node temp2 = null;
        while ( p.next != null && p.next.next != null ) {
            temp2 = p.next.next.next;
            temp = p.next;
            p.next = p.next.next;
            p.next.next = temp;
            temp.next = temp2;
            p = temp;
        }
    }

    public static void interchangeNode2( Node head ) {

        Node p = head;
        Node temp = null;
        //翻转幅度为2
        while ( p.next != null && p.next.next != null ) {
            temp = p.next.next.next;
            p.next.next.next = p.next;
            p.next = p.next.next;
            p.next.next.next = temp;
            p = p.next.next;
        }

    }


    private static Node createNodeList( int[] values) {
        Node head = new Node();
        Node node = head;
        for (int val :
                values) {
            node.next = new Node();
            node = node.next;
            node.data = val;
        }
        return head;
    }
}
