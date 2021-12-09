package Leet1;

import java.util.*;
import java.util.function.IntFunction;

/*
    与所有单词相关联的字串
    思路：
        将给定的 word排列组合。
        找到所有使得能匹配其排列组合元素的下标起点

        解法：
            1.得到全排列的每一个元素时，和输入串进行一次匹配。但是若有重复元素则不行
            2.用hashmap存下每个word，遍历values进行匹配，
            截取word长度的字串查找hashmap是否存在 或者 已经标记为0
            若标记为0则说明之前已经匹配完成，不合格。若不存在说明匹配失败，不合格


     启发：标记元素出现个数时，如String，可以使用hashmap。
     某种程度下可以使用数组代替
 */
public class Leet30 {

    public static void main(String[] args) {

        System.out.println( Arrays.toString(relevantWord("barfoothefoobarman",new String[]{"foo","bar"})) );

    }

    public static int[] relevantWord( String str,String[] words ) {

        HashMap<String, Integer> map = new HashMap<>();
        for (String word :
                words) {
            if( map.containsKey( word ) ) {
                map.put( word,map.get(word) + 1 );
            }else {
                map.put(word,1);
            }
        }
        //每个单词的长度是一致的
        HashSet<Integer> result = new HashSet<>();
        int wordLength = words[0].length();
        for (int i = 0; i < str.length(); i++) {
            Map<String, Integer> strMap = (Map<String, Integer>) map.clone();
            int j = i;
            while ( j < str.length() && i + wordLength < str.length() ) {
                String word = str.substring(j, j + wordLength);
                Integer integer = strMap.get(word);
                if( integer == null || integer == 0 ) {
                    break;
                }
                strMap.put( word,strMap.get(word) - 1 );
                j = j+3;
            }
            boolean flag = false;
            for (Map.Entry<String,Integer> entry :
                 strMap.entrySet()) {
                if( entry.getValue() != 0 ) {
                    flag = true;
                    break;
                }
            }
            if( !flag ) {
                result.add(i);
            }
        }
        int[] ints = new int[result.size()];
        Object[] objects = result.toArray();
        for (int i = 0; i < objects.length; i++) {
            ints[i] = (int) objects[i];
        }
        return ints;
    }

}
