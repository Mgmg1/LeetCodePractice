package Leet1;

import java.util.Arrays;
import java.util.LinkedList;

/*
    插入区间
 */
public class Leet57 {

    public static void main(String[] args) {
        int [][] partition1= new int[][]{
                new int[]{1,3},
                new int[]{6,9},
        };
        int [][] partition= new int[][]{
                new int[]{1,2},
                new int[]{3,5},
                new int[]{6,7},
                new int[]{8,10},
                new int[]{12,16}
        };
//        mergeSort(partition,0,partition.length-1,new int[partition.length][]);
        Object[] objects = insertPartition(partition, new int[]{
                4,8
        });
        for (Object ints :
                objects) {
            System.out.println(Arrays.toString((int[]) ints));
        }
    }


    public static Object[] insertPartition(int [][] partitions,int [] inserted){
        if( partitions.length == 0 ){
            return new int[][]{
                    inserted
            };
        }
        partitions = Arrays.copyOf(partitions, partitions.length+1);
        partitions[partitions.length-1] = inserted;
        mergeSort(partitions,0,partitions.length-1,new int[partitions.length][]);
        LinkedList<int[]> results = new LinkedList<>();
        results.add(Arrays.copyOf(partitions[0],partitions[0].length));
        for (int i = 1; i < partitions.length; i++) {
            int[] last = results.getLast();
            if( partitions[i][0] <= last[1] ) {
                last[1] = Math.max(last[1],partitions[i][1]);
            }else {
                results.add(Arrays.copyOf(partitions[i],partitions[i].length));
            }
        }
        return  results.toArray();
    }

    //合并排序,发现一行两半有序，无法原地直接转为一行有序，若不借助额外空间，则需要进行折半插入排序
    public static void mergeSort( int[][] partition,int left,int right,int [][] newPartition){
        if( right <= left ){
            return;
        }
        mergeSort(partition,left,(left+right)/2,newPartition);
        mergeSort(partition,(left+right)/2 +1 ,right,newPartition);
        int leftEnd = (left+right)/2;
        int k = left, j = leftEnd + 1;
        for (int i = left; i <= right; i++) {
            //重点
            if( j > right ||( k <= leftEnd && partition[k][0] < partition[j][0] ) ){
                newPartition[i] = partition[k++];
            }else {
                newPartition[i] = partition[j++];
            }
        }
        for (int i = left; i <= right; i++) {
            partition[i] =  newPartition[i];
        }
    }


}
