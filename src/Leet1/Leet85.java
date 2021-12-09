package Leet1;

import java.util.Stack;

/*
    最大矩形
    思路：
        结合柱形图中的最大矩形

 */
public class Leet85 {

    public static void main(String[] args) {

        char[][] map = {
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()
        };
        System.out.println( maxRectangle(map) );

    }

    public static int maxRectangle( char[][] map ) {

        int[] heights = new int[map[0].length];
        int i = 0 ;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        while ( i < map.length ) {
            stack.clear();
            for (int j = 0; j < map[0].length; j++) {
                heights[j] = map[i][j] == '0' ? 0 : heights[j] + 1;
            }
            for (int j = 0; j <= map[0].length; j++) {
                int jHeight = j == map[0].length ? -1 : heights[j];
                if( !stack.isEmpty() && jHeight < heights[stack.peek()] ) {
                    Integer pop = stack.pop();
                    max = Math.max( max,( stack.empty() ? j : j - stack.peek() - 1  ) * heights[pop] );
                    j--;
                }else {
                    stack.push(j);
                }
            }
            i++;
        }
        return max;
    }

}
