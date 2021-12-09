package Leet1;

/*
    加油站
 */
public class Leet134 {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas,cost));
        System.out.println(canCompleteCircuit2(gas,cost));
        System.out.println(canCompleteCircuit3(gas,cost));
    }


    //方法1  比较麻烦的做法,也是利用了贪心法的特性
    //发现贪心法就是在不断维护数据集合某个特性，可以因此而假如或者丢弃某个数据来更新数据集状态，然后继续使用贪心思想
    public static int canCompleteCircuit( int[] gas,int []cost ) {
        boolean flag = false;
        int start = 0,end = 0;
        int sum = 0;
        while( start < gas.length && (!flag || end != start) ) {
            if( sum < 0 ) {
                sum = sum - gas[start] + cost[start] ;
                start++;    //
            }else {
                sum = sum + gas[end] - cost[end];
                if( end + 1 == gas.length ) {
                    end = 0;
                    flag = true;
                }else {
                    end++;  //
                }
            }
        }
        if( start < gas.length ) return start;
        return -1;
    }

    //第二种做法，一般来说，贪心法都在维护某个种可衡量的值，正负或者大小
    //假若 start 不唯一，这个方法也能求出start
    //如果可以采用贪心法，那么可以从题解反向寻找思路，即贪心法成立时，在维护数据时，算法所选择数据具有必然性。在这个题解可以体现
    public static int canCompleteCircuit2 ( int[] gas,int []cost ) {

        int store = 0;
        int start = -1;
        for ( int i = 0; i < gas.length;i++ ) {
            if( start < 0 ) {
                start = i;
            }
            store+=( gas[i] - cost[i] );
            if( store < 0) {
                store = 0;
                start = -1;
            }
        }
        if( start > 0 ) {
            for (int i = 0; i < start; i++) {
                store += gas[i] - cost[i];
                if( store < 0 ) {
                    return -1;
                }
            }
        }
        return  start;
    }

    //也是贪心法，但是得结合具体场景，如 sum < 0 和 sum > 0 的选择
    public static int canCompleteCircuit3( int[] gas,int []cost ) {

        if( gas.length == 1 ) {
            return gas[0] >= cost[0] ? 0 : -1;
        }
        int start = gas.length - 1;
        int end = gas.length - 1;
        int sum = gas[start] -cost[start];
        do {
            if( sum < 0  ) {
                start = ( start - 1 + gas.length ) % gas.length;
                sum+= gas[start] - cost[start];
            }else {
                end = ( end + 1 ) % gas.length;
                sum += gas[end] - cost[end];
            }
        }while ( start != end  );

        return sum > 0 ? start : -1;
    }
}
