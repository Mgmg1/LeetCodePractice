package Leet1;

/**
 * 位1的个数
 */
public class Leet191 {

    public static void main(String[] args) {
//        System.out.println( oneCount1(128) );
        System.out.println( oneCount2(128) );
    }

    /**
     * 直接计算1的个数,注意可能有负数
     * 要使用逻辑右移 或者  1<<i
     * @param num
     * @return
     */
    private static int oneCount1( int num ) {
        int result = 0;
        //逻辑右移
        while ( num != 0 ) {
            result+= num & 1;
            num>>>=1;
        }
        //让1左移
//        for (int i = 0; i < 32 ; i++) {
//            result+= (num & 1<<i) != 0 ? 1 : 0;
//        }
        return result;
    }

    /**
     *
     * @param num
     * @return
     */
    private static int oneCount2( int num ) {
        int count = 0;
        while ( num != 0 ) {
            count++;
            num = ( num - 1 ) & num;
        }
        return count;
    }
}
