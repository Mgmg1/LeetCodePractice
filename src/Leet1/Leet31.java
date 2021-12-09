package Leet1;

import java.util.Arrays;

/*
    下一个排列

    思路：一个可排列的元素集合，是由一系列 连续增大元素 和 连续减小元素 组成的 ！！！

    由十进制进位可想到：数字+1，第n位数字增大，则 0 ~ n-1 位 由最大 转为 最小

 */
public class Leet31 {

    public static void main(String[] args) {

        char[] sequence = "1235321".toCharArray();
        nextManagement( sequence );
        System.out.println(Arrays.toString( sequence ));
    }

    private static void nextManagement( char[] sequence ) {
        int i;
        for (i = sequence.length-1; i > 0 && sequence[i-1] >= sequence[i] ; i--) {}
        i--;
        if( i == 0 ) {
            //翻转sequence
            reverse(sequence,0,sequence.length-1);
        }else {
            //可以二分查找，这里不写
            //这里应该从小到大遍历，因为不一定会有 小于 sequence[i]!!!
            //假如找大于sequence[i]的最小元素，则从小遍历到大
            //反之从大遍历到小
            for (int j = sequence.length-1; j > i ; j--) {
                //必当存在
                if( sequence[j] > sequence[i] ){
                    char temp = sequence[i];
                    sequence[i] = sequence[j];
                    sequence[j] = temp;
                    reverse( sequence,i + 1,sequence.length-1 );
                    break;
                }
            }

        }

    }

    private static void reverse( char[] sequence,int start,int end) {
        int i = start,j = end;
        char temp = 0;
        while ( i < j ) {
            temp = sequence[i];
            sequence[i] = sequence[j];
            sequence[j] = temp;
            i++;
            j--;
        }
    }
}
