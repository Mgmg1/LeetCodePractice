package Leet1;

import java.util.*;

/*
    路径总和II
 */
public class Leet113 {

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
        Node node6 = new Node(17,null,null);

        Node node4 = new Node(13, null, null);
        Node node3 = new Node(4, null, null);

        Node node2 = new Node(4, node5, node6);
        Node node1 = new Node(8, node4, node3);

        Node node = new Node(5, node2, node1);

        int sum = 26;
//        LinkedList<List<Integer>> lists = new LinkedList<>();
//        recursivePathSum( node,lists,new LinkedList<>(), sum );

        List<List<Integer>> lists = pathSum(node, sum);
        System.out.println( lists );

    }


    /*
        递归形式
        不要访问 为 null 的节点( 三个if处理！！ )，否则list会出现重复的值
     */
    public static void recursivePathSum(Node node,
                                        List<List<Integer>> list,
                                        LinkedList<Integer> queue,
                                        int sum) {
        /*
            只有可能根节点为 null，
            往后的递归过程不会出现null 的 节点
         */
        if( node == null ) {
            list.add( new ArrayList<>() );
            return;
        }
        queue.addLast( node.data );
        sum-=node.data;
        if( node.lChild == null && node.rChild == null ) {
            if( sum == 0 ) {
                list.add( new ArrayList<>(queue) );
            }
            queue.removeLast();
            return;
        }

        /*
            不能使用 else if ！！！！ ，因为这样会导致只能遍历到一边的树
         */
        if( node.lChild != null ) {
            recursivePathSum(node.lChild, list, queue, sum);
        }
        if( node.rChild != null ) {
            recursivePathSum(node.rChild, list, queue, sum);
        }
        queue.removeLast();
    }


    /*
        非递归的
        使用后序遍历


        后序遍历的骨架为
        while ( true ) {
            if( p.lChild != null ) {
                stack.addLast( p );
                p = p.lChild;
            }else if( p.rChild != null ) {
                stack.addLast( p );
                p = p.rChild;
            }else {
                //对parent进行后序访问
                Node parent = p;
                Node child = null;
                while ( !stack.isEmpty() && ( stack.getLast().rChild == null || stack.getLast().rChild == parent ) ){
                    child = parent;
                    parent = stack.removeLast();
                    //对parent进行后序访问....
                }

                //遍历结束
                if( stack.isEmpty() && ( parent.rChild == null || parent.rChild == child ) ) {
                    //对根节点进行 后序访问....

                    //返回
                    return result;
                }
                //切换节点的两种情况
                if( stack.isEmpty() ) {
                    p = parent.rChild;
                    stack.addLast( parent );
                }else {
                    p = stack.getLast().rChild;
                }
            }
        }
     */

    public static List<List<Integer>> pathSum( Node root,int sum ) {

        List<List<Integer>> result =  new LinkedList<>();
        if( root == null ) return result;


        int treeSum = 0;
        LinkedList<Node> stack = new LinkedList<>();
        Node p = root;
        while ( true ) {
            if( p.lChild != null ) {
                stack.addLast( p );
                treeSum+= p.data;
                p = p.lChild;
            }else if( p.rChild != null ) {
                stack.addLast( p );
                treeSum+= p.data;
                p = p.rChild;
            }else {
                //此时不入栈。。。。
                if( sum == treeSum + p.data ) {
                    LinkedList<Integer> linkedList = new LinkedList<>();
                    stack.forEach((node) -> {
                        linkedList.add( node.data );
                    });
                    linkedList.addLast( p.data );
                    result.add( linkedList );
                }
                Node parent = p;
                Node child = null;
                while ( !stack.isEmpty() && ( stack.getLast().rChild == null || stack.getLast().rChild == parent ) ){
                    child = parent;
                    parent = stack.removeLast();
                    //treeSum 和为 栈内元素的值的和
                    treeSum-=parent.data;
                }

                //遍历结束
                if( stack.isEmpty() && ( parent.rChild == null || parent.rChild == child ) ) {
                    //对根节点进行 后序访问....

                    //
                    if( parent.rChild == null && parent.lChild == null && sum == parent.data ) {
                        LinkedList<Integer> linkedList = new LinkedList<>();
                        linkedList.addLast( parent.data );
                        result.add( linkedList );
                    }
                    //返回
                    return result;
                }

                //分为回溯到 根节点和 未回溯到根节点的情况
                if( stack.isEmpty() ) {
                    p = parent.rChild;

                    stack.addLast( parent );
                    treeSum+= parent.data;
                }else {
                    p = stack.getLast().rChild;
                }
            }

        }


    }

}
