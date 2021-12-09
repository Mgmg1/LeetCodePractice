package Leet1;

import java.util.Stack;

/*
    相同的树
    思路：
        1.两边一边进行非遍历一边比较。减少空间开销。
        2.两边一边进行非遍历一边比较。。。比较复杂
 */
public class Leet100 {

    private static class Node {
        int data;
        Node lChild;
        Node rChild;

        public Node(int data, Node lChild, Node rChild) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }


    public static void main(String[] args) {

        Node node11 = new Node(11, null, null);
        Node node1 = new Node(2, node11, null);
        Node node2 = new Node(3, null, null);
        Node node3 = new Node(1, node1, node2);

        Node node44 = new Node(11, null, null);
        Node node4 = new Node(2, node44, null);
        Node node5 = new Node(3, null, null);
        Node node6 = new Node(1, node4, node5);

        System.out.println(isSameTree(node3, node6));

    }

    public static boolean isSameTree(Node root1, Node root2) {

        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        Node data1 = null, data2 = null;
        Node p1 = root1, p2 = root2;
        boolean flag1 = false, flag2 = false;
        while (true) {
            if (!stack1.isEmpty() || p1 != null) {
                data1 = p1;
                flag1 = true;
                if (p1 != null) {
                    stack1.push(p1);
                    p1 = p1.lChild;
                } else {
                    p1 = stack1.pop().rChild;
                }
            }
            if (!stack2.isEmpty() || p2 != null) {
                data2 = p2;
                flag2 = true;
                if (p2 != null) {
                    stack2.push(p2);
                    p2 = p2.lChild;
                } else {
                    p2 = stack2.pop().rChild;
                }
            }
            if (!((data1 == null && data1 == data2) ||
                    (data1 != null && data2 != null && data1.data == data2.data))) return false;

            if (!flag1 && !flag2) return true;
            if (!flag1 || !flag2) return false;
            flag1 = false;
            flag2 = false;
        }
    }

}
