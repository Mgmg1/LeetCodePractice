package Leet1;

/*
    长度最小的子数组
 */
public class Leet209 {

    public static void main(String[] args) {
        System.out.println(way1( new int[]{2,3,1,2,4,3} ,7 ));
    }


    public static int way1( int[] nums ,int s ) {

        int sum1 = nums[0];
        int minLength = nums[0] >= s ? 1 : 0;
        int left = 0,right = 0;

        for (int i = 1; i < nums.length; i++) {
            sum1+=nums[i];
            right++;
            if( sum1 >= s  ) {
                int j = left;
                for (; j < right; j++) {
                    if( sum1 - nums[j] >= s ) {
                        sum1-=nums[j];
                    }else {
                        break;
                    }
                }
                left = j;
                if( minLength == 0 || minLength > right - left + 1 ) {
                    minLength = right - left + 1;
                }
            }
        }
        return minLength;
    }

}
