package Leet1;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    删除排序链表中的重复元素II

    删除重复的节点，不要有剩余

 */
public class Leet82 {

    private static class Node {
        int data;
        Node next;
    }

    public static void main(String[] args) {

        Node list = createList(10, 3, Stream.of(2, 5, 6, 8).collect(Collectors.toList()));
        removeSortListDuplicateElement( list );
        travelList( list );
    }

    /*
        数组中寻找，连续相同的元素的 第一个不同的元素下标时
        假如 使用 i + 1,则循环停止后，i恰好为最后一个连续相同的元素  ( 效果相当于 p.next != null 的循环 )
        假如 使用 i - 1,则循环停止后，i恰好为第一个不同元素的下标   ( 效果相当于 p != null 的循环 )
     */
    public static Node removeSortListDuplicateElement(Node head) {

        Node p = head;
        Node before = p;
        while (p.next != null) {
            p = p.next;
            int count = 1;
            while (p.next != null && p.next.data == p.data) {
                p = p.next;
                count++;
            }
            if (count > 1) {
                //假如此时使用c语言，则需要释放节点内存
                before.next = p.next;
            }else {
                before = p;
            }
        }
        return head;
    }

    private static void travelList(Node head) {
        while (head.next != null) {
            head = head.next;
            System.out.println(head.data);
        }
    }

    /*
        创建从 从 0 ~ n 的链表
     */
    private static Node createList(int n, int count, List<Integer> targets) {

        Node head = new Node();
        Node p = head;
        int times = 0;
        int timess = 1;
        for (int i = 0; i <= n; i++) {
            times = 0;
            timess = 1;
            if (targets.contains(i)) {
                timess = count;
            }
            while (times < timess) {
                p.next = new Node();
                p.next.data = i;
                p = p.next;
                times++;
            }
        }
        return head;
    }
}