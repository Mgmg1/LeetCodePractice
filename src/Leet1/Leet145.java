package Leet1;

import java.util.Stack;

/*
    二叉树后序遍历
    普遍地，非递归地后序遍历都得注意节点访问
 */
public class Leet145 {

    /*
        非递归的后序遍历有两种：
        1.使用flag标记是否已经出过栈
        2.不使用flag
     */
    private static class BSNode {
        int data;
        BSNode lChild;
        BSNode rChild;
        public BSNode(int data, BSNode lChild, BSNode rChild) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {
        BSNode bsNode3 = new BSNode(3,null,null);
        BSNode bsNode4 = new BSNode(4,null,null);
        BSNode bsNode1 = new BSNode(1,bsNode3,bsNode4);
        BSNode bsNode2 = new BSNode(2,null,null);
        BSNode root = new BSNode(0,bsNode1,bsNode2);
//        recursivePostTravel( root );
//        postOrderTravelWithStack( root );
        postOrderTravel( root );
    }

    /*
        递归后序遍历
     */
    public static void recursivePostTravel( BSNode root ) {
        if( root != null ) {
            recursivePostTravel( root.lChild );
            recursivePostTravel( root.rChild );
            System.out.println( root.data );
        }
    }

    /*
        非递归后序遍历，
        使用state栈标记 ( 每次入栈时判断state ) state标识当前节点p的状态
        每次p改变时，都需要变更状态
        : state == 0时，说明该节点是第一次到达  对应前序
        state == 1时，则是第二次到达   对应中序
        state == 2时，则是第三次到达   对应后序
     */
    public static void postOrderTravelWithStack(  BSNode root ) {
        if( root == null ) return;
        Stack<BSNode> stack = new Stack<>();
        Stack<Integer> stateStack = new Stack<>();
        BSNode p = root;
        int state = 0;  //初始状态，对于一棵二叉树，一个节点有三个状态
//        while ( !stack.isEmpty() || p != null ) { 条件永为真
        while ( true ) {
            if( p != null ) {
                if( state == 0 ) {
                    stack.push( p );
                    stateStack.push( 0 );
                    p = p.lChild;
                    //state = 0;这里，state已经是0了，不需要重复执行，但是加上该语句，则逻辑更清晰，表明更新p节点的state
                }else if( state == 1 ) {
                    stack.push( p );
                    p = p.rChild;
                    stateStack.push( 1 );
                    state = 0;
                }else {
                    System.out.println( p.data );
                    if( stack.isEmpty() ) {
                        //说明已经到了根节点
                        break;
                    }
                    p = stack.pop();
                    state = stateStack.pop() + 1;
                }
            }else {
                p = stack.pop();
                state = stateStack.pop() + 1; //状态改变
            }
        }
    }

    /*
        非递归后序遍历
        不是flag数组，较麻烦
     */
    public static void postOrderTravel( BSNode root ) {
        if( root == null ) return;

        Stack<BSNode> stack = new Stack<>();
        BSNode p = root;
        while ( true ) {
            if( p.lChild != null ) {
                stack.push( p );
                p = p.lChild;
            }else if( p.rChild != null ) {
                stack.push( p );
                p = p.rChild;
            }else {
                //属于叶子节点
                BSNode parent = p;
                BSNode child = null;
                while (!stack.empty() && ( parent.rChild == child || ( parent.lChild == child && parent.rChild == null ) )) {
                    //后序访问节点
                    System.out.println( parent.data );
                    child = parent;
                    parent = stack.pop();
                }
                if( stack.isEmpty() ) {
                    if( parent.rChild == child || ( parent.lChild == child && parent.rChild == null ) ) {
                        //后序访问根节点
                        System.out.println( parent.data );
                        break;
                    }
                    stack.push( parent );
                    p = parent.rChild;
                }else {
                    stack.push( parent );
                    p = stack.peek().rChild;
                }
            }
        }
    }
}
