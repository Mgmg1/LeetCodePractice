package Leet1;

import java.util.List;

/*
    分隔链表
    要求大于 target 的 节点的相对位置不变
    思路：
        找到第一个的大于等于target之前的元素p
        往后，一旦找到小于target的元素，删除并插入p后
 */
public class Leet86 {

    public static void main(String[] args) {

        Node head = new Node();
        head.add(1).add(4).add(3).add(2).add(5).add(2);
        int target = 3;
        separateList( head,target);
        travelList( head );
    }

    private static class Node {
        int data;
        Node next;
        Node add(int val) {
            this.next = new Node();
            this.next.data = val;
            return this.next;
        }
    }


    private static void travelList(Node head) {
        while (head.next != null) {
            head = head.next;
            System.out.println(head.data);
        }
    }

    public static void separateList( Node head ,int target) {

        Node p = head;
        Node q = null;
        while ( p.next != null && p.next.data < target ) {
            p = p.next;
        }
        q = p;

        while ( q != null && q.next != null ) {
            if( q.next.data < target ) {
                //摘下 q.next 节点
                Node temp = q.next;
                q.next = q.next.next;
                //插入节点
                temp.next = p.next;
                p.next = temp;
            }else {
                q = q.next;
            }
        }
    }

}
