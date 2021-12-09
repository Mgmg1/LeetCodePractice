package Leet1;

/*
    最小栈
    要求实现一个 O(1) 的获取栈内最小值的操作
 */
public class Leet155 {

    /*
        链栈
     */
    private static class SNode{
        int data;
        SNode next;
    }
    /*
        非静态内部类只能由 外围类的非静态方法创建,即创建内部类时，必须保证方法的上下文this指向外部类实例
        静态内部类则没有这种限制
     */
    private static class Stack{
        SNode top = new SNode();
        int count = 0;
        void push(int val ) {
            SNode sNode = new SNode();
            sNode.data = val;
            sNode.next = this.top.next;
            this.top.next = sNode;
            count++;
            pushMin(val);
        }
        int pop() {
            if( this.top.next == null ) {
                throw new RuntimeException();
            }
            int result = this.top.next.data;
            this.top.next = this.top.next.next;
            count--;
            removeMinTop();
            return result;
        }
        int getMin( ) {
            if( this.minTop.next == null ) {
                throw new RuntimeException();
            }
            return minTop.next.data;
        }
        private SNode minTop = new SNode();
        private void pushMin( int val ) {
            SNode sNode = new SNode();
            if( minTop.next == null ) {
                minTop.next = sNode;
                sNode.data = val;
                return;
            }
            sNode.data = Math.min( val,minTop.next.data );
            sNode.next = minTop.next;
            minTop.next = sNode;
        }
        private void removeMinTop( ) {
            if( minTop.next != null ) {
                minTop.next = minTop.next.next;
            }
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 10; i > 0; i--) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println( stack.getMin() );
            stack.pop();
        }
    }
}
