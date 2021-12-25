package Leet1;

/**
 * 地下城游戏
 */
public class Leet174 {

    public static void main(String[] args) {
        int[][] maze = {
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}
        };
//        System.out.println( minInitialHP( maze ) );
        System.out.println( minInitialHP2( maze ) );
    }

    /*
        使用动态规划：通过优化，空间复杂度可以由O(MN) 缩减为 O(M) 或 O(N)
        循环方向必须是从右下到左上，从左上到右下是另一种做法！！
        M * N
        这里以O(N为例子)
     */
    private static int minInitialHP( int[][] maze ) {
        if( maze == null || maze.length == 0 || maze[0].length == 0 ) { return 1; }

        int[] dp = new int[maze[0].length];
        dp[maze.length-1] = -maze[maze.length-1][maze[0].length-1];

        for (int i = maze.length - 2; i >= 0 ; i--) {
            dp[i] = Math.max( dp[i + 1] - maze[maze.length -1][i],- maze[maze.length -1][i]);
        }

        for (int i = maze.length -2 ; i >= 0 ; i--) {
            for (int j = maze.length - 1; j >=0 ; j--) {
                if( j == ( maze[0].length - 1 ) ) {
                    dp[j] = Math.max( dp[j] - maze[i][j],-maze[i][j] );
                }else {
                    dp[j] = Math.max( Math.min( dp[j + 1],dp[i] ) - maze[i][j],-maze[i][j] );
                }
            }
        }
        //结果必须是正值
        return Math.max( dp[0] + 1,1 );
    }

    /*
        从左上到右下,必须引入骑士的血量标记数组
     */
    private static int minInitialHP2( int[][] maze ) {
        if( maze == null || maze.length == 0 || maze[0].length == 0 ) { return 1; }

        int[] dp = new int[maze[0].length];
        int[] hp = new int[maze[0].length];
        hp[0] = maze[0][0];
        dp[0] = -maze[0][0];
        for (int i = 1; i < maze[0].length ; i++) {
            dp[i] = Math.max( dp[i - 1] -maze[0][i],dp[i-1] );
            hp[i] = hp[i-1] + maze[0][i];
        }
        for (int i = 1; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if( j == 0 ) {
                    hp[j] = hp[j] + maze[i][j];
                    dp[j] = Math.max( dp[j],-hp[j] );
                }else {
                    if( Math.max( dp[j],-( hp[j] + maze[i][j] ) ) < Math.max( dp[j-1],-( hp[j-1] + maze[i][j] ) )  ) {
                        dp[j] = Math.max( dp[j],-( hp[j] + maze[i][j] ) );
                        hp[j] = hp[j] + maze[i][j];
                    }else {
                        dp[j] = Math.max( dp[j-1],-( hp[j-1] + maze[i][j] ) );
                        hp[j] = hp[j-1] + maze[i][j];
                    }
                }
            }
        }
        return Math.max( dp[maze[0].length-1] + 1 , 1 );
    }
}
