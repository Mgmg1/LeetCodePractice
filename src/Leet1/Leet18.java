package Leet1;

import java.util.Arrays;
import java.util.LinkedList;

/*
    四数之和，不可重复

    参考三数之和
    在三数之和的基础上只需要增加一个循环 （不需要duplicate检测）
    假如有5数之和，也不需要duplicate!!!
    证明：

    四叔之和转为多个三数之和，因为有threeDuplicate，只有当三数之和为新的组合时，
    才有可能得到四数之和（不重复的三数之和 + 第四个数（可重复也可不重复）），每次形成不重复的四数之和

   再次，五数之和转为多个四数之和，而四数之和每次提供不同的组合。。。。。

 */
public class Leet18 {


    public static void main(String[] args) {


        int[][] ints = fourNumSum(new int[]{1, 0, -1, 0,0,0,0, -2, 2}, 0);
        for (int[] arr :
                ints) {
            System.out.println(Arrays.toString(arr));
        }

    }

    public static int[][] fourNumSum(int[] nums, int target) {

        Leet15.quickSort(nums, 0, nums.length - 1);
        System.out.println( Arrays.toString( nums )  );
        if (nums.length <= 3) return new int[0][];
        //循环相当于规定0 ~  i 的数组,
        LinkedList<int[]> result = new LinkedList<>();
//        int fourDuplicate = nums[2] == nums[1] ? ( nums[1] == nums[0] ? 2 : 1 ) : 0;
        for (int i = 3; i < nums.length; i++) {
//            fourDuplicate = nums[i] == nums[i-1] ? fourDuplicate+1 : 0;
            //要求不能有重复的，由三数之和来处理重复的数组部分
//            if( fourDuplicate == 0 ) {  不需要判 fourDuplicate，它
            //求四数之和前，需要构造不重复的三数之和。。。需要加一层循环

            //必须加。。。。否则会有重复的出现。
            while ( i + 1 < nums.length && nums[i] == nums[i+1] ) {
                i++;
            }
            if( i >= nums.length ) break;

            for (int j = 2; j < i; j++) {
                //改良
                while ( j + 1 < i && nums[j] == nums[j+1] ) {
                    j++;
                }
                if(  j >= i ) break;
                int k = 0, l = j - 1;


                //数字重复，因此不是 k <= l
                while (k < l) {
                    //转换为两数之和
                    if (nums[k] + nums[l] + nums[j] == target - nums[i]) {
                        result.add(new int[]{nums[k], nums[i], nums[j], nums[l]});
                        while (k + 1 < nums.length && nums[k] == nums[k + 1]) {
                            k++;
                        }
                        k++;
                        while (l - 1 >= 0 && nums[l] == nums[l - 1]) {
                            l--;
                        }
                        l--;
                    } else if (nums[k] + nums[l] + nums[j] < target - nums[i]) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }

        }
        return result.toArray(new int[result.size()][]);
    }


}
