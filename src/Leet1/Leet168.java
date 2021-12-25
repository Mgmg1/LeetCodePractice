package Leet1;

/*
    Excel表列名称
    思路：实际上是 10进制转26进制 ( 0 ~ 26 有26个数字 ),主要问题在于  要进行偏移量修正!!!该题偏移量为1
 */
public class Leet168 {
    public static void main(String[] args) {
        int val = 28;
        System.out.println(  toExcelColumnName( val ) );
        System.out.println(  toExcelColumnName2( val ) );
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

    /*
        新的解法，比较好理解
        和一般的进制不太一样地方：假如这是k进制，则某一位的最大值是k，最小值是1（ 一般的进制是 最小值是0，最大值是k-1 ）
     */
    private static String toExcelColumnName2( int val ) {
        if( val < 1 ) { return ""; }
        StringBuilder sb = new StringBuilder();
        while ( val != 0 ) {
            if( val % 26 == 0 ) {
                sb.append( 'Z' );
                val = val / 26 - 1;
            }else {
                sb.append( (char) (val % 26 + 'A' - 1));
                val /= 26;
            }
        }
        return sb.reverse().toString();
    }
}
