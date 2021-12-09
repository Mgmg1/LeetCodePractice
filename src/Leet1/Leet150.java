package Leet1;

import java.util.Stack;

public class Leet150 {

    public static void main(String[] args) {
        String[] expression1 = { "2","1","+","3","*" };
        String[] expression2 = { "4","13","5","/","+" };
        String[] expression3 = { "10","6","9","3","+","-11","*","/","*","17","+","5","+" };
        System.out.println( calc( expression1 ) );
        System.out.println( calc( expression2 ) );
        System.out.println( calc( expression3 ) );
    }

    //逆波兰表达式( 后缀表达式 )
    public static int calc(String[] expression) {

        Stack<Integer> nums = new Stack<>();
        for (String c :
                expression) {
            if (c.length() > 1 || ( c.charAt(0) - '0' >= 0 &&  c.charAt(0) - '0' <= 9 )) {
                nums.push(Integer.parseInt( c ));
            }
            Integer num1 = null;
            Integer num2 = null;
            //注意 num1首先pop,在二目符的右侧，num2然后pop在二目符号左侧
            switch (c) {
                case "+":
                    num1 = nums.pop();
                    num2 = nums.pop();
                    nums.push(num2 + num1);
                    break;
                case "-":
                    num1 = nums.pop();
                    num2 = nums.pop();
                    nums.push(num2 - num1);
                    break;
                case "*":
                    num1 = nums.pop();
                    num2 = nums.pop();
                    nums.push(num2 * num1);
                    break;
                case "/":
                    num1 = nums.pop();
                    num2 = nums.pop();
                    nums.push(num2 / num1);
                    break;
                default:
                    break;
            }
        }
        return nums.pop();
    }
}
