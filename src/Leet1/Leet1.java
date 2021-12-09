package Leet1;

import java.util.HashMap;
import java.util.Map;

/*
     两数之和
 */
public class Leet1 {

    public static void main(String[] args) {
        System.out.println( twoNumSum(new int[]{2,7,11,15},9) );
    }


    /*
        利用hahsmap 将value 和 index 反转，
        一般用于寻找value之间的关系，而 key 又难以映射时
        这种做法恰好防止存在相同的key时，value被覆盖
     */
    public static boolean twoNumSum(int[] arr,int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length ; i++) {
            if( map.containsKey( target - arr[i] ) ) {
                return true;
            }
            map.put( arr[i],i );
        }
        return false;
    }

}
