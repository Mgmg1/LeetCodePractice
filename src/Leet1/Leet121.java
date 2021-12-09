package Leet1;


/*
    买卖股票的最佳时机
    只能买一次
 */
public class Leet121 {

    public static void main(String[] args) {

        int[] values = {7,1,5,3,6,4};
//        System.out.println( bestTimeForBuyStock(values) );
        System.out.println( bestTimeForBuyStock(values,0,values.length - 1) );
    }

    /*
        O(n) 的解法
        证明：假如存在一个购买的最佳时机 0<= i < j，则必然有  value[i] 是 0~j-1 中的最小元素
     */
    public static int bestTimeForBuyStock(int[] value ) {

        if( value == null || value.length <= 1 ) return 0;
        int min = value[0];
        int maxProfit = 0;
        for (int i = 1; i < value.length ; i++) {
            maxProfit = Math.max( value[i] - min,maxProfit );
            min = Math.min( min,value[i] );
        }
        return maxProfit;
    }

    /*
        O(nlogn)解法
        分治法，参考快速排序
     */
    public static int bestTimeForBuyStock(int[] value,int start,int end) {

        if( value == null || value.length <= 1 ) return 0;

        if( end <= start ) return 0;

        if( start == end -1 ) return Math.max( value[end] - value[start] ,0 );

        int pivot = ( start + end ) / 2;
        int leftMax = 0;
        int rightMax = 0;
        int i = pivot - 1,j = pivot + 1;
        while ( i >= start || j <= end ) {
            if( i >= start ) {
                leftMax = Math.max( value[pivot] - value[i--],leftMax  );
            }
            if( j <= end ) {
                rightMax = Math.max( value[j++] - value[pivot],rightMax  );
            }
        }
        return Math.max( Math.max( bestTimeForBuyStock(value,start,pivot - 1),bestTimeForBuyStock( value,pivot + 1,end ) ),leftMax + rightMax );

    }
}
