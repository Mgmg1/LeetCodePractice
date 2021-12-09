package Leet1;


import java.util.HashMap;
import java.util.Map;

/*
    LRU缓存机制
    使用栈实现.....栈大小固定，

    当数据已存在栈中，将数据提送到栈顶
    当访问新的数据时
        栈空间足够，压入栈
        栈空间不足时，移除栈底元素

    方法一：具体来说，适合栈 配合hashMap 因为要移动位置还要确定元素是否存在于栈中


    方法二：使用双向链表，和hashmap(用于索引到节点，而不是方法一那样索引到上一个节点)
    根据hashmap确定节点是否存在：
        存在则取出自身，并放到链表头
        否则， 将新节点放入链表头(空间不够时，先取下tail节点)
 */
public class Leet146 {

    //假定元素是不可重复,因为需要根据数据确定是否有重复元素。
    private static class LRU<T> {
        private class LNode {
            T data;
            LNode next;
        }
        int maxSize = 5; // 不能为0
        int size;
        LNode head;
        LNode tail;
        Map<T,LNode> map = new HashMap<>(); //取得目标数据所在节点地上一个节点
        //链表进出方向反了。。。
        public LNode get(T data) {
            if (map.containsKey(data)) {
                LNode prior = map.get(data);
                if (prior != null) {
                    LNode p = prior.next;
                    if (p.next != null) {
                        map.put(p.next.data, prior);
                        prior.next = p.next;
                    }else {
                        head = prior;
                    }
                    p.next = tail;
                    map.put( tail.data,p );
                    map.put( p.data,null );
                }
            }
            //达到最大容量出队
            if (size == maxSize) {
                LNode lNode = map.remove(head.data);
                if (lNode == null) {
                    tail = null;
                    head = null;
                }else {
                    lNode.next = null;
                    head = lNode;
                }
                size--;
            }
            //入队
            LNode newNode = new LNode();
            if (head == null) {
                head = newNode;
                newNode.data = data;
                tail = head;
                map.put(data, null);
            } else {
                newNode.data = data;
                newNode.next = tail;
                map.put(tail.data, newNode );
                map.put( data,null );
                tail = newNode;
            }
            size++;
            return newNode;
        }
    }

    public static void main(String[] args) {
        LRU<Integer> integerLRU = new LRU<>();
        for (int i = 0; i < 10; i++) {
            integerLRU.get( i%6 );
        }
        return;
    }

    public static void way2 () {
        //to-do
    }

}
