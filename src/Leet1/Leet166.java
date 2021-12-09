package Leet1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/*
    分数到小数
    思路：模拟手写的除法。
 */
public class Leet166 {

    public static void main(String[] args) {
        int numerator = 111;
        int denominator = 87;
        System.out.println( fractionYoFloat(numerator,denominator) );
    }

    /*
        假设denominator不为0
     */
    private static String fractionYoFloat (int numerator,int denominator ) {
        if( numerator == 0 ) return "0";
        String result = Integer.toString( numerator / denominator);
        numerator = numerator % denominator;
        if( numerator == 0 ) {
            return result;
        }
        result = result + '.';

        LinkedList<String> list = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;

        //需要使用hashmap记录下曾经出现过的 余数
        while ( numerator != 0 && !map.containsKey( numerator) ) {
            map.put( numerator,i );
            numerator*=10;
            while ( numerator < denominator ){
                numerator*=10;
                list.addLast("0");
                i++;
            }
            list.addLast( Integer.toString( numerator / denominator ) );
            i++;
            numerator = numerator % denominator;
        }


        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        int j = 0;
        while ( iterator.hasNext() ) {
            String next = iterator.next();
            if( numerator != 0 && map.get( numerator) == j ) {
                sb.append('(');
            }
            sb.append(next);
            j++;
        }
        if( numerator != 0 ) {
            sb.append(')');
        }
        return  result + sb.toString();
    }
}
