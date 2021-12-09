package Leet1;

import java.util.Stack;

/*
    柱形图中的最大矩形

 */
public class Wleet84 {

    public static void main(String[] args) {
//        System.out.println( maxMatrix( new int[]{2,1,5,6,5,3} ) );
//        System.out.println( largestRectangleArea( new int[]{1,2,1,5,6,5,3} ) );
        System.out.println( largestRectangleArea( new int[]{8,4,8} ) );
    }

    // 错误做法,原因是没能考虑到 左右下标数组的赋值操作不是O(1)，而是O(n)
    //关键点是  最大矩形中，必然存在至少一个位置的高度等于其高度，且矩形左侧和右侧的高度均低于此高度
    public static int maxMatrix( int[] barChart ) {

        //左边第一个小于它的元素下标+1
        int [] lextMaxs = new int[barChart.length];
        //右边第一个小于它的元素的下标+1
        int [] rightMaxs = new int[barChart.length];
        lextMaxs[0] = 0;
        rightMaxs[barChart.length - 1] = barChart.length - 1;

//        int max = barChart[0];
        int max = 0;
        for (int i = 1; i < barChart.length; i++) {
            if( barChart[i] > barChart[i-1] ) {
                max = i;
            }
            lextMaxs[i] = max;
        }
//        max = barChart[barChart.length-1];
        max = barChart.length-1;
        for (int i = barChart.length-2; i >= 0; i--) {
            if( barChart[i] > barChart[i+1] ) {
                max = i;
            }
            rightMaxs[i] = max;
        }
        max = 0;
        for (int i = 0; i < barChart .length; i++) {
            max = Math.max( max , ( rightMaxs[i] - lextMaxs[i] + 1 ) * barChart[i]  );
        }
        return max;
    }


    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
