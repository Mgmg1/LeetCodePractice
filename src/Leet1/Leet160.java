package Leet1;

/*
    相交链表
    思路1：使用两个栈，分别对应两端遍历存储。结束后再同时释放栈节点，当节点不相等时，则上一个节点是 相交节点
    思路2：先计算两端的个数。再让长的一段提前走多的n步，再一起走，当node指针相等时，则该节点就是相交节点
 */
public class Leet160 {

    private static class LNode {
        int data;
        LNode next;
        public LNode(int data, LNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        LNode tail = new LNode(-1,null);
        LNode l11 = new LNode(11,tail);
        LNode l22 = new LNode(22,tail);
        LNode l21 = new LNode(21,l22);
        LNode h1 = new LNode(0,l11);
        LNode h2 = new LNode(0,l21);

        LNode junction1 = getJunction2(h1, h2);
        System.out.println( junction1.data );
    }

    private static LNode getJunction2(LNode h1,LNode h2 ) {
        if( h1 == null || h2 ==null || h1.next == null || h2.next == null ) { return null; }
        int count1 = 0;
        int count2 = 0;
        LNode p1 = h1;
        LNode p2 = h2;
        while ( p1.next != null || p2.next != null ) {
            if( p1.next != null ) {
                p1 = p1.next;
                count1++;
            }
            if( p2.next != null ) {
                p2 = p2.next;
                count2++;
            }
        }
        LNode longer = count1 > count2 ? h1 : h2;
        LNode shorter = count1 > count2 ? h2 : h1;
        int distance = Math.abs( count1 - count2 );
        while ( distance != 0 ) {
            longer = longer.next;
            distance--;
        }
        //longer剩下的节点个数和shorter相等( 不包含头节点 )
        while ( longer.next != null ) {
            if( shorter.next == longer.next ) {
                return shorter.next;
            }
            shorter = shorter.next;
            longer = longer.next;
        }
        return null;
    }
}
