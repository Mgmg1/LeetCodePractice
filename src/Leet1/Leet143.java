package Leet1;

/*
    重排链表

 */
public class Leet143 {

    private static class LNode {
        int data;
        LNode next;
        public LNode(int data, LNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LNode lNode = generateLNodeList(new int[]{1,2,3,4});
//        reSort( lNode );
        reSort2( lNode );
        while ( lNode.next != null ) {
            lNode = lNode.next;
            System.out.println( lNode.data );
        }
    }

    /*
        无序链表到有序链表
        要使用插入排序
     */
//    public static void lNodeListToSorted(LNode head ) {
//
//        if( head == null || head.next == null ) return;
//
//        LNode p = head.next;
//        LNode prior = head;
//        while ( p != null ) {
//            if( prior == head || prior.data <= p.data  ) {
//                prior = p;
//                p = p.next;
//            }else {
//                LNode temp = p;
//                prior.next = p.next;
//                p = head;
//                while ( p.next!= prior.next && p.next.data < temp.data ) {
//                    p = p.next;
//                }
//                temp.next = p.next;
//                p.next = temp;
//
//                p = prior.next;
//            }
//        }
//    }

    /*
        效率较低的做法：
        每次取下在最后的节点，再插入。时间复杂度为O(n^2)
     */
    public static void reSort( LNode head ) {

        if( head == null || head.next == null  ) return ;

        LNode p = head.next;
        LNode q = p;

        while ( p != null ) {
            while ( p.next != null && p.next.next != null) {
                p = p.next;
            }
            if( p.next != null ) {
                LNode temp = p.next;
                p.next = null;
                temp.next = q.next;
                q.next = temp;
            }else {
                return;
            }
            q = q.next;
            p = q;
        }
    }

    public static LNode generateLNodeList( int[] elements ) {
        LNode head = new LNode(0,null);
        if( elements == null || elements.length == 0 ) return head;

        LNode p  = head;
        for (int ele :
                elements) {
          p.next = new LNode(ele,null);
          p = p.next;
        }
        return head;
    }

    /*
        取下后半部分，翻转，再插入原链表
     */
    public static LNode reSort2( LNode head  ) {
        //保证长度至少为2，长度小于2时，可以直接返回
        //等于2时也可直接返回。。。。
        if( head == null || head.next == null || head.next.next == null ) {return head;}
        LNode p = head,q = head;
        while ( p.next != null && p.next.next != null ) {
            q = q.next;
            p = p.next.next;
        }

        LNode temp = q; // 临时变量，用于交换值和临时保存变量
        //取下后半段链表！！
        if( p.next == null) {
            q = q.next;
            temp.next = null; //必须置null!!否则链表会连成一个环
        }else {
            q = q.next.next;
            temp.next.next = null; //必须置null!!否则链表会连成一个环
        }

        q = reverseNode(q);//翻转
        //再插入
        p = head;

        //链表合并
        //关于循环条件：可能出现两种情况,q.next != null, 而 q == null 以及 q == null 并且 q.next == null;
        while ( q != null && p.next != null ) {
            p = p.next;

            temp = q.next;
            q.next = p.next;
            p.next = q;
            p = q;

            q = temp;
        }
        return head;
    }

    /*
        不带头节点翻转
     */
    public static LNode reverseNode( LNode node ) {
        if ( node == null ) {return null;}
        if( node.next == null ) {return node;}

        LNode temp = node,prior = null,nextNode = null;
        while ( temp != null ) {
            nextNode = temp.next;
            temp.next = prior;
            prior = temp;
            temp = nextNode;
        }
        return prior;
    }
}
