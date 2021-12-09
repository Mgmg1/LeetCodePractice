package Leet1;

import java.util.Stack;

/*
   最长有效括号
   思路1：利用括号  左边大于右边时括号可能有效的特性 一次遍历 记录下最大的 左边括号数量等于右边的情况，

   思路2：
        一个有效括号在栈中，必然可以完全出栈。
 */
public class Leet32 {

    public static void main(String[] args) {

//        char[] brackets = ")()())".toCharArray();
        char[] brackets = "()()".toCharArray();
        System.out.println( longestValidBracket( brackets )  );
        System.out.println( longestValidBracket2( brackets )  );

    }

    /*
        利用 有效括号里 左括号数量小于右括号时一定无效的特征判断
     */
    public static int longestValidBracket( char[] brackets ) {

        int left = 0,right = 0;
        int maxLeft = 0,maxRight = 0;
        int i = 0;
        while ( i < brackets.length ) {
            if( brackets[i] == '(' ) {
                left++;
                i++;
            }else {
                right++;
                if( left == right ) {
                    if( right + left > maxRight + maxLeft ) {
                        maxLeft = left;
                        maxRight = right;
                    }
                    i++;
                }else if( left > right ){
                    i++;
                }else {
                    i = i + left + right ;
                    left = 0;
                    right = 0;
                }
            }
        }
        if( left > right ) {
            for (int j = brackets.length - left - right; j < brackets.length ; j++) {
                if( left == right && right + left > maxRight + maxLeft ) {
                    maxLeft = left;
                    maxRight = right;
                }
                if( brackets[j] == '('  ) {
                    left--;
                }else {
                    right--;
                }
            }
        }
        return maxRight + maxLeft ;
    }

    /*
        用栈
        用上一个次检查到无效括号)的下标兜底 。开始时用  -1  等价
        检查到 (  时下标入栈， 因此栈中只有上一次无效括号的下标在栈底，以上为 ( 的下标
        每当检查到 ） 时出栈，
        所有被抵消的括号组成一个有效括括号!!!!
     */
    public static int longestValidBracket2( char[] brackets) {

        Stack<Integer> stack = new Stack<>();
        stack.push( -1 );
        int result = 0;
        for (int i = 0; i < brackets.length; i++) {
            if( brackets[i] == '(' ) {
                stack.push( i );
            }else {
                stack.pop();
                if( stack.isEmpty() ) {
                    //说明此时已经把兜底的上一个无效括号下标取出
                    //即此次的循环遍历到的括号是新的无效括号
                    stack.push(i);
                }else {
                    //所有被抵消的括号组成一个有效括括号!!!!
                    //说明出栈的是 ( 的下标,此时可以得到一次有效括号的范围
                    result = Math.max( result,i - stack.peek());
                }
            }
        }

        return result;
    }
}
