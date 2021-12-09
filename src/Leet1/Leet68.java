package Leet1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    文本左右对齐
    代码量比较大，有点无聊的题
 */
public class Leet68 {

    public static void main(String[] args) {
//        String[] input = {
//                "This", "is", "an", "example", "of", "text", "justification.","byebye","Hello"
//        };
        String[] input = {
                "What", "must", "be", "acknowledgment", "shall", "be"
        };

        List<String> result = fullJustify(input, 16);
        for (String str :
                result) {
            System.out.println(str);
        }
    }
    /*
        { n } maxWidth，一行最大长度
     */
    public static List<String> fullJustify( String[] words , int n ){
        LinkedList<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int wordsLength = 0;
        int i = 0;
        int lineWordsCount = 0;
        while ( i <= words.length ) {

            if(  i != words.length && lineWordsCount == 0 ) {
                wordsLength = words[i].length();
                lineWordsCount++;
                i++;
            }else if( i != words.length &&  wordsLength + words[i].length() + lineWordsCount - 1 <= n ) {
                wordsLength =  wordsLength + words[i].length() ;
                lineWordsCount++;
                i++;
                // lineWordsCount != 0 防止多出来空白的一行
            }else if( lineWordsCount != 0 ) {
                int interval = lineWordsCount > 1 ? ( wordsLength - lineWordsCount + 1 ) / ( lineWordsCount - 1 ) : n - words[i-1].length() ;
                int leftsWaste = lineWordsCount > 1 ? ( wordsLength - lineWordsCount + 1 ) % ( lineWordsCount - 1 ) : 0 ;
                int whiteLength = interval;
                if( i == words.length ) {
                    leftsWaste = 0;
                    whiteLength = 1;
                }
                for (int j = i - lineWordsCount; j < i  ; j ++) {
                    if( leftsWaste != 0 ){
                        whiteLength++;
                        leftsWaste--;
                    }
                    if( j != i - lineWordsCount  ) {
                        char[] whiteSpaces = new char[whiteLength];
                        Arrays.fill(whiteSpaces,' ');
                        sb.append(whiteSpaces).append(words[j]);
                    }else {
                        sb.append(words[j]);
                    }
                }

                //句尾填充空格,只有一个单词时需要单独处理
                if( i != words.length &&  lineWordsCount == 1 ) {
                    char[] whiteSpaces = new char[whiteLength];
                    Arrays.fill(whiteSpaces,' ');
                    sb.append(whiteSpaces);
                }else if( i == words.length ) {
                    char[] whiteSpaces = new char[n - wordsLength - lineWordsCount + 1];
                    Arrays.fill(whiteSpaces,' ');
                    sb.append(whiteSpaces);
                }

                result.addLast( sb.toString() );
                lineWordsCount = 0;
                wordsLength = 0;
                sb.delete(0,sb.length());

                if( i == words.length ) {
                    break;
                }

            }
        }
        return result;
    }

}
