package Leet1;

import java.util.Stack;

/*
    路径总和
    查看 是否存在 根节点到叶子节点的结点值的和 等于 给定的值
 */
public class Leet112 {

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
        Node node5 = new Node(11, null, null);
        Node node4 = new Node(13, null, null);
        Node node3 = new Node(4, null, null);

        Node node2 = new Node(4, node5, null);
        Node node1 = new Node(8, node4, node3);

        Node node = new Node(5, null, node1);

        System.out.println(pathSum( node,5 ));
    }

    /*
        递归版的路径求和
     */
    public static boolean recursivePathSum( Node node ,int sum ) {
        if( node == null ) return sum == 0;
        return recursivePathSum(node.lChild,sum - node.data) || recursivePathSum(node.rChild,sum - node.data);
    }

    /*
        非递归版路径求和
        前序遍历 无法完成 : 可以得到结论，  凡是需要路径和这样的回溯法，前序和中序都无法完成

        应使用后序遍历
        对于边界的判断，如  根节点 没有两棵树的情况下，应该提前判断!!! 即放在 方法的开始处，或者在结束时统一判断
     */
    public static boolean pathSum( Node node,int sum ) {

        if( node == null ) return sum == 0;

        Stack<Node> stack = new Stack<>();
        Node p = node;

//        if(( node.lChild == null && node.rChild == null )&& sum == node.data ) return true;

        int treeSum = 0;
        while ( !stack.isEmpty() || p != null ) {
            if( p.lChild != null ) {
                stack.push( p );
                treeSum+=p.data;
                p = p.lChild;
            }else if( p.rChild != null ) {
                stack.push( p );
                treeSum+=p.data;
                p = p.rChild;
            }else {
                treeSum+=p.data;
                if( treeSum == sum ) return true;
                Node parent = p;
                Node child = null;
                while ( !stack.isEmpty() && ( stack.peek().rChild == null || stack.peek().rChild == parent ) ) {
                    //post visit
                    child = parent;
                    treeSum-=parent.data;
                    parent = stack.pop();
                }
                //说明完成遍历了
                if( stack.isEmpty() && ( parent.rChild == null || parent.rChild == child ) ) {
                    //post  visit
//                    对于边界的判断，如  根节点 没有两棵树的情况下，应该提前判断!!! 即放在 方法的开始处，或者在结束时统一判断
                    return parent.rChild == null && parent.lChild == null && treeSum == sum;
                }
                if( stack.isEmpty() ) {
                    p = parent.rChild;
                    stack.push( parent );
                    //!!
                    treeSum+=parent.data;
                }else {
                    p = stack.peek().rChild;
                    //!!
                    treeSum-=parent.data;
                }
            }
        }
        return false;
    }
}
