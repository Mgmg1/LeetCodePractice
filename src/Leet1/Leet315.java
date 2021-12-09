package Leet1;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/*
    计算右侧小于当前元素的个数
    给定一个整数数组 _nums_ ，按要求返回一个新数组 _counts_ 。
    数组 _counts_ 有该性质： `counts[i]` 的值是 `nums[i]` 右侧小于 `nums[i]` 的元素的数量。
 */
public class Leet315 {

    static class  Node {
        int val;
        int sum = 1;
        Node lChild;
        Node rChild;
        public Node (int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1,2,3,4,5,6,7,8 };
//        Node root = new Node( 5 );
//        for (int i = 0; i < 10; i++) {
//            insert(root,i);
//        }
//        for (int i = 0; i < 3; i++) {
//            root = delete(root,i);
//        }
//
//        travelInOrder( root );
        System.out.println(Arrays.toString(countSmaller(nums)));
    }

    public static int[] countSmaller(int[] nums) {
        Node root = new Node(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            insert(root,nums[i]);
        }
        int[] result = new int[nums.length];
        Node next = root;
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = next.lChild == null ? 1 : ( 1 + next.lChild.sum ) ;
            while ( true ) {
                if( next.val > nums[i] ) {
                    sum = sum - ( next.lChild.rChild == null ?  1 :( next.lChild.rChild.sum + 1) );
                    next = next.lChild;
                }else if( next.val < nums[i] ) {
                    sum = sum + ( next.rChild.lChild == null ?  1 :( next.rChild.lChild.sum + 1) );
                    next = next.rChild;
                }else {
                    break;
                }
            }
            result[i] = sum - 1;
            root = delete(root,nums[i]);
            next = root;
        }

        return result;
    }

    //右子树为的所有值大于等于根节点，允许插入重复值
    public static void insert(Node root, int val) {
        Node next = root;
        Node parent = null;
        while (next != null) {
            next.sum++;
            parent = next;
            if( val < next.val ) {
                next = next.lChild;
            }else {
                next = next.rChild;
            }
        }
        if( val < parent.val  ) {
            parent.lChild = new Node(val);
        }else {
            parent.rChild = new Node(val);
        }
    }

    public static int findMin( Node root ) {
        Node next = root;
        while( next.lChild != null ) {
            next = next.lChild;
        }
        return next.val;
    }

    public static Node delete(Node root,int val) {

        if( root == null ) {
            return null;
        }
        root.sum--;
        if( root.val == val ) {
            if( root.lChild == null ) {
                return root.rChild;
            }
            if( root.rChild == null ) {
                return root.lChild;
            }
            root.val = Leet315.findMin(root.rChild);
            root.rChild = delete( root.rChild,root.val);
        }else {
            if( root.val > val ) {
                root.lChild = delete( root.lChild,val);
            }else {
                root.rChild = delete( root.rChild,val);
            }
        }
        return root;
    }

    public static void travelInOrder(Node root) {
        if (root != null) {
            travelInOrder(root.lChild);
            System.out.println(root.val);
            travelInOrder(root.rChild);
        }
    }

}
