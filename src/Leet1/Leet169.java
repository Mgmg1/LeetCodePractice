package Leet1;


import java.util.HashMap;

/*
    求众数

 */
public class Leet169 {

    public static void main(String[] args) {
        int[] input = {
                2, 2, 1, 1, 1, 2, 2, 2, 2, 3, 4, 2, 6, 7
        };
        System.out.println(maxTimesNumber( input ));
        System.out.println(maxTimesNumber2( input ));
        System.out.println(maxTimesNumber3( input,0,input.length - 1));
        System.out.println( maxTimesNumber4( input ) );
    }

    //常规方法,统计出现次数最多的元素，判断 出现个数是否大于n/2(向下取整) ，用到了Map,但不是最佳解法
    public static int maxTimesNumber(int[] nums ) {

        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 1;
        int maxNum = nums[0];
        for (int num :
                nums) {
            if ( map.containsKey( num ) ) {
                map.put(num,map.get(num) +  1);
            }else {
                map.put(num,1);
            }
            if( map.get(num) > max ) {
                max = map.get(num);
                maxNum = num;
            }
        }
        return maxNum;
    }

    /**
     * 摩尔投票法,最佳解，正确性由 异国士兵极限一换一的例子 来证明
     * 异国士兵极限一换一：
     *    数组中不重复的数字总共有m个，可以假设有m个国家
     *    每个重复的数字个数设置为k，那么当遇到两个数字不同时，就可以都消去，最后留下的肯定是重复超过二分之一的数字
     * @param nums 输入数组
     * @return
     */
    public static int maxTimesNumber2( int[] nums ) {

        int times = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if( times == 0 ) {
                num = nums[i];
                times++;
                continue;
            }
            if( num != nums[i] ) {
                times--;
            }else {
                times++;
            }
        }

        //如果此时 times 等于 0 ，那么说明 nums 中不存在 个数大于 n/2(向下取整) 的值,此处默认绝对存在
        return num;
    }

    /**
     * 分治法 nlogn 的事件复杂度,发现该方法只在 存在 个数大于n/2(向下取整) 的元素 时有效。
     * @param nums
     * @param start
     * @param end
     * @return 返回值为数组中重复数字最多的数字
     */
    public static int maxTimesNumber3( int[] nums ,int start,int end) {

        if( start == end ) {
            return nums[start];
        }

        int middle = ( start + end ) /2;
        int lMax = maxTimesNumber3(nums, start, middle);
        int rMax = maxTimesNumber3(nums, middle + 1, end);
        if( lMax == rMax ) {
            return lMax;
        }
        int lTimes = 0,rTimes = 0;
        for (int i = start; i <= end ; i++) {
            if( nums[start] == lMax ) {
                lTimes++;
            }
            if( nums[start] == rMax ) {
                rTimes++;
            }
        }
        return lTimes > rTimes ? lMax : rMax;
    }

    // 还可以使用 排序的方法！！然后取 num[num.length/2]

    //还可以使用 计算32位bit的方法，事件复杂度位O(n) 如果 存在 k 个数 > n.length/2 那么它的每一位二进制个数也必然 > n.length/2
    //当过没有过半的时，说明过半的数字为0

    /**
     * 另外一种方法，由剑指offer得到 : 通过时间复杂度为O(N)的求中间位置的快速排序中的parttion算法变形
     * @param values
     * @return
     */
    public static int maxTimesNumber4( int[] values ) {
        int start = 0;
        int end = values.length - 1;
        while ( true ) {
            int pivot = partition(values, start, end);
            if( pivot == values.length/2 ) {
                return values[pivot];
            }else if( pivot < values.length/2 ) {
                start = pivot + 1;
            }else {
                end = pivot - 1;
            }
        }
    }

    /**
     *
     * @param values
     * @param start
     * @param end
     * @return 返回 partition后的中枢下标位置，当start > end 时，返回 - 1
     */
    private static int partition( int[] values,int start,int end ) {

        if( start > end ) { return -1; }
        if( start == end ) { return start; }

        int pivot = start + (int)(( end - start ) * Math.random());
        //交换
        int temp = values[pivot];
        values[pivot] = values[start];
        values[start] = temp;

        int begin = start + 1,last = end;
        while ( begin <= last ) {
            while ( begin <= last && values[begin] <= values[start] ) { begin++; }
            while ( begin <= last && values[last] > values[start] ) { last--; }
            if( begin <= last ) {
                temp = values[begin];
                values[begin] = values[last];
                values[last] = temp;
            }
        }
        //此时start + 1 ~ begin - 1 的元素都满足 val <= values[start]
        temp = values[start];
        values[start] = values[begin - 1];
        values[begin-1] = temp;

        return begin - 1;
    }
}
