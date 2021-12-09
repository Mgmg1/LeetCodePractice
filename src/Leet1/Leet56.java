package Leet1;


import java.util.Arrays;

/*
    合并区间
    先排序，O(n)遍历一次
 */
public class Leet56 {

    public static void main(String[] args) {
        int [][] partition= new int[][]{
                new int[]{1,3},
                new int[]{2,6},
                new int[]{8,10},
                new int[]{15,18}
        };
        int [][] partition2= new int[][]{
                new int[]{1,4},
                new int[]{4,5},
        };
        int[][] ints = mergePartition(partition2);
        for (int[] arr :
                ints) {
            System.out.println(Arrays.toString(arr));
        }
    }


    public static int[][] mergePartition( int[][] partition ){
        //题目没有说明partition已经在partition[n][0]上有序，因此先进行排序
        if (partition.length <= 1){
            return partition;
        }
        quickSort(partition,0,partition.length-1);
        int end = 0;
        for (int i = end + 1; i < partition.length ; i++) {
            if( partition[end][1] >= partition[i][0] ){
                partition[end][1] = Math.max(partition[end][1],partition[i][1]);
            }else {
                end++;
                partition[end] = partition[i];
            }
        }
        return Arrays.copyOfRange(partition,0,end + 1);
    }

    //和一般的快速不太一样的是——这里的中枢在数组之外，螺丝和螺母问题也是这么解决的
    public static void quickSort(int[][] partition,int left,int right){
        if( right < left ) {
            return;
        }
        int middle = partition[left][0];
        int min=left,max = right;
        int[] temp;
        while( min < max ){
            temp = partition[min+1];
            if( partition[min+1][0] <= middle ){
                partition[min+1] = partition[min];
                partition[min] = temp;
                min++;
            }else {
                partition[min+1] = partition[max];
                partition[max] = temp;
                max--;
            }
        }
        quickSort(partition,left,min-1);
        quickSort(partition,min +1,right);
    }
}
