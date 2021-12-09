package Leet1;

import java.util.Arrays;

/*
    分发糖果。。。。比较难以理解的题
    思路和 Leet152的O(n)解法有异曲同工之妙

    贪心算法： 典型的分两次进行
    对于i，第一次保证在 i的分数大于 i-1 的分数时，i所获得的糖果是 i-1 的再加1
    第二次保证在 i的分数大于 i+1 的分数时，i所获得的糖果是 i+1 的再加1!!
 */
public class Leet135 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString( distributeSugar( new int[]{1,0,2} )) );
        System.out.println( Arrays.toString( distributeSugar( new int[]{1,2,2} ) ) );
    }

    public static int[] distributeSugar( int[] grades) {
        if( grades == null || grades.length == 0 ) return new int[0];
        int[] sugars = new int[grades.length];
        sugars[0] = 1;
        for (int i = 1; i < grades.length; i++) {
            sugars[i] = grades[i] > grades[i-1] ? sugars[i-1] + 1: 1;
        }
        for (int i = grades.length-2; i >=0 ; i--) {
            sugars[i] = grades[i] > grades[i+1] ? sugars[i+1] + 1 : sugars[i];
        }
        return sugars;
    }

}
