package Leet1;

import java.util.LinkedList;

/*
    有效括号
    使用栈。当遇到 ) ] } 时，出栈分别抵消 ( [ { 。若无法抵消或者循环结束 stack的不为空，则无无效
 */
public class Leet20 {

    public static void main(String[] args) {

        char[] input = "(()()[{}])".toCharArray();
        System.out.println( isBracketValid( input ) );

    }


    public static boolean isBracketValid( char[] str ) {

        if( str == null || str.length == 0 ) return true;
        LinkedList<Character> stack = new LinkedList<>();
        for (char c :
                str) {
            if( c == ')' || c == '}' || c == ']' ) {
                if( stack.isEmpty() ) {
                    return false;
                }
                Character cc = stack.removeLast();
                switch ( cc ) {
                    case '{': if( c != '}' ) return false;break;
                    case '(': if( c != ')' ) return false;break;
                    case '[': if( c != ']' ) return false;break;
                    default: return false;
                }
            }else {
                stack.addLast( c );
            }
        }
        return stack.isEmpty();
    }

}
