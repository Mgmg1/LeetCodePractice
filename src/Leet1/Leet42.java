package Leet1;

/*
    接雨水
    想象柱形图缩小后，类似于积分那样不断缩小，呈现出了 曲线！！！
    可以发现有一个个 类似的 极值点！！

    思路1：。。。
    思路2:使用两个数组，分别记录  分别记录从 左边到当前i 的元素中的最大值 和 从 右边到当前i 的元素中的最大值 （包括i）
          则 ( Math.min( lefts[i],rights[i] ) - nums[i] )
          可想而知，只要两边皆大于它，则雨水最终会被兜住

    思路3：得到nums中得到最大值下标i，
    再分别 从左边到 i  ，从右边到 i （不包括i）。同时分别记录 从左边到当前i 和 从右边到当前i 的最大值
    则 lMax - nums[i]  和  lMax - nums[i]


 */
public class Leet42 {

    public static void main(String[] args) {
        int[] ints = {
                0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1
        };
        System.out.println( way1( ints ) );
        System.out.println( way2( ints ) );
        System.out.println( way3( ints ) );
    }

    /*
        思路1（自创）：
        从左遍历到右，nums[0] 为一个极值点，找到 一个 大于等于 该点的值，返回，num[i]减去极值点 加上result；
        再从右遍历到左 num[num.length - 1] 为一个极值点，找到 一个 大于 该点的值，返回，num[i]减去极值点 加上result；
     */
    public static int way1( int[] nums ) {

        int left = 0;
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            if( nums[i] >= nums[left] ) {
                for (int j = left + 1; j < i ; j++) {
                    sum+=nums[left] - nums[j];
                }
                left = i;
            }
        }
        int right = nums.length -1;
        for (int i = nums.length - 2; i >= 0 ; i--) {
            if( nums[i] > nums[right] ) {
                for (int j = right - 1; j > i ; j--) {
                    sum+= nums[right] - nums[j];
                }
                right = i;
            }
        }

        return sum;
    }


    public static int way2( int[] nums ) {

        if( nums == null || nums.length <= 1 ) {
            return 0;
        }
        int[] lefts = new int[nums.length];
        int[] rights = new int[nums.length];
        lefts[0] = nums[0];
        rights[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            lefts[i] = Math.max( nums[i], lefts[i-1]);
        }
        for (int i = nums.length - 2; i >=0 ; i--) {
            rights[i] = Math.max( nums[i],rights[i+1]);
        }
        int result = 0;
        for (int i = 0; i < nums.length ; i++) {
            result+= ( Math.min( lefts[i],rights[i] ) - nums[i] );
        }
        return result;
    }

    public static int way3( int[] nums ) {

        int maxI = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if( nums[i] > max ) {
                maxI = i;
                max = nums[i];
            }
        }
        int result = 0;
        max = nums[0];
        for (int i = 0; i < maxI; i++) {
            max = Math.max( max,nums[i] );
            result += ( max - nums[i] );
        }
        max = nums[nums.length - 1];
        for (int i = nums.length - 1; i > maxI; i--) {
            max = Math.max( max,nums[i] );
            result += ( max - nums[i] );
        }
        return result;
    }

}
