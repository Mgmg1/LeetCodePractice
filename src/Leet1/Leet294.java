package Leet1;

import java.util.HashMap;

/*
    翻转游戏II
 */
public class Leet294 {

    public static void main(String[] args) {
        System.out.println( isFirstWin(new HashMap<>(),"++++") );
    }


    public static boolean isFirstWin(HashMap<String,Boolean> map,String str) {

        for (int i = 1; i < str.length(); i++) {
            if( str.charAt(i) == '+' && str.charAt( i - 1) == '+' ) {
                String newStr =  str.substring(0,i-1) + "--" + str.substring(i+1);
                if( map.containsKey(newStr)  ) {
                    if( !map.get(newStr) ) {
                        return true;
                    }
                    continue;
                }
                boolean result = isFirstWin(map, newStr);
                map.put(newStr,result);
                if( !result ) {
                    return true;
                }
            }
        }
        return false;
    }

}
