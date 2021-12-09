package Leet1;

import java.util.HashMap;
import java.util.Stack;

/*
    柱状图中的最大矩形
 */
public class Leet84 {


    public static void main(String[] args) {

        System.out.println( maxRectangle( new int[]{2,1,5,6,2,3} ) );
        recur( new int[]{2,1,5,6,2,3},-1,0 ,true);
        System.out.println( recurMax );

    }

    /*
         本题的关键模型是： 升序柱形图！！！
        当 arr[i] 大于栈顶元素时，取出栈顶元素并计算。（取出栈后，如果栈空且当前 i!=arr.length,则需要入栈i，防止退出循环）
        否则入栈i，i++;

        体现了栈中元素的对称性使用。 遍历时，要考虑 元素左边stack.peek  和 右边 i + 1   跟 i 的元素的大小关系
        一旦满足则 push 或者 pop
        这样栈中元素就有一定的对称性， 即 stack.peek + 1 ~  pop  ~ i - 1  ,都有 element >= arr[pop]


        第二种的写法比较好
     */
    public static int maxRectangle( int[] arr ) {

        Stack<Integer> stack = new Stack<>();
//        stack.push(0 );
//        int i = 1;
//        int max = 0;
//        while ( !stack.isEmpty() ) {
//            if( i == arr.length || arr[ stack.peek() ] > arr[i] ) {
//                Integer pop = stack.pop();
//                max = Math.max( max,( i - pop ) * arr[pop] );
//                if( i!=arr.length && stack.isEmpty() ) {
//                    stack.push(i);
//                    i++;
//                }
//            }else {
//                stack.push(i);
//                i++;
//            }
//        }


        int max = 0;
        for (int i = 0; i <= arr.length; i++) {
            int height = i == arr.length ? -1 : arr[i];
            if( !stack.isEmpty() && height < arr[stack.peek()] ) {
                Integer pop = stack.pop();
                //  stack.empty() ? i : i - stack.peek() - 1   注意， 以i为中心， 从 stack.peek()+1 ~ j-1 都是大于等于 arr[pop]
                max = Math.max( max, ( stack.empty() ? i : i - stack.peek() - 1  ) * arr[pop] );
                i--;
            }else {
                stack.push( i );
            }
        }

        return max;
    }

    /*
        是上述
        很复杂。。。。递归版。。。
        before：相当于栈中的peek
        flag 标识该递归是否要 更新recurMax
        int：return  i+1 ~ arr.length - 1  || arr.length 中元素小于 arr[i] 的第一个下标
     */
    private static int recurMax = 0;
    public static int recur( int[] arr,int before,int i,boolean flag) {

        if( i >= arr.length ) {
            return arr.length;
        }

        int recur = recur(arr, i, i + 1,true);
        if( flag ) {
            recurMax = Math.max( recurMax,( recur - before - 1 ) * arr[i] );
        }
        if( before < 0 || arr[i] > arr[before] ) {
            if( recur == arr.length || before < 0 || arr[recur] < arr[before] ) {
                return recur;
            }else {
                //这一步有问题,需要增加 是否需更新recurMax的标识 flag
                return recur(arr, before, recur + 1,false);
            }
        }else {
            return i;
        }
    }
}
