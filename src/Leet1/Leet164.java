package Leet1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
    最大间距
 */
public class Leet164 {

    public static void main(String[] args) {
        int[] values = {3,6,10,1};
//        int[] values = {10};
        System.out.println( maxInterval(values) );
    }
    /*
        利用桶排序（桶排序是计数排序的拓展版本）
        N是元素个数，M是桶个数  时间复杂度 = O(N) + O(M * N/Mlog(N/M) ) =  O(N) + O(Nlog(N/M))
        空间复杂度 = O(N) + O(M)
        使用前提是  数组的分布必须均匀
     */
    private static int maxInterval(int[] values ) {
        if( values ==null || values.length <= 1 ) {return 0;}
        int max = values[0],min = values[0];
        for (int i = 1; i < values.length; i++) {
            max = Math.max( max,values[i] );
            min = Math.min( min,values[i] );
        }
        int bucketNum = ( max - min ) / values.length + 1; //+1的原因： 整数除法线下取整，因此余数部分的需要一个桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketNum);
        for (int i = 0; i <= bucketNum; i++) {
            buckets.add( new ArrayList<>() );
        }
        for (int i = 0; i < values.length; i++) {
            buckets.get( ( values[i] - min ) / values.length ).add( values[i] );
        }
        for (int i = 0; i <= bucketNum; i++) {
            Collections.sort( buckets.get(i) );
        }
        int prior = buckets.get(0).get(0);
        int maxInterval = 0;
        for (ArrayList<Integer> bucket:
             buckets) {
            for (Integer v :
                    bucket) {
                maxInterval = Math.max( maxInterval,v - prior );
                prior = v;
            }
        }
        return maxInterval;
    }
}
