package Leet1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    复制带随机指针的链表
    默认头指针什么也没有
 */
public class Leet138 {

    public static class LNode {
        int data;
        LNode next;
        LNode randomPoint;
    }
    public static void main(String[] args) {
        LNode head = new LNode();
        LNode lNode1 = new LNode();
        LNode lNode2 = new LNode();
        LNode lNode3 = new LNode();
        head.randomPoint = lNode2;
        head.next = lNode1;
        lNode1.next = lNode2;
        lNode1.data = 3;
        lNode1.randomPoint = lNode3;
        lNode2.next = lNode3;
        lNode3.randomPoint = lNode1;
        lNode3.data = 13;
//        copyListWithRandomPointByMap( head );
        copyListWithRandomPoint( head );
    }

    /*
        使用HashMap的办法
     */
    public static LNode copyListWithRandomPointByMap(LNode head ) {
        LNode result = new LNode();
        if( head == null || head.next == null ) {return result;}
        HashMap<LNode, LNode> map = new HashMap<>();
        LNode p = head;
        LNode q = result;
        map.put( p,q );
        while ( p.next != null ) {
            p = p.next;
            LNode lNode = new LNode();
            lNode.data = p.data;

            q.next = lNode;
            q = q.next;

            map.put(p,lNode );
        }
        Set<Map.Entry<LNode, LNode>> entries = map.entrySet();
        for (Map.Entry<LNode, LNode> entry :
                entries) {
            entry.getValue().randomPoint = map.get( entry.getKey().randomPoint );
        }
        return result;
    }

    /*
        取巧的办法
     */
    public static LNode copyListWithRandomPoint( LNode head ) {
        LNode result = new LNode();
        if( head == null || head.next == null ) {return result;}
        LNode p = head;
        // head-> origin1->copy1->origin2->copy2->....->....->null
        while ( p.next != null ) {
            p = p.next;
            LNode copy = new LNode();
            copy.next = p.next;
            copy.data = p.data;
            p.next = copy;
            p = copy;
        }
         p = head.next;
        LNode q = null;
        LNode temp1 = null,temp2 = null;
        //收获：关于链表的遍历，最好只由一个主要的指针的改变进行，这样比较不容易混乱
        //由于链表的随机指针可能会连接到已经遍历过的节点，因此不能一次遍历完成随机指针赋值和链表拆分
        while ( p != null ) {
            q = p.next;
            q.randomPoint = p.randomPoint == null ? null : p.randomPoint.next;
            p = q.next;
        }
        p = head.next;
        q = null;
        //第二次遍历任务是拆分
        while ( p != null ) {
            if( q == null ) {result.next = p.next;}
            q = p.next;
            p.next = p.next.next;
            p = p.next;
            q.next = p == null ? null : p.next;
        }
        return result;
    }
}
