package Leet1;

import java.util.TreeSet;

/*
    存在重复元素
    给定一个整数数组，判断数组中是否有两个不同的索引 _i_ 和 _j_ ，
    使得 nums [i] 和 nums [j] 的差的绝对值最大为 _t_ ，
    并且 _i_ 和 _j_ 之间的差的绝对值最大为 _ķ_ 。
 */
public class Leet220 {

    public static void main(String[] args) {

        int[] nums = {1,5,9,1,5,9};
        int[] nums1 = {1,0,1,1};
        int k =1;
        int t =2;
        System.out.println(containsNearbyAlmostDuplicate(nums1 ,k,t));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums,int k,int t) {

        TreeSet<Integer> treeSet = new TreeSet<>();
        //得到树的下一个大于或等于目标值的值
        for (int i = 0; i < nums.length; i++) {
            Integer nextGreater = treeSet.ceiling(nums[i]);
            if( nextGreater !=null && nextGreater - nums[i] <= t ) {
                return  true;
            }
            Integer lastSmaller = treeSet.floor(nums[i]);
            if( lastSmaller !=null && nums[i] - lastSmaller <= t ) {
                return  true;
            }
            if( treeSet.size() == k ) {
                treeSet.remove( nums[i-k] );
            }
            treeSet.add(nums[i]);
        }
        return false;
    }

}
