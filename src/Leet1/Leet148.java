package Leet1;


/*
    排序链表
    灵感：
        归并排序的非递归版本(自底向上)
        快速排序（递归）
 */
public class Leet148 {

    private static class LNode {
        int data;
        LNode next;
    }

    public static void main(String[] args) {
        LNode lNode = generateLNodeList(new int[]{3,5,4,2, 1});
        sortList( lNode );
//        quickSortList( lNode );
        while ( lNode.next != null ) {
            lNode = lNode.next;
            System.out.println( lNode.data );
        }
    }

    /*
        归并排序的非递归版本 时间复杂度O(nlogn) 空间复杂度O(1)
     */
    private static void sortList( LNode head ) {
        if( head == null || head.next ==null || head.next.next == null ) {return;}

        int length = 0;
        LNode p = head;
        while ( p.next!= null ) {
            length++;
            p = p.next;
        }
        LNode h1 = new LNode();
        LNode h2 = new LNode();
        int leave = length;
        int interval = 1;
        while ( interval < length ) {
            p = head;
            LNode temp = null;
            leave = length;
            while ( leave > interval ) {
                LNode t1 = findNotExceedNLNode(p, interval);
                LNode t2 = findNotExceedNLNode(t1, interval);
                h1.next = p.next;
                h2.next = t1.next;
                t1.next = null;
                temp = t2.next;
                t2.next = null;
                LNode t3 = toOneSortList(h1, h2);
                t3.next = temp;
                p.next = h1.next;
                p = t3;
                leave-=count;
            }
            interval*=2;
        }
    }

    /*
        合并后，头节点为h1
        返回尾节点，只有头节点时返回 null
     */
    static int count = 0;
    private static LNode toOneSortList( LNode h1,LNode h2) {
        count = 0;
        LNode p = h1;
        h1 = h1.next;
        h2 = h2.next;
        while ( h1 != null || h2 != null ) {
            if( h2 == null || ( h1 != null && h1.data < h2.data ) ) {
               p.next = h1;
               h1 = h1.next;
            }else {
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;
            count++;
        }
        return p;
    }

    /*
        形成一条至多count个节点的链表
        并返回尾节点
     */
    private static LNode findNotExceedNLNode(LNode h, int count) {
        int i = 0;
        while ( h.next != null && i < count ) {
            h = h.next;
            i++;
        }
        return h;
    }

    private static LNode generateLNodeList(int[] values ) {
        LNode head = new LNode();
        if (values == null || values.length == 0) return head;
        LNode p = head;
        for (int value :
                values) {
            p.next = new LNode();
            p.next.data = value;
            p = p.next;
        }
        return head;
    }

    static LNode h1 = new LNode();
    static LNode h2 = new LNode();
    /*
        返回不带头节点的链表。根据快速排序改造
     */
    public static LNode quickSortList( LNode h ) {
        if( h == null || h.next == null ) {return null;}
        if( h.next.next == null ) {return h.next;}

        LNode pivot = h.next;
        LNode left = h1;
        LNode right = h2;
        LNode p = h.next;
        while ( p.next != null ) {
            p = p.next;
            if( p.data <= pivot.data ) {
                left.next = p;
                left = left.next;
            }else {
                right.next = p;
                right = right.next;
            }
        }
        left.next =null;
        right.next =null;

        LNode temp1 = h1.next;
        LNode temp2 = h2.next;

        LNode ch1 = null;
        LNode ch2 = null;
        if( temp1 != null ) {
            ch1 = quickSortList( h1 );
        }
        if( temp2 != null  ) {
            //上一个递归中，h2.next可能被修改
            h2.next = temp2;
            ch2 = quickSortList( h2 );
        }

        pivot.next = ch2;
        if( ch1 != null ) {
            p = ch1;
            //ch1得到最后一个节点，以便和pivot连接
            while ( p.next != null ) {
                p = p.next;
            }
            p.next = pivot;
            h.next = ch1;
            return ch1;
        }else {
            h.next = pivot;
            return pivot;
        }
    }
}
