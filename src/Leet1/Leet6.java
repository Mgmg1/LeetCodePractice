package Leet1;


/*
    Z字形变换
    根据 下，上，下。。。。两个循环反复，将字符映射到对应的string上
 */
public class Leet6 {

    public static void main(String[] args) {
        System.out.println( zTransform("PAYPALISHIRING",3) );
    }


    public static String zTransform( String str,int numRows ) {

        StringBuilder[] strings = new StringBuilder[numRows];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new StringBuilder();
        }

        int k = 0;
        int j = 0;
        while ( k < str.length() ) {
            for (int i = 0; i < numRows - 1; i++) {
                if( k < str.length() ) {
                    strings[j] = strings[j].append(str.charAt(k++));
                    j++;
                }else {
                    break;
                }
            }
            for (int i = 0; i < numRows - 1; i++) {
                if( k < str.length() ) {
                    strings[j] = strings[j].append(str.charAt(k++));
                    j--;
                }else {
                    break;
                }
            }
        }
        for (int i = 1; i < numRows; i++) {
            strings[0].append(strings[i]);
        }
        return strings[0].toString();
    }

}
