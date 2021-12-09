package Leet1;

/*
     两数相加
 */
public class Leet2 {

    private static class LNode {
        int data;
        LNode next;
    }

    public static void main(String[] args) {
// 测试
//        LNode lNodeList = getLNodeList(new int[]{2, 4, 3});
//        while ( lNodeList.next != null ) {
//            lNodeList = lNodeList.next;
//            System.out.println(lNodeList.data);
//        }

        LNode lNode = addTwoNum(getLNodeList(new int[]{2, 4, 3}), getLNodeList(new int[]{5, 6, 4}));
        while ( lNode.next != null ) {
            lNode = lNode.next;
            System.out.println( lNode.data );
        }

    }

    public static LNode addTwoNum( LNode list1,LNode list2 ) {

        LNode head = new LNode();

        if( list1 == null && list2 == null ) {
            return head;
        }
        if( list1 == null ) {
            return list2;
        }
        if( list2 == null ) {
            return list1;
        }

        LNode head1 = list1,head2 = list2,p = head;
        int num1 = 0,num2 = 0,up = 0;
        while ( head1.next != null || head2.next != null || up != 0 ) {
            if( head1.next != null ) {
                head1 = head1.next;
                num1 = head1.data;
            }else {
                num1 = 0;
            }
            if( head2.next != null ) {
                head2 = head2.next;
                num2 = head2.data;
            }else {
                num2 = 0;
            }
            p.next = new LNode();
            p.next.data = ( up + num1 + num2  ) % 10;
            p = p.next;
            up = ( up + num1 + num2 ) / 10;
        }

        return head;
    }

    public static LNode getLNodeList(int[] arr) {

        LNode head = new LNode();
        LNode p = head;
        for (int i = 0; i < arr.length; i++) {
            LNode lNode = new LNode();
            lNode.data = arr[i];
            p.next = lNode;
            p = lNode;
        }
        return head;
    }



}
