package Leet1;

import java.util.Arrays;

/*
    去除重复字母
 */
public class Leet316 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicateLetters2("cbacdcbc")));
        System.out.println(Arrays.toString(removeDuplicateLetters2("bcabc")));
        System.out.println(Arrays.toString(removeDuplicateLetters2("efhaef")));
    }

    //错误...
    public static char[] removeDuplicateLetters( char[] letters ) {

        int[] indexs = new int[26];
        for (int i = 0; i < letters.length; i++) {
            indexs[letters[i] - 'a']++;
        }

        StringBuffer s = new StringBuffer(new String(letters));

        for (int i = 0 ; i < s.length() - 1; ) {
            if( indexs[letters[i] - 'a'] > 1 && s.charAt( i ) >= s.charAt(i + 1) ) {
                s.deleteCharAt( i );
                indexs[letters[i] - 'a']--;
                i--;
            }else {
                i++;
            }
            if( i == -1 ) {
                i = 0 ;
            }
        }
        return s.toString().toCharArray();
    }


    public static char[] removeDuplicateLetters2( String s ) {

        int ASCII_LEN = 256;
        int counter[]  = new int[ASCII_LEN];
        boolean visited[] = new boolean[ASCII_LEN];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)]++;
        }

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counter[ch]--;
            // if the current `ch` has already put into the result.
            if (visited[ch]) continue;

            // if the current `ch` is smaller than the last one char in result.
            // and we still have duplicated last-one char behind, so we can remove the current one.
            while ( !result.isEmpty() && ch < result.charAt(result.length()-1) && counter[result.charAt(result.length()-1)] > 0 ) {
                visited[result.charAt(result.length()-1)] = false;
                result = result.substring(0,result.length()-1);
            }
            result = result + ch;
            visited[ch] = true;
        }

        return result.toCharArray();

    }
}
