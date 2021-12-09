package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    二进制手表
    先根据已给的条件求出所有数字二进制表示时1的个数，再不断更改分配给hour部分的以亮led的个数，得到所有可能组合
    有够无聊的一道题
 */
public class Leet401 {

    public static void main(String[] args) {
        List<String> strings = bManager(4);
        for (String time :
                strings) {
            System.out.println(time);
        }
    }

    public static List<String> bManager(int n){
        int[] ints = new int[60];
        for (int i = 0; i < 60; i++) {
            int num = i;
            while( num != 0 ){
                if( num%2 == 1 ){
                    ints[i]++;
                }
                num>>=1;
            }
        }
        LinkedList<String> results = new LinkedList<>();
        int left = n%5;
        int right = n - left;
        while ( left >= 0 ){
            LinkedList<String> lefts = new LinkedList<>();
            for (int i = 0; i <= 11; i++) {
                if( ints[i] == left ){
                    lefts.addLast(Integer.toString(i));
                }
            }
            for (int i = 0; i < 60; i++) {
                if( ints[i] == right ){
                    for (String hour :
                            lefts) {
                        results.add( hour + ':' + (i < 10 ? "0"+i : Integer.toString(i)) );
                    }
                }
            }
            left--;
            right++;
            lefts.remove();
            lefts = null;
        }
        return results;
    }

}
