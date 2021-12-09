package Leet1;

/*
    Excel表列名称
    思路：实际上是 10进制转26进制 ( 0 ~ 26 有26个数字 ),主要问题在于  要进行偏移量修正!!!该题偏移量为1
 */
public class Leet168 {
    public static void main(String[] args) {
        int val = 701;
        System.out.println(  toExcelColumnName( val ) );
    }

    private static String toExcelColumnName( int val ) {
        if( val < 1 ) { return ""; }
        StringBuilder sb = new StringBuilder();
       do{
            val--;
            sb.append( (char) (val % 26 + 'A'  ) );
            val = val / 26;
        }while ( val != 0 );
        return sb.reverse().toString();
    }
}
