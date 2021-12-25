package Leet1;

/**
 * Excel表列序号
 * 是Leet168的逆版本
 */
public class Leet171 {

    public static void main(String[] args) {
        System.out.println( excelTableColumnNumber( "ZY" ) );
    }


    public static int excelTableColumnNumber( String str ) {
        char[] chars = str.toCharArray();
        int num = 0;
        int carry = 1;
        for (int i = chars.length - 1; i >= 0 ; i--) {
            num+= carry * ( chars[i] - 'A' + 1 );
            carry*=26;
        }
        return num;
    }
}
