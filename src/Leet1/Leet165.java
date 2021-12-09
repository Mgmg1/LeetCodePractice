package Leet1;

/*
    比较版本号
 */
public class Leet165 {

    public static void main(String[] args) {
        String version1 = "1.0.1";
        String version2 = "1";
        System.out.println(compareVersion( version1,version2 ));
    }


    private static int compareVersion( String str1,String str2 ) {
        if( str1 == null && str2 == null ) { return 0; }
        if( str1 == null ) { return -1; }
        if( str2 == null ) { return 1; }
        int i = -1,j = -1;
        int priorI,priorJ;

        while ( true ) {
            i++;
            j++;
            priorI = i;
            while ( i < str1.length() && str1.charAt( i ) != '.' ) {
                i++;
            }
            priorJ = j;
            while ( j < str2.length() && str2.charAt( j) != '.' ){
                j++;
            }
            int intI = Integer.parseInt(str1.substring(priorI, i));
            int intJ = Integer.parseInt(str2.substring(priorJ, i));
            if( intI > intJ ) {
                return 1;
            }else if( intI < intJ ){
                return -1;
            }
            if( i == str1.length() && j == str2.length() ) {
                return 0;
            }
            if( i == str1.length() ) {
                return -1;
            }
            if( j == str2.length() ) {
                return 1;
            }
        }
    }

}
