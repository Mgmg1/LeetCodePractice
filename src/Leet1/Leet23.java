package Leet1;

/*
    合并k个排序链表。。由两个有序链表合并扩展
    假如 q 并入 p，则循环条件为q!=null
    q.data 和 p.next.data 作比较。这样才可以q插入p
    两个升序的数组合并时，如 q 并入 p ，则当 p元素大于等于 q的时，才并入q。降序则相反。
 */
public class Leet23 {


    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {

        int[][] nodess = {
                {1,4,5},
                {1,3,4},
                {2,6}
        };
        Node node = combineListNode(createNodeList(nodess));
        while ( node.next != null ) {
            node = node.next;
            System.out.println( node.data );
        }

    }

    public static Node combineListNode(Node[] nodes ) {

        Node head = new Node();
        if( nodes == null || nodes.length == 0 ) {
            return head;
        }
        head.next = nodes[0];
        Node temp = null;
        for (int i = 1; i < nodes.length; i++) {
            Node p = nodes[i];
            Node q = head;
            while ( p != null ) {
                if( q.next == null ) {
                    q.next = p;
                    break;
                }
                if( q.next.data >= p.data ) {
                    temp = p.next;
                    p.next = q.next;
                    q.next = p;
                    p = temp;
                    q = q.next;
                }else {
                    q = q.next;
                }
            }
        }
        return head;
    }

    public static Node[] createNodeList(int[][] nodess) {

        Node[] result = new Node[nodess.length];
        int j = 0;
        for (int[] nodes :
                nodess) {
            Node fisrt = null;
            for (int i = 0; i < nodes.length; i++) {
                if( fisrt == null ) {
                    fisrt = new Node();
                    fisrt.data = nodes[i];
                    result[j] = fisrt;

                }else {
                    fisrt.next = new Node();
                    fisrt = fisrt.next;
                    fisrt.data = nodes[i];
                }
            }
            j++;
        }
        return result;
    }
}
