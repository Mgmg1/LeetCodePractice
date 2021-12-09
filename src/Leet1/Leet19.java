package Leet1;


/*
    删除链表倒数第n个节点
    foreach循环前需要判空
    思路：
    让其中一个指针先跑n步，想象有
    p:head->7->6->5->4->3->2->1->null
    q:head->7->6->5->4->3->2->1->null
    假设删除倒数第3个，当 p== null的时候，q在3处，即p比q多找三步，
    也就是先让p先执行三次 p = p.next
    可以用 p.next != null 作循环判断，删除时会比较方便
    要保证 n > 0 ,否则q.next = q.next.next 会引发空指针
 */
public class Leet19 {


    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {

        int[] values = new int[9];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        Node node = create(values);
        deleteLastNNode( node,10);
        while ( node.next != null ) {
            node = node.next;
            System.out.println( node.data );
        }
    }


    public static Node create( int...values ) {

        Node head = new Node();
        Node p = head;
        // foreach循环前需要判空
        for (int value:
             values) {
            p.next = new Node();
            p.next.data = value;
            p = p.next;
        }
        return head;
    }

    public static boolean deleteLastNNode( Node head, int n) {
        int i = 0;
        Node p = head;
        //先让 p 比 q 多执行 n 次 p = p.next
        while ( p.next != null && i < n) {
            i++;
            p = p.next;
        }
        if( i < n || n <= 0 ) {
            return true;
        }
        Node q = head;
        while ( p.next != null ) {
            p = p.next;
            q = q.next;
        }
        //这里没有释放内存
        //当  n == 0 时，会引发空指针，因此要保证 n > 0
        q.next = q.next.next;
        return true;
    }

}
