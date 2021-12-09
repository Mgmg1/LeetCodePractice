package Leet1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    电话号码的字母组合
    利用 循环 和 递归都可以，使用集合类会比较方便
    假如不使用集合类，则必须先算出需要的数组的大小。由乘法定理算出
    开始前，需要位
 */
public class Leet17 {

    public static void main(String[] args) {

        /*
        乘法定理
            2:abc
            3:def
            4:ghi
            5:jkl
            6:mno
            7:pqrs
            8:tuv
            9:wxyz
         */
        System.out.println(Arrays.toString( recurPhoneNumCombination( new int[]{2,3,} ) ));
        System.out.println(Arrays.toString( phoneNumCombination( new int[]{2,3,} ) ));


    }

    /*
        递归版

     */
    public static String[] recurPhoneNumCombination(int[] nums) {

        char[][] map = {
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        List<String> strings = new ArrayList<>();
        strings.add("");
        List<String> strings1 = phoneNumCombination(nums, 0, strings, map);
        return strings1.toArray(new String[strings1.size()]);
    }

    public static List<String> phoneNumCombination(int[] nums,
                                                   int index,
                                                   List<String> result,
                                                   char[][] map) {
        List<String> newResult = new ArrayList<>();
        if( index < nums.length && nums[index] != 1 ) {
            for (char c:
                 map[nums[index]]) {
                for (String str :
                        result) {
                    newResult.add(str+""+c);
                }
            }
            result.clear();
            return phoneNumCombination(nums,index+1,newResult,map);
        }else {
            return result;
        }

    }

    /*
        循环
     */
    public static String[] phoneNumCombination(int[] nums ) {
        List<String> result = new ArrayList<>();
        result.add("");
        char[][] map = {
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        for (int i = 0; i < nums.length; i++) {
            if( nums[i] != 1 ) {
                List<String> strings = new ArrayList<>();
                for (char c :
                        //nums[i] 数字
                        // map[nums[i]] 是nums[i] 数字对应的 char数组，也就是对应的多个字母
                        map[nums[i]] ) {
                    for (String str :
                            result) {
                        strings.add(str + "" +c );
                    }
                }
                result.clear();
                result = strings;
            }
        }
        return result.toArray(new String[result.size()]);
//        return result.toArray(new String[0]);  也可，会自动扩大数组大小
    }
}
