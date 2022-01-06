package Leet1;

/**
 * 颠倒二进制位
 * 有两张思路
 */
public class Leet190 {


    public static void main(String[] args) {
        System.out.println( reverseNumber1( 43261596 ) );
        System.out.println( reverseNumber2( 43261596 ) );


    }

    /**
     * 思路：像颠倒整数的每一位时那样做
     * @return
     */
    private static int reverseNumber1( int num ) {

        int res = 0;
        int n = num;
        for (int i = 0; i < 32; i++) {
            res<<=1;
            res+= n & 1;
            n = n >>> 1;
        }
        return res;
    }

    /**
     * 思路：像颠倒字符串的每一个字符那样做
     */
    private static int reverseNumber2( int num ) {
        int temp1,temp2;
        for (int i = 0,j = 31; i < 32 / 2; i++,j--) {
            temp1 = ( num >>> i ) & 1;
            temp2 = ( num >>> j ) & 1;
            if( temp1 > 0 ) {
                num |= 1<<j;
            }else {
                num &= ~( 1<<j );
            }
            if( temp2 > 0 ) {
                num |= 1<<i;
            }else {
                num &= ~( 1<<i );
            }
        }
        return num;
    }
}


