package Leet1;

/*
    验证回文串
 */
public class Leet125 {

    public static void main(String[] args) {

        String str = "A man, a plan, a canal: Panama";
        System.out.println( isPalindromeStr(str) );
    }


    public static boolean isPalindromeStr(String str) {

        if( str == null || str.length() <= 1 ) return true;

        char[] chars = str.toCharArray();
        int i = 0,j = chars.length - 1;

        while ( i < j ) {
            while ( !isAlphaAndNum(chars[i]) && i < j ) i++;
            while ( !isAlphaAndNum(chars[j]) && i < j ) j--;
            if( i >= j ) return true;
            if( !equalIgnoreCase(chars[i],chars[j] ) ) return false;
            i++;j--;
        }
        return true;
    }

    public static boolean isAlphaAndNum( char c ) {
        return  ( c >= '0' && c <= '9' ) || ( c >= 'A' && c <= 'Z' ) || ( c >= 'a' && c <= 'z' );
    }

    public static boolean equalIgnoreCase( char c1,char c2 ) {
        c1 = ( c1 >= 'A' && c1 <= 'Z' ) ? (char) (c1 + 32) : c1;
        c2 = ( c2 >= 'A' && c2 <= 'Z' ) ? (char) (c2 + 32) : c2;
        return c1 == c2;
    }
}
