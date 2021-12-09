package Leet1;

/*
    买股票最佳时机
 */
public class Leet122 {

    public static void main(String[] args) {
        int[] stocks =  new int[]{7,1,5,3,6,4};
//        System.out.println( bestTime1( stocks,stocks.length - 1 ) );
//        System.out.println( bestTime( stocks ) );
        System.out.println(bestTime2(stocks));
    }

    //贪心算法
    public static int bestTime2(int[] stock) {

        int profit = 0 ;
        // 比较啰嗦的做法
//        int time = 0;
//        for (int i = 1; i < stock.length ; ) {
//            if( stock[i] < stock[time] ) {
//                time = i;
//                i++;
//            }else {
//                int j = time + 1;
//                for ( ; j < stock.length && stock[j] >= stock[j-1] ; j++);
//                profit+=(  stock[j - 1] - stock[time] );
//                time = j;
//                i = j;
//            }
//        }

        for (int i = 0; i < stock.length - 1; i++) {
            profit+= Math.max( stock[i + 1] - stock[i] , 0  );
        }
        return profit;
    }


    //动态规划
    public static int bestTime(int[] stock){
        int[] dp = new int[stock.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max( dp[i] , dp[j] + Math.max( stock[i] - stock[j],0 ) );
            }
        }
        return dp[stock.length - 1];
    }

    //递归,注意   Math.max( stock[n] - stock[n-i],0 ) ，不能让 stock[n] - stock[n-i] < 0 时算入 max
    public static int bestTime1( int[] stock ,int n ) {
        int max = 0;
        for ( int i = 1; i <= n ;i++ ) {
            max = Math.max(  max, bestTime1( stock , n- i) + Math.max( stock[n] - stock[n-i],0 ) );
        }
        return max;
    }

}
