package Leet1;

import java.util.Arrays;

/*
    跳跃游戏
    递归，dp，贪心法
 */
public class Leet55 {

    public static void main(String[] args) {
        int[] stepArr1 = new int[]{2,3,1,1,4};
        int[] stepArr2 = new int[]{3,2,1,0,4};
//        Boolean result1 = canJump1(stepArr2);
//        Boolean result3 = canJump3(stepArr1,0);
        Boolean result2 = canJump2(stepArr2);
        System.out.println(result2);
    }

    //贪心法
    public static Boolean canJump1( int[] stepArr ){

        int maxDistance = stepArr[0];
        for (int i = 1; i <stepArr.length; i++) {
            maxDistance = Math.max(maxDistance, i + stepArr[i]);
            if( maxDistance == i  ) {
                return  false;
            }
        }
        return true;
    }


    //动态规划(dp),动态规划可以一句话概括，就是要自底向上，依赖之前的结果退出后面的结果
    public static Boolean canJump2( int[] stepArr ){
        boolean[] canJump = new boolean[stepArr.length];
        Arrays.fill(canJump,false);
        canJump[0] = true;
        for (int i = 0; i < canJump.length ; i++) {
            if( !canJump[i] ){
                return false;
            }
            for (int j = 1; j <= stepArr[i]; j++) {
                canJump[i+j] = true;
            }
        }
        return true;
    }

    //一般递归解法,存在重复计算的部分....若满足某些条件可以转为动态规划
    public static Boolean canJump3( int[] stepArr ,int i){

        if( i == stepArr.length -1  ) {
            return true;
        }

        boolean flag = false;
        for (int j = 1; j <= stepArr[i] ; j++) {
            flag= flag || canJump3(stepArr,i+j);
        }
        return flag;
    }

}
