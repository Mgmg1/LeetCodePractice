package Leet1;

/*
    翻转链表，从 m 到 n
 */
public class Leet92 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {

        Node head = createNodeList(10);
        travleNodeList( head );
        reverseNodeList( head,4,5);
        travleNodeList( head );

    }

    /*
        对于 循环 i 之类的代码 ，可以使用  <= , <  >= , >= 就使用，不要使用  ！= 号
        否则在边界情况下可能会出错！！！
     */
    private static void reverseNodeList( Node head , int m ,int n) {

        if( m >= n ) return;
        if( m < 1 ) return;
        int i = 1;
        Node p = head;
        while ( p.next != null && i != m ) {
            p = p.next;
            i++;
        }
        if( p.next == null ) return;
        // 此时可以把 p 看成新的 head；
        Node prior = null,temp = null;
        Node q = p.next;
        // 使用 i <= n + 1 而不是  i != n + 1
        while ( p.next != null &&  i <= n  ) {
            temp = p.next;
            p.next = p.next.next;
            temp.next = prior;
            prior = temp;
            i++;
        }
        q.next = p.next;
        p.next = prior;
    }


    /*
        打印遍历字符串
     */
    private static void travleNodeList( Node head) {

        StringBuilder stringBuilder = new StringBuilder();
        while ( head.next != null) {
            head = head.next;
            stringBuilder.append(" " + head.data);
        }
        System.out.println( stringBuilder );
    }


    /*
        生成 具有 1~n 的值的 链表
        包含头节点。总共 n+1的node
     */
    private static Node createNodeList( int n ) {

        Node head = new Node();
        if( n <= 0 ) return head;

        Node p = head;
        int i = 1;
        while ( i <= n ) {
            p.next = new Node();
            p.next.data = i;
            p = p.next;
            i++;
        }
        return head;
    }


}
