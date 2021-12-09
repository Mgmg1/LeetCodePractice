package Leet1;

import java.util.HashMap;
import java.util.Map;

/*
    整数转罗马数字
    根据组成规则可以发现，这个问题如同零钱兑换时先兑换最大的，再考虑小的（贪心法,罗马数字存在类似进制的变化）
    贪心算法！！！
 */
public class Leet12 {

    public static void main(String[] args) {

        System.out.println( numToRome(58)  );
        System.out.println( numToRome2(58)  );
        System.out.println( numToRome2(9)  );

    }

    public static String numToRome( int num ) {
        Map<Integer, String> map = new HashMap<>();
        int[] keys = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        map.put(1,"I");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(900,"CM");
        map.put(1000,"M");
        String result = "";
        for (int key:
             keys) {
            while ( num >= key ) {
                result = result + map.get(key);
                num-=key;
            }
        }
        return result;
    }

    public static String numToRome2( int num ) {

        int[] keys = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romes = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String result = "";
        for (int i = 0; i < keys.length; i++) {
            while ( num >= keys[i] ) {
                result = result + romes[i];
                num-=keys[i];
            }
        }
        return result;
    }
}
