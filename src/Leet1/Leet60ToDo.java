package Leet1;

import java.util.LinkedList;

/*
    第k个排列
    数组flag对应关系挺麻烦的
    思路：
        递归版：计算n的阶乘，最后一个排列
            从最高位到最低位，计算出第i位的排列范围，
            假如小于最低的范围，则退到下一位，假如大于，则寻找到不大于k的最大范围对应的位置，用k减去它，n = n + 1
            flag数组，标记高位部分已经被使用的数字，这样就能得到当下位能够使用的所有位置

        递归版 =》循环： 将 n > 0 作为 外层循环条件，

        新思路：升序排列是第1个排列。找到第i! （i是 i!大于等于k 的最小值） 个排列，这时有 第i~1位，降序。
              令 k = k - j * (i-1)!  (此时 k - (j + 1) * (i-1)! < 0  ) ， i = i - 1。
              外层循环条件为 k > 0

 */
public class Leet60ToDo {

    public static void main(String[] args) {
        int n = 4;
        int k = 9;
        //flag数组大小不止要根据n而定！！
        int result1 = rKthArrangement(n, k, new boolean[10], new LinkedList<Integer>());
        int result2 = kthArrangement(n, k);
        System.out.println(result1);
        System.out.println(result2);
    }

    //递归版
    // flag 标记当前可用的数字， flag[] 为 false 说明可用，否则为不可用
    public static int rKthArrangement(int n, int k, boolean[] flag, LinkedList<Integer> result) {
        if (n == 0) {
            StringBuilder sb = new StringBuilder();
            result.forEach(sb::append);
            return Integer.parseInt(sb.toString());
        } else {
            int f = factorial(n - 1);
            for (int i = n - 1; i >= 0; i--) {
                if (k >= f * i + 1) {
                    //找到可用的第i+1小
                    int count = 0;
                    for (int j = 0; j < flag.length; j++) {
                        if (!flag[j] && count == i) {
                            flag[j] = true;
                            result.addLast(j + 1);
                            return rKthArrangement(n - 1, k - count * f, flag, result);
                        } else if (!flag[j]) {
                            count++;
                        }
                    }
                }
            }
        }
        return 0;
    }

    //非递归
    public static int kthArrangement(int n, int k) {
        int f = factorial(n);
        LinkedList<Integer> result = new LinkedList<>();
        boolean[] flag = new boolean[10];
        while (n > 0) {
            f /= n;
            for (int i = n - 1; i >= 0; i--) {
                if (k >= i * f + 1) {
                    int count = 0;
                    for (int j = 0; j < flag.length; j++) {
                        if (!flag[j] && count == i) {
                            flag[j] = true;
                            result.add(j + 1);
                            break;
                        } else if (!flag[j]) {
                            count++;
                        }
                    }
                    k-=count*f;
                    break;
                }
            }
            n--;
        }
        StringBuilder sb = new StringBuilder();
        result.forEach(sb::append);
        return Integer.parseInt(sb.toString());
    }


    public static int factorial(int n){
        int sum = 1;
        for (int i = 1; i<= n;i++){
            sum*=i;
        }
        return sum;
    }

}
