package Leet1;

import java.util.Stack;

/*
    二叉树的锯齿遍历，属于栈的应用
    实际上，这是用栈来进行层次遍历,和层次遍历差不多，但是使用栈来遍历作为它的数据结构
 */
public class Leet103 {

    public static void main(String[] args) {
        Integer[] tree= {3,9,20,null,null,15,7};
        shuffleTravel( tree );
    }

    public static void shuffleTravel( Integer[] tree ) {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push( 0 );
        boolean dirc = true;
        boolean flag1 = false;
        boolean flag2 = false;
        while ( !stack1.isEmpty() ) {
            while ( !stack1.isEmpty() ) {
                Integer pop = stack1.pop();
                System.out.println( tree[pop] );
                if( pop * 2 + 1 < tree.length && tree[pop*2+1]!= null ) {
                    flag1 = true;
                }
                if( pop * 2 + 2 < tree.length && tree[pop*2+2]!= null ) {
                    flag2 = true;
                }
                if( dirc ) {
                    if( flag1 ) {
                        stack2.push( pop*2+1 );
                    }
                    if( flag2 ) {
                        stack2.push( pop*2+2 );
                    }
                }else {
                    if( flag2 ) {
                        stack2.push( pop*2+2 );
                    }
                    if( flag1 ) {
                        stack2.push( pop*2+1 );
                    }
                }
                flag1 =false;
                flag2 =false;
            }
            dirc = !dirc;
            Stack<Integer> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
        }
    }
}
