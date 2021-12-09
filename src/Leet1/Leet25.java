package Leet1;

/*
    k个一组翻转链表
    先统计有多少个元素，然后若剩余小于k则不进行翻转 !!!!
    或先按k个翻转一次，当翻转完成发现不够k个时，最后再翻一次回来！！！
    注意，链表循环计数后要 把 head 重新赋值给 p 。 因为 此时 p是最后一个节点

    翻转的循环不变式， 将 p 后的k个节点翻转,完毕后，p重新指向起点。
    此时可以更新p为翻转后的最后一个节点...



 */
public class Leet25 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {
        Node nodeList = createNodeList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10,11,12,13});
        kReverseNodeList2( nodeList,3 );
        while ( nodeList.next != null ) {
            nodeList = nodeList.next;
            System.out.println( nodeList.data );
        }
    }

    public static void kReverseNodeList( Node head,int k ) {
        Node p = head;
        int length = 0;
        Node temp = null;
        while ( p.next != null ) {
            length++;
            p = p.next;
        }
        p = head;
        while ( length >= k ) {
            int i = 0;
            //假如没有length >= k 保证，则要注意 p.next = null 的情况
            Node j = p.next;
            Node q = null;
            while ( i < k  ) {
                q = p.next;
                p.next = p.next.next;
                q.next = temp;
                temp = q;
                i++;
            }
            length-=k;
            j.next = p.next;
            p.next = temp;
            p = j;
        }
    }

    public static void kReverseNodeList2( Node head,int k ) {

        Node p = head;
        int i = k;
        Node q = null;
        Node temp = null;
        Node j = p.next;
        while ( i == k && j != null ) {
            i = 0;
            while ( i < k && p.next != null ) {
                q = p.next;
                p.next = p.next.next;
                q.next = temp;
                temp = q;
                i++;
            }
            j.next = p.next;
            p.next = temp;
            p = j;
            j = p.next;
        }
        if( j == null ) {
            return;
        }
        //假如 p.next 为 null 说明所有元素被翻转则程序会在上一步被返回
        while ( p.next != null ) {
            q = p.next;
            p.next = p.next.next;
            q.next = temp;
            temp = q;
        }
        j.next = p.next;
        p.next = temp;
    }

    private static Node createNodeList(int[] values) {
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
