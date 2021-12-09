package Leet1;

/*
    合并两个有序列表
    新的链表是通过原节点串成

    为了插入方便，因此比较的形式是  p.next.data,即有插入或者删除操作时，
    最好将循环条件设置为 p.next != null ,这样发现目标节点时，p 就是目标节点的前一个结点
    !!!!
    当 q 在循环开头通过一个 q = q.next 更新后，
    需要再次改变 q 的指向为 next，可能会有循环头会有空指针异常，因此循环条件改为 q != null 。假设还需要上一个节点，
    则另外设置一个变量
    针对node.next时，循环条件为 q.next != null
    针对node也是当前节点时，循环条件为  q != null
 */
public class Leet21 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {
        Node node = create(1, 5, 7, 12, 15, 20);
        Node node1 = create(2, 4, 6, 13, 14, 19, 22);
        Node node2 = combineSortedNode(node1, node);
        while ( node2.next != null ) {
            node2 = node2.next;
            System.out.println( node2.data );
        }
    }

    public static Node combineSortedNode(Node head1,Node head2 ) {

        Node temp = head1;
        Node p = head1,q = head2.next;
        //
        if( p.next == null ) {
            return head2;
        }
        //将 head2 插入head1,因而循环的条件是 head2 相关（语义是head2还没插完时，继续插入!!），当head2没数据可插入时，可返回head1
        //为了插入方便，因此比较的形式是  p.next.data
        //当 q 在循环开头通过一个 q = q.next 更新后，再次改变q，可能会有循环头会有空指针异常，因此循环条件改为 q != null
        //由于q节点被占用，因此 q.next 不可以可以被赋值!!! 使用 q != null 条件
        while ( q != null  ) {
            if( p.next == null ) {
                p.next = q;
                break;
            }
            if( p.next.data <= q.data ) {
                p = p.next;
            }else {
                temp = q.next;
                q.next = p.next;
                p.next = q;
                q = temp;
                p = p.next;
            }
        }
        return head1;
    }

    private static Node create( int...values ) {
        Node head = new Node();
        Node p = head;
        for (int value :
                values) {
            p.next = new Node();
            p.next.data = value;
            p = p.next;
        }
        return head;
    }
}
