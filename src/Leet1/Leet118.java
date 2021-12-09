package Leet1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    杨辉三角
 */
public class Leet118 {

    public static void main(String[] args) {
        List<int[]> triangle = triangle(5);
        for (int[] arr :
                triangle) {
            System.out.println(Arrays.toString( arr ));
        }
        int[] p = null;

    }

    public static List<int[]> triangle( int n ) {
        LinkedList<int[]> result = new LinkedList<>();

        if( n < 1 ) return result;
        result.addLast( new int[]{1} );
        if( n == 1 ) return result;

        int[] prior = { 1 };
        for (int i = 1; i < n; i++) {
            int[] ele = new int[i + 1];
            ele[0] = 1;
            ele[ele.length-1] = 1;
            for (int j = 1; j < prior.length ; j++) {
                ele[j] = prior[j] + prior[j-1];
            }
            prior = ele;
            result.addLast(ele);
        }

        return result;
    }
}
