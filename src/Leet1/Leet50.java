package Leet1;

/*
    Pow(x,n) 算出x的n次方
    思路：
        不是 直接乘以n次x，
        而是使用指数的位运算 (注意，指数运算时相乘)
 */
public class Leet50 {

    public static void main(String[] args) {

        System.out.println( pow( 2,-2 ) );

    }

    /*
        不考虑 0^0的情况
        //指数二进制： x = 2，n = 10 时， x^1100 = x^1000 * x^0100
        //而 x^1000 = x^0100 *  x^0100 ,因此 current*=curren

     */
    public static float pow( float x,int n ) {

        if( x == 0 ) return 0;
        if( n == 0 ) return 1;
        if( n < 0 ) {
            x = 1/x;
            n = -n;
        }
        float result = 1;
        float current = x;
        while ( n > 0 ) {
            if( (n & 1) == 1 ) {
                result*=current;
            }
            n = n >> 1;
            //指数二进制： x^1000 = x^0100 *  x^0100 ,因此 current*=current
            current*=current;
        }
        return result;
    }

}
