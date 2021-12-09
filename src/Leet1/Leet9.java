package Leet1;

import java.util.Scanner;

/*
    回文数
 */
public class Leet9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
//        System.out.println(num + "是否是回文数:"+way1( num ));
        System.out.println(num + "是否是回文数:"+way2( num ));

    }

    /*
        将整数转为String来判断的方法
     */
    public static boolean way1( int num ) {

        if( num < 0  ) {
            return false;
        }
        String numStr = Integer.toString(num);
        boolean flag = false;
        int i = numStr.length()/2,j = numStr.length()/2;
        if( numStr.length() % 2 == 0 ) {
            i--;
        }
        while (  i>=0 && j < numStr.length() && numStr.charAt( i ) == numStr.charAt( j )  ) {
            i--;
            j++;
        }
        return i == -1;
    }

    /*
        不将整数转为String来判断的方法
        假如正整数是回文数，必定翻转后大小相等,此时要求个位数字不为0
        也不能是0，是0返回false。假如不止一位，个位是0，则该数反转后必定变化，因为最高位变为0了
     */
    public static boolean way2( int num ) {

        if( num == 0 ) {
            return true;
        }
        if( num < 0 || num % 10 == 0 ) {
            return false;
        }

        int reverseNum = 0,cNum = num;
        while ( cNum != 0 ) {
            reverseNum = reverseNum * 10 + cNum % 10;
            cNum = cNum / 10;
        }
        return num == reverseNum;
    }

}
