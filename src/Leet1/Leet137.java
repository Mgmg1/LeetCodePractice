package Leet1;

/*
    只出现一次的数字II
 */
public class Leet137 {

    public static void main(String[] args) {

        int[] input = {2,4,2,2,1,1,1,0,0,0};
//        System.out.println( onlyOnceAppearNumber1(input) );
        System.out.println( singleNum(input) );
    }

    public static int onlyOnceAppearNumber1( int[] nums ) {
        int[] ints = new int[32];
        int result = 0;
        int mask = 1;
        for (int i = 0; i < ints.length; i++) {
            for (int num :
                    nums) {
                if( ( mask & num ) != 0 ) {
                    ints[i]++;
                }
            }
            if( ints[i] % 3 == 1 ) {
                result+=mask;
            }
            mask<<=1;
        }
        return result;
    }

    public static int singleNum( int[] nums ) {

//        int counter1 = 0;
//        int counter2 = 0;
//        int mask = 0;
//        for (int num : nums) {
//            counter2 ^= counter1 & num;
//            counter1 ^= num;
//            mask = ~(counter1 & counter2);
//            counter1 &= mask;
//            counter2 &= mask;
//        }
//        return counter1;

//        int one = 0;
//        int two = 0;
//        for (int i = 0; i < nums.length; i++){
//            int element = nums[i];
//            int T = two;
//            int O = one;
//            two = T ^ (T & element); //去除出现过三次的比特位1
//            element = element ^ (T & element); //去除出现过三次的比特位1
//            two = two | (element & O); //设置出现两次的比特位1
//            one = one ^ element; //设置只出现一次的比特位1
//        }
//        return one;

        int one = 0,two = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp1 = two;
            int temp2 = nums[i];
            two = two & ~temp2;
            temp2 = temp2 & ~temp1;
            temp1 = one;
            one = one ^ temp2;
            two = two | (temp1 & temp2);
        }
        return one;
    }

}
