package Leet1;

import com.sun.prism.PresentableState;

/*
    两数相除
 */
public class Leet29 {

    public static void main(String[] args) {

        System.out.println( divideTwoNum( 1<<31,1<<30 ) );
        System.out.println( (1<<31)/(1<<30) );
    }

    public static int divideTwoNum(int dividend ,int divisor ) {

        //思路
        //都转化成 正整数处理,对负数最小值进行判断确保转化为正数时不溢出
        int minInt = 1<<31;
        if( dividend == minInt && divisor == minInt ) {
            return 1;
        }
        if( divisor == minInt ) {
            return 0;
        }
        //被除数 特殊处理
        int result = 0;
        if( dividend == minInt ) {
            //因为结果先显示为正数，再加上正负性。要防止结果溢出
            if(  divisor == -1 ) return ~(1<<31);
            if( divisor == 1 ) return dividend;
            //妥协，先取出一个divisor
            dividend = dividend + ( divisor > 0 ? divisor : -divisor );
            result++;
        }
        int dividendPlus = Math.abs( dividend );
        int divisorPlus = Math.abs( divisor );
        //转化为正数时，当 除数 大于等于 overTestNum 时，说明除数再次扩大时会溢出
//        int overTestNum = 1<<30;
        while ( dividendPlus >= divisorPlus ) {
            //因为dividendPlus >= divisorPlus 起始条件设置为 k = 1;
            int k = 1;
            //divisorPlus<<k > 0 说明正数溢出为负数
            while ( divisorPlus<<k > 0 && dividendPlus >= divisorPlus<<k ) {
                k++;
            }
            k--;
            dividendPlus-=divisorPlus<<k;
            result+= 1<<k;
        }
        return ( dividend >=0 && divisor >0 ) || ( dividend <0 && divisor <0 ) ? result : -result;
    }

}
