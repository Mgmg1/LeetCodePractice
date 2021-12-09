package Leet1;

/*
    x的平方根
    实现 int sqrt( x )
    利用类似二分的 夹逼法，同时要向下取整
 */
public class Leet69 {

    public static void main(String[] args) {
//        for (int i = 1; i < 26; i++) {
//            System.out.println(sqrt(i)+"  "+i);
//        }
        System.out.println(sqrt(25));
    }

    /*
        假定 x >= 1
     */
    public static int sqrt( int x ) {
        int left = 1,right = x;
        int mid = 0;
        while ( true ) {
            mid = ( left + right ) / 2;
            if( mid*mid > x ) {
//                if( mid - 1 <= right / ( mid - 1 ) ) {
//                    return (int) (mid - 1);
//                }
                right = mid  ;
            } else {
                if( ( mid + 1 ) * ( mid + 1  ) > x  ) {
                    return mid ;
                }
                left =  mid ;
            }
        }

    }


}
