package Leet1;

/**
 * 快乐数
 */
public class Leet202 {

    public static void main(String[] args) {
        System.out.println( happyNumber(23227) );
    }

    /**
     * 大致分析可知，输入的大小变化后不会超过999
     * @return
     */
    private static boolean happyNumber( int num ) {
        int length = 999;
        int[] flags = new int[length];
        int newNum;
        while ( !hasOneOne(num) ) {
            //num < length ，num刚输入时，可能大于等于 length
            if( num < length && flags[num] == 1 ) {
                return false;
            }
            if( num < length ) {
                flags[num] = 1;
            }
            newNum = 0;
            while ( num != 0 ) {
                newNum+= Math.pow(num%10,2);
                num /= 10;
            }
            num = newNum;
        }
        return true;
    }
    private static boolean hasOneOne( int num ) {
        while (  num != 0 && num % 10 == 0 ) {
            num /= 10;
        }
        return num == 1;
    }
}
