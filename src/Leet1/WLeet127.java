package Leet1;

import java.util.LinkedList;

/*
    单词接龙
 */
public class WLeet127 {


    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();
        list.add("hot");
        list.add("dot");
        list.add("lot");
        list.add("dog");
        list.add("log");
        list.add("cog");
        LinkedList<LinkedList<String>> linkedLists = way1("cog", "hit", list);
        System.out.println("");

    }

    //求出了所有的接龙，没有直接算出最小的个数。使用了递归
    public static LinkedList<LinkedList<String>> way1(String target,String before,LinkedList<String> remains) {
        LinkedList<LinkedList<String>> lists = new LinkedList<>();
        for (int i = 0; i < remains.size(); i++) {
            String next = remains.get(i);
            if( match( before,next ) ) {
                if( target.equals( next ) ) {
                    LinkedList<LinkedList<String>> list = new LinkedList<>();
                    list.add( new LinkedList<String>() );
                    list.getFirst().addFirst( target );
                    return list;
                }
                remains.remove( i );
                LinkedList<LinkedList<String>> result = way1(target, next, remains);
                for (LinkedList<String> list:
                        result) {
                    list.addFirst(next);
                }
                lists.addAll( result );
                remains.add( i,next );
            }
        }
        return lists;
    }

    public static boolean match( String word,String other ) {
        int mismatchCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if( word.charAt( i ) != other.charAt( i ) ) {
                mismatchCount++;
            }
            if( mismatchCount > 1 ) {
                return false;
            }
        }
        return mismatchCount != 0;
    }


    //非递归算法
    public static void way1( String target,String before ) {




    }

}
