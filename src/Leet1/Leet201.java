package Leet1;

/**
 * 数字范围按位与
 * 写出范例就可以发现规律
 */
public class Leet201 {

    public static void main(String[] args) {
//        System.out.println( andWithNumber( 5,7 ) );
        System.out.println( andWithNumber( 1,8 ) );
    }

    private static int andWithNumber( int start,int end ) {
        if( start > end || start < 0 ) { return 0; }

        int begin = start,last = end;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            // end - start < 1<<i 位的值跨越
            if( last >= 1<<i && begin >= 1<<i && end - start < 1<<i  ) {
                result+=1<<i;
            }
            if( last >= 1<<i ) {
                last-=1<<i;
            }
            if( begin >= 1<<i ) {
                begin-=1<<i;
            }
        }
        return result;
    }

}
