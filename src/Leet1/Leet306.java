package Leet1;



import java.util.Arrays;

/*
    累加数

 */
public class Leet306 {

    public static void main(String[] args) {
        char[] chars2 = {
                '1', '1', '2', '3', '5', '8'
        };
        char[] chars1 = {
                '1', '9', '9', '1', '0', '0',
                '1','9','9'
        };
        boolean flag = false;
//        for (int i = 0; i <= (chars2.length - 1)/2; i++) {
//            flag = way1( chars2,0, i + 1);
//            if( flag ) {
//                break;
//            }
//        }
        for (int i = 0; i < (chars2.length - 1)/2; i++) {
            if( chars2[ i + 1] == '0' ) {
                continue;
            }
            int left = Integer.parseInt(new String(Arrays.copyOfRange(chars2, 0, i + 1)));
            for (int j = i + 1; j < chars2.length; j++) {
                int right = Integer.parseInt(new String(Arrays.copyOfRange(chars2, i + 1, j + 1)));
                Integer i1 = left + right;
                if( i1.toString().length() + j + 1 <= chars2.length && i1.toString().equals(
                        new String(chars2,j + 1,i1.toString().length())
                ) ){
                    flag = way2(chars2,i + 1,j - i,i1.toString().length());
                    if( flag ) System.out.println(flag);
                    return;
                }
            }
        }
    }

    //误解题意的做法，实际上需要将前一个递归方法中确定的两个数利用，这里只利用了一个
    public static boolean way1( char[] chars ,int k,int leftLength) {
        if( chars[k + leftLength] == '0' ) {
            return false;
        }
        int left = Integer.parseInt(new String(chars,k,leftLength));
        for (int j = k + leftLength; j < chars.length ; j++) {
            if( j + 1 == chars.length ) {
                return true;
            }
            int right = Integer.parseInt( new String(Arrays.copyOfRange(chars,k + leftLength,j + 1)) );
            String s = Integer.toString(left + right);

            if( s.length() + j >= chars.length ||
                    !s.equals(  new String(Arrays.copyOfRange(chars,j+1,j+1+s.length())))  ) {
                continue;
            }
            if(  way1( chars,k + leftLength,Integer.toString(right).length()) ) {
                return true;
            }
        }
        return false;
    }

    public static boolean way2( char[] chars,int k,int leftLength,int rightLength ) {

        if( k + leftLength + rightLength == chars.length ) {
            return true;
        }

        int left = Integer.parseInt(new String(chars, k, leftLength));
        int right = Integer.parseInt(new String(chars, k + leftLength, rightLength));
        Integer plus = left + right;
        int newRightEnd = plus.toString().length() + k + leftLength +rightLength;
        if( newRightEnd > chars.length ) {
            return false;
        }
        String plusStr = new String(chars,k + leftLength + rightLength,plus.toString().length());
        if( plusStr.equals(plus.toString()) ) {
            return way2(chars, k + leftLength, rightLength,plusStr.length());
        }
        return false;
    }

}
