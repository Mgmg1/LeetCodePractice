package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    翻转游戏
 */
public class Leet293 {

    public static void main(String[] args) {
        List<String> strings = turnOverGame("++++");
        for (String str :
                strings) {
            System.out.println( str );
        }
    }

    public static List<String> turnOverGame(String str ) {

        LinkedList<String> list = new LinkedList<>();
        for (int i = 1; i < str.length(); i++) {
            if( str.charAt(i) == '+' && str.charAt(i-1)== '+' ) {
                list.add( str.substring(0,i-1) + "--" + str.substring(i + 1) );
            }
        }
        return list;
    }

}
