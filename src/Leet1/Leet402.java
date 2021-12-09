package Leet1;

/*
    移除k位数字
 */
public class Leet402 {

    public static void main(String[] args) {
        System.out.println( removeKdigits("10200".toCharArray(),1) );
        System.out.println( removeKdigits("1432219".toCharArray(),3) );
        System.out.println( removeKdigits("100".toCharArray(),2) );
    }


    public static String removeKdigits( char[] nums , int k) {

        if( nums.length <= k ) {
            return "";
        }
        int length =  nums.length - k;
        char[] stack = new char[nums.length];
        int top = 0;
        for (int i = 0; i < nums.length; i++) {
            while ( top > 0 && k > 0 && stack[top-1] > nums[i] ) {
                top--;
                k--;
            }
            stack[top++] = nums[i];
        }

        int zeroCount = 0;
        while ( stack[zeroCount] == '0' ) {
            zeroCount++;
        }
        if( length - k - zeroCount <= 0 ) {
            return "0";
        }else {
            return new String( stack,zeroCount,length - k - zeroCount );
        }
    }


}
