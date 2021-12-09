package Leet1;

import java.util.HashMap;
import java.util.Map;

/*
    直线上最多的点数

    O(n^2) 对每一个点使用一次循环，使用公式 ( (y1-y2)/(x1-x2) ) 假如斜率相等则认为斜率相等( double是不准的！！！ )
    缺点：在极端情况下，double溢出会导致错误。

    需要注意，会存在重复遍历的部分，例如对同一条直线上的节点重复遍历，
    然而假如记录下过往 每条斜线的记录上的点，并通过检查该斜率计算是否重复来减少计算,那么会有会有 O(N)的空间开销

    由于斜率的计算会有精度问题，导致统计各个斜率的数量，因此采用最简分数的形式存储

 */
public class Leet149 {

    public static void main(String[] args) {
        int[][] points = {
//                {1,1},{2,2},{3,3}
                {1,1},{3,2},{5,3},{4,1},{2,3},{1,4}
        };
//        int i = maxPointCountOnLine(points);
//        System.out.println( i );
        System.out.println(generateGCD2(-3, 1));
    }

    public static int maxPointCountOnLine(int[][] points ) {
        if( points == null || points.length ==0 ){ return 0; }
        if( points.length <= 2 ) {return 2;}

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 2;
        for (int i = 0; i < points.length; i++) {
            int max = 0; //记录此次循环和i点处于一条直线上的不与i重复的点的个数
            int overlap = 0; //和i节点重叠的点，因为斜率无法计算，因此独立出来统计
            map.clear();
            /*
                j从i + 1开始，而不是0 。则不会影响正确性。
                原因：
                假如存在点 < i ,也在直接上却每次本次循环被统计，
                那么它一定在往前的 < i 的循环上被统计了，并且位于含有 i 的直线上
             */
            for (int j = i + 1; j < points.length; j++) {

                //使用公式 ( (x1-x2)/(y1-y2) )
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if( x == 0 && y == 0 ) {
                    overlap++;
                    continue;
                }
                //用于斜率使用分数标识，因此存储k的最简分数，需要使用 最大公约数算法化简
                //其中，分母和分子 同时除以 最大公约数 可以得到 最简形式 ，这是可以证明的
                int gcd = gcd(x, y);
                if( gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                //开始统计
                if( map.containsKey( x ) ) {
                    Map<Integer, Integer> vMap = map.get(x);
                    if( vMap.containsKey( y ) ){
                        vMap.put( y,vMap.get( y ) + 1 );
                    }else {
                        vMap.put( y,1 );
                    }
                }else {
                    Map<Integer, Integer> vMap = new HashMap<>();
                    vMap.put( y,1 );
                    map.put( x,vMap );
                }
                max = Math.max( max,map.get(x).get(y));
            }
            // +overlap的个数是指 和 i点重叠却不是i点的点的个数;
            // + 1 代表加上i点;
            // +max则是非i和重叠的i点的在同一直线上的点的个数
            result = Math.max( result,max + overlap + 1 );
        }
        return result;
    }

    /*
        最大公约数。
        并非一定需要 a >= b
        但是要求 a 不能为 0,总之使用前最好检查是否有0，有则可以直接输出了
     */
    private static int gcd(int a,int b ) {
        if( b == 0 ) {
            return a;
        }else {
            return gcd( b,a % b );
        }
    }

    /*
        最大公约数的非递归形式
     */
    private static int generateGCD2( int a, int b ) {
        int temp = 0;
        while ( b != 0 ) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
