package Leet1;

import java.util.LinkedList;

/*
    二叉的最深深度
    思路1：
        1.递归定义取得
        2.层次遍历记录最深深度
           开始遍历新一层时，有变量：
              一层的总个数levelCount ,一层当前已遍历的个数currentCount，
              遍历的同时记录下下一层的个数(nextLevelCount)
              当 currentCount == levelCount, 当前层结束
               然后更新currentCount，levelCount，nextLevelCount

 */
public class Leet104 {

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

        Node node11 = new Node(21, null, null);
        Node node1 = new Node(4, node11, null);
        Node node2 = new Node(55, null, null);
        Node node3 = new Node(23, node1, node2);

        Node node44 = new Node(11, null, null);
        Node node4 = new Node(2, node44, null);
        Node node5 = new Node(3, node3, null);
        Node node6 = new Node(1, node4, node5);

        System.out.println(recurMaxDepth(node6));
        System.out.println( maxDepth( node6 ) );
    }

    public static int recurMaxDepth(Node node) {

        return node != null ? Math.max(recurMaxDepth(node.lChild), recurMaxDepth(node.rChild)) + 1 : 0;

    }

    public static int maxDepth(Node node) {

        if (node == null) return 0;

        LinkedList<Node> queue = new LinkedList<>();

        int levelCount = 1;
        int currentCount = 0;
        int nextLevelCount = 0;
        int depth = 0;

        queue.addLast(node);
        while (!queue.isEmpty()) {

            Node node1 = queue.removeFirst();
            if (node1.lChild != null) {
                nextLevelCount++;
                queue.addLast(node1.lChild);
            }
            if (node1.rChild != null) {
                nextLevelCount++;
                queue.addLast(node1.rChild);
            }
            currentCount++;
            if (currentCount == levelCount) {
                depth++;
                levelCount = nextLevelCount;
                currentCount = 0;
                nextLevelCount = 0;
            }
        }
        return depth;
    }
}
