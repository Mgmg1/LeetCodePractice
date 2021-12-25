package Leet1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 重复的DNA序列
 */
public class Leet187 {

    public static void main(String[] args) {
        String dnaStr = "AAAAACCCCCAAAAACCCCCAAAAAGGGTT";
//        String dnaStr = "AACCAA";
        System.out.println(Arrays.toString( duplicateDNASequence( dnaStr ) ));
    }

    /*
        题目要求是得出重复的长度超过10的重复序列(子串)，
     */
    private static String[] duplicateDNASequence( String DNAStr ) {
        if( DNAStr == null || DNAStr.length() <= 10 ) { return new String[0]; }
        LinkedList<String> result = new LinkedList<>();

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < DNAStr.length() - 10; i++) {
            String substring = DNAStr.substring(i, i + 10);
            map.put( substring,map.getOrDefault(substring,0) + 1 ) ;
        }
        for (Map.Entry<String, Integer> entry :
               map.entrySet() ) {
            if( entry.getValue() > 1 ) {
                result.add( entry.getKey() );
            }
        }
        return result.toArray(new String[0]);
    }
}
