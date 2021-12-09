package Leet1;

import java.util.Arrays;

/*
    格雷编码
 */
public class Leet89 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString( way1(2) ));
//        System.out.println(Arrays.toString( way1(3) ));
        System.out.println(Arrays.toString( way2(3) ));
        System.out.println(Arrays.toString( grayCode(3) ));
    }

    public static int[] way1( int n ) {

        if( n <= 0 ) {
            return null;
        }

        int length = (int) Math.pow(2, n);
        int[] result = new int[length];
        result[0] = 0;
        result[1] = 1;
        if( n == 1 ) {
            return result;
        }
        int i = 2;
        int secondary = 1;
        while ( i < length ) {
            int addition = (int) Math.pow(2,secondary);
            //这种需要逆回去的结构可以使用栈解决，但是要注意平衡好 空间占用
            for (int j = i-1; j >= 0 ; j--) {
                result[i] = addition + result[j];
                i++;
            }
            secondary++;
        }

        return result;
    }

    public static int[] way2( int n ) {

        return way22(new int[(int) Math.pow(2, n)], n);

    }

    public static int[] way22(int[] result,int times) {

        if( times == 1  ) {
            result[0] = 0;
            result[1] = 1;
            return result;
        }
        int addition = (int) Math.pow(2,times - 1);
        way22(result,times - 1);
        int j = addition ;
        for (int i = addition - 1; i >=0; i--) {
            result[j++] = result[i] + addition;
        }
        return result;
    }


    public static int[] grayCode( int n ) {

        if( n <= 0 ) {
            return new int[0];
        }
        int length = (int) Math.pow(2,n);
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i ^ ( i >> 1 );
        }
        return result;
    }

}
