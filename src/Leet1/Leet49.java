package Leet1;

import java.util.*;

/*
    字母异位词分组
 */
public class Leet49 {

    public static void main(String[] args) {

        String[] words  =  {
                "eat","tea",
                "tan","ate","nat"
        };
        List<String>[] lists = way1(words);
        for (List<String> list :
                lists) {
            System.out.println( list);
        }

    }

    /*
           letterSort
           new 泛型数组引用时，不需要加泛型标记
           思路：
           用int[] 数组记录下每个单词的引用次数，再生成最小次序作为 map 的 key （无法直接使用int[] 作为key）
           假如 得出来的key相同，说明是字母异序的单词！！！
     */
    public static List<String>[] way1(String[] words ) {

        HashMap<String, List<String>> map = new HashMap<>();
        int[] records= new int[26];
        StringBuilder sb = new StringBuilder();
        for (String word:
             words) {
            Arrays.fill( records,0 );
            for (int i = 0; i < word.length(); i++) {
                records[ word.charAt(i) - 'a' ]++;
            }
            for (int i = 0; i < records.length; i++) {
                //这里可以简化为  字母拼接records[i]，取代不断append的做法
                for (int j = 0; j < records[i]; j++) {
                    sb.append( 'a' + i );
                }
            }
            List<String> list = map.getOrDefault(sb.toString(), new LinkedList<>());
            list.add( word );
            map.put( sb.toString(),list );
            sb.delete(0, sb.capacity());
        }
        return map.values().toArray( new LinkedList[0] );
    }





}
