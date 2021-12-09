package Leet1;

/*
    跳跃游戏
 */
public class Leet45 {

    public static void main(String[] args) {

        int[] arr = { 2,3,1,1,4 };
        System.out.println( jumpGame(arr,0) );
        System.out.println( jumpGame2(arr) );
        System.out.println( greedyJumpGame(arr) );

    }

    /*
        递归
        先写出递归停止条件！！！ 即递归出口
     */
    public static int jumpGame( int[] arr,int i) {

        if( i == arr.length - 1 ) {
            return 0;
        }
        //
        int result = ~( 1 << 31 );
        for (int j = i + 1; j < arr.length && j <= i + arr[i]; j++) {
            result = Math.min( jumpGame(arr,j),result );
        }
        return result + 1;
    }

    /*
        动态规划。
        注意数组可能越界，需要加条件  j < step.length && j <= i + arr[i] !!
     */
    public static int jumpGame2( int[] arr) {

        int[] step = new int[arr.length];
        for (int i = step.length - 2; i >= 0; i--) {
            int min = ~( 1<<31 );
            for (int j = i + 1; j < step.length && j <= i + arr[i] ; j++) {
                min = Math.min( step[j],min );
            }
            step[i] = min + 1;
        }
        return step[0];
    }

    /*
        贪心算法
        贪心点是 到达最远处所需要的 步数，当最远处恰大于等于 arr.length - 1,则可以返回

        当到达上一个max时，才可以更新maxI，
        根据之前更新max，得到新增的一步最多可到达哪里!!!

        基本思想是：计算出下一步可以到达多远。
        一边更新下一步可以到达的最远距离，
        当i到达上一步可到达的最远距离时，得到下一步(在上一步的基础上)可到达的最远距离

     */

    public static int greedyJumpGame( int[] arr ) {

        int max = 0;
        int maxI = 0;
        int step = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if( i + arr[i] > max ) {
                max = i + arr[i];
            }
            if(  maxI == i ) {
                maxI = max;
                step++;
            }
            if( maxI >= arr.length - 1 ) {
                return step;
            }
        }
        //说明无法到达
        return -1;
    }

}
