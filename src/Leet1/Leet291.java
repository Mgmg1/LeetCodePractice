package Leet1;

/*
    单词规律II
 */
public class Leet291 {

    public static void main(String[] args) {
//        String pattern = "abab";
//        String str = "redblueredblue";
//        String pattern = "aaaa";
//        String str = "asdasdasdasd";
        String pattern = "aabb";
        String str = "xyzabcxzyabc";


        String[] patternStr = new String[256];
        System.out.println( isMatchPattern(patternStr,pattern,str));
    }

    public static boolean isMatchPattern(String[] patternStr,
                                         String pattern,String str ) {

        if( pattern.isEmpty() && str.isEmpty() ) {
            return true;
        }else if( pattern.isEmpty() || str.isEmpty() ) {
            return false;
        }
        char c = pattern.charAt(0);
        for (int i = 1; i <= str.length(); i++) {
            if( patternStr[c] != null ) {
                return  str.length() >= patternStr[c].length() &&
                        patternStr[c].equals(str.substring(0,patternStr[c].length())) &&
                        isMatchPattern(patternStr, pattern.substring(1), str.substring(patternStr[c].length()));
            }else {
                patternStr[c] = str.substring(0,i);
                if( isMatchPattern(patternStr, pattern.substring(1), str.substring(i)) ) {
                    return true;
                }else {
                    patternStr[c] = null;
                }
            }
        }
        return false;
    }

}
