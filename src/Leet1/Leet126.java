package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    单词接龙
 */
public class Leet126 {

    public static void main(String[] args) {

        String[] words = {
                "hot","dog","dot","lot","log","cog"
        };
        String beginWord = "hit";
        String endWord = "cog";
        wordsToDragon(words,beginWord,endWord);
    }

    public static void wordsToDragon( String[] words,String beginWord,String endWord ){
        LinkedList<List<String>> result = new LinkedList<>();
        LinkedList<String> temp = new LinkedList<>();
        temp.addLast( beginWord );
        boolean[] visited = new boolean[words.length];
        wordsToDragon(words,result,temp,visited,endWord);
        System.out.println( result );
    }

    /*
        回溯法都是深度优先
        回溯法（关键递归所在循环的范围设置）解决问题，并存储下结果

        需要有一个临时链表 !!
        和一个用于存储结果的 泛型链表  !!

        希望得到  顺序相关 时，每次循环直接从0开始。( 会出现重复元素！！ )
            还希望元素不重复时，引入 访问visited数组 (会出现顺序不同但是组合相同的元素)

        希望得到某种复合条件的组合，即  顺序无关  时，可以通过过参数i指定循环起始位置，缩小循环规模。(还可以保证不重复)
     */
    public static void wordsToDragon(String[] words,
                                     List<List<String>> dragon,
                                     LinkedList<String> temp,
                                     boolean[] visited,
                                     String target
    ) {
        //关键
        for (int j = 0; j < words.length; j++) {
            if(!visited[j] && isOneCharDifferent( temp.get(temp.size()-1),words[j] )) {
                if( words[j].equals( target ) ) {
                    if( dragon.size()!= 0 && dragon.get(0).size() > temp.size() + 1 ) {
                        dragon.clear();
                    }
                    if( dragon.size() == 0 || dragon.get(0).size() == temp.size() + 1 ) {
                        List<String> list = (List<String>) temp.clone();
                        list.add( words[j] );
                        dragon.add(list);
                    }
                    return;
                }
                temp.add( words[j] );
                visited[j] = true;
                wordsToDragon(words, dragon,temp,visited,target);
                visited[j] = false;
                temp.remove( temp.size()-1 );
            }
        }
    }

    public static boolean isOneCharDifferent( String str1,String str2 ) {
        if( str1 == null || str2 == null || str1.length() != str2.length() ) return false;
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if( str1.charAt(i) != str2.charAt(i) ) count++;
        }
        return count == 1;
    }
}
