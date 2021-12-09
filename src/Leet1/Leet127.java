package Leet1;

/*
    单词接龙
 */
public class Leet127 {

    public static void main(String[] args) {
        String[] words = {
                "hot","dog","dot","lot","log","cog"
        };
        String beginWord = "hit";
        String endWord = "cog";
        int i = wordDragon(words, beginWord, endWord);
        System.out.println( i );
    }

    
    public static int wordDragon(
            String[] wordList,
            String beginWord,
            String endWord ) {
        if( wordList == null || wordList.length == 0 || beginWord == null
                || endWord == null ) return 0;
        if( beginWord.equals( endWord ) ) return 1;

        boolean[] isVisited = new boolean[wordList.length];
        int min = Integer.MAX_VALUE;               // ~(1<<31)
        for (int i = 0; i < wordList.length; i++) {
            if( isOneAlphaDiff(beginWord,wordList[i]) ) {
                isVisited[i] = true;
                int length = wordDragon(wordList, wordList[i], endWord,isVisited, 2);
                min = Math.min( min,length );
                isVisited[i] = false;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static int wordDragon(
            String[] wordList,
            String beginWord,
            String endWord,
            boolean[] isVisited,
            int count) {
        if( beginWord.equals( endWord ) ) return count ;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < wordList.length; i++) {
            if( !isVisited[i] && isOneAlphaDiff(beginWord,wordList[i]) ) {
                isVisited[i] = true;
                int length = wordDragon(wordList, wordList[i], endWord,isVisited, count + 1);
                min = Math.min( min,length );
                isVisited[i] = false;
            }
        }
        return min;
    }

    public static boolean isOneAlphaDiff(String str1,String str2 ) {
        if( str1 == null || str2 == null || str1.length() != str2.length() ) return false;
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if( str1.charAt( i ) != str2.charAt(i) ) count++;
            if( count > 1 ) return false;
        }
        return count == 1;
    }
}
