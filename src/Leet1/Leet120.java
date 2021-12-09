package Leet1;

import java.util.LinkedList;
import java.util.Stack;

/*
    三角形最小路径和
    每次只能移动到下一行的相邻部分
 */
public class Leet120 {

    public static void main(String[] args) {
        int[][] triangle = {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}
        };
//        System.out.println( shortestPath(triangle,0,0) );
//        System.out.println( shortestPath(triangle) );
        System.out.println( dp(triangle) );
    }


    /*
        回溯法
     */
    public static int shortestPath(int[][] triangle,int i,int j ) {

        if( i == triangle.length ) return 0;

        return Math.min( shortestPath(triangle, i + 1, j),shortestPath(triangle, i + 1, j + 1)  ) + triangle[i][j];

    }


    /*
        使用栈
        参考后序遍历的做法
     */

    public static int shortestPath( int[][] triangle ) {

        if ( triangle == null || triangle.length == 0 ) return 0;

        Stack<Integer> stack = new Stack<>();
        int min = ~( 1<<31 );
        int sum = 0;
        int j = 0;
        int i = 0;
        while ( true ) {
            if( i < triangle.length - 1 ) {
                stack.push( j );
                sum+=triangle[i][j];
                i++;
            }else {
                //前序遍历
                sum+=triangle[i][j];
                min = Math.min( min,sum);
                int parent = j;
                int child = j;
                while ( !stack.isEmpty() && stack.peek() + 1 == parent ) {
                    sum-=triangle[i--][parent];
                    child = parent;
                    parent = stack.pop();
                    //对partent进行后序访问
                }
                if( stack.isEmpty() ) {
                    if( parent + 1 == child  ) {
                        return min;
                    }
                    j = 1;
                    stack.push(0);
                    i++;
                }else {
                    sum-=triangle[i][parent];
                    j = parent + 1;
                }
            }
        }
    }
    /*
        动态规划
     */
    public static int dp( int[][] triangle ) {

        int[] dp = new int[triangle.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = triangle[triangle.length-1][i];
        }

        for (int i = triangle.length - 2; i >= 0  ; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j],dp[j+1]) + triangle[i][j];
            }
        }
        return dp[0];
    }
}
