package Leet1;

import java.util.*;

/*
    三数之和，需要结合两数之和
    使用两数之和来排查问题时要追加循环来排除相等的元素

    扩展为 三个数字之和为 x ，而不是 固定的0 !!! 只需要引入一个 target 值， 也可以以此来构造出  四数之和为 0
 */
public class Leet15 {


    public static void main(String[] args) {

        int[] nums = {2,12,3,2,1,5,6,7,-3,-2,-1,3,0,0,1,0,-4,-5,9};
//        int nums[] = { 0,0,0,0,0 };
        //快排
//        quickSort(nums,0,nums.length-1);
        combineSort( nums,new int[nums.length],0,nums.length - 1 );
        System.out.println( Arrays.toString( nums ) );
        int[][] ints = threeNumSum(nums,2);
        for (int[] arr :
                ints) {
            System.out.println( Arrays.toString( arr ) );
        }
    }


    public static int[][] threeNumSum( int[] nums,int target) {

        LinkedList<int[]> ints = new LinkedList<>();

        if( nums.length < 3 ) return new int[0][];
        int duplicate = 0;
        duplicate =  nums[1] == nums[0] ? 1 : 0;
        for (int i = 2; i < nums.length; i++) {
            //计算 num[i]后的重复个数
//            for (int l = i-1; l>= 0 && nums[l] == nums[i] ; l--) {
//                duplicate++;
//            }
            //判重优化 需要考虑到 nums[0] 和 nums[1] 是否重复。。。。这是容易忽略的地方。
            //可以用数学归纳法 证明，它的值和上方循环求得的值等价  duplicate初值 只需要考虑  0 和 1即可
            duplicate = nums[i] == nums[i-1] ? duplicate+1 : 0;
            if( duplicate == 1 ) {
                for (int l = 0; l < i-1; l++) {
                    if( nums[i]*2 + nums[l] == target ) {
                        ints.addLast( new int[]{nums[l],nums[i],nums[i]} );
                        while ( l + 1 < nums.length && nums[l] == nums[l+1] ) {
                            l++;
                        }
                    }
                }
                //是 duplicate == 2 而不是 duplicate >= 2 ,否则会出现 [0,0,0] 重复
            }else if( duplicate == 2 && nums[i] * 3 == target  ) {
                //也就是说 nums[i]和nums[i-1]和nums[i-2]  等于0？？？
                ints.addLast( new int[]{nums[i],nums[i],nums[i]} );
            }else {
                //!!!          k防止纳入重复的 nums[i]
                int j = 0,k = i - duplicate - 1;
                while (  j < k  ) {
                    if( nums[j] + nums[k] + nums[i] == target ) {
                        ints.addLast(new int[]{nums[j],nums[k],nums[i]});
                        while ( j + 1 < nums.length && nums[j] == nums[j+1] ) {
                            j++;
                        }
                        j++;
                        while ( k - 1 >= 0 && nums[k] == nums[k-1] ) {
                            k--;
                        }
                        k--;
                    }else if( nums[j] + nums[k] < -nums[i] ){
                        j++;
                    }else {
                        k--;
                    }
                }
            }
        }
       return ints.toArray(new int[ints.size()][]);
    }

    public static void quickSort( int[] nums,int start,int end) {

        if( start >= end ) {
//        if( start == end ) {  错误，可能会出现 start>end,导致空指针
            return;
        }
        if( start == end + 1 ) {
//            nums[start] = nums[start] <= nums[end] ? nums[start] : nums[end];
//            nums[end] = nums[start] > nums[end] ? nums[start] : nums[end];
            nums[start] = Math.min( nums[start],nums[end] );
            nums[end] = Math.max( nums[start],nums[end] );
            return;
        }
        int middle = start,j = end;
        //快排  index > j 的元素全都是大于nums[middle]的元素,nums[j]不确定
        while ( middle < j ) {
            if( nums[middle + 1] <= nums[middle] ) {
                int temp = nums[middle+1];
                nums[middle+1] = nums[middle];
                nums[middle] = temp;
//                nums[middle + 1] ^= nums[middle];
//                nums[middle] ^= nums[middle + 1];
//                nums[middle + 1] ^= nums[middle];
//                一种不用中间变量的交换值的方法
                middle++;
            }else {
                int temp = nums[middle+1];
                nums[middle+1] = nums[j];
                nums[j--] = temp;
            }
        }
        quickSort(nums,start,middle-1);
        quickSort(nums,middle+1,end);
    }
    /*
        归并排序
        注意临时交换区的下标不要从0开始，否则会导致 temp[0]被反复覆盖,无法避免。造成错误!!!
        由于递归定义为执行两段递归，temp中已有两段有序元素，因此当start==end时，需要为temp数组赋值

        改进：用一个true来标志当前nums的参数位置，调用时输入为true，当到达递归出口时，
        若为false，不需要进行为为temp赋值，当为true时，需要为temp赋值

     */
    private static void combineSort( int[] nums,int[] temp,int start,int end) {

        if( start>end ) return;
        if( start == end ) {
            //这是错误的，会导致temp[0] 被覆盖两次  combineSort( nums,temp,0,0) 和 combineSort( nums,temp,1,1) ,不可避免!!
            //temp[0] = nums[start];
            temp[start] = nums[start];
            return;
        }
//        if( start >= end ) return;
        int middle = ( start + end )/2;
        combineSort( nums,temp,start,middle);
        combineSort( nums,temp,middle + 1,end );
        int i = start,j = middle+1;
        int k = start;
        while ( i <= middle || j <= end ) {
            //两个数组合一的一般思路（不同时到达最大长度，判断时，注意下标是否越界），需记住
//            if( ( j > end  && i <= middle ) ||  temp[i] < temp[j] ) {  错误
            if( i<=middle && ( j > end || temp[i] < temp[j] ) ) {
                nums[k++] = temp[i++];
            }else {
                nums[k++] = temp[j++];
            }
        }
        k = start;
        for (int l = start; l < end + 1; l++) {
            temp[l] = nums[k++];
        }
    }
}
