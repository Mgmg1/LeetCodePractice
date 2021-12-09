package Leet1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    二叉树层次遍历
 */
public class Leet102 {

    public static void main(String[] args) {

        //给定的是一个满二叉树
        Integer[] tree = { 3,9,29,null,null,15,7};
//        for (Integer[] level :
//                levelTravel( tree )) {
//            System.out.println(Arrays.toString( level ));
//        }
        for (int[] level :
                levelTravel2( tree )) {
            System.out.println(Arrays.toString( level ));
        }

    }

    /*
        只适合 满二叉树的情况。。。。有小问题
     */
    public static Integer[][] levelTravel( Integer[] tree ) {

        int length = tree.length + 1 ;
        int height = 0;
        while ( length != 1 ) {
            length>>=1;
            height++;
        }

        Integer[][] result = new Integer[height][];

        int i = 0,addition = 1;
        int iHeight = 0;
        while ( i < tree.length ) {
            int num = 0;
            int j = i;
            while ( j < i + addition ) {
                if( tree[j++] != null ) {
                    num++;
                }
            }
            int index = 0;
            result[iHeight] = new Integer[num];
            while ( i < j ) {
                if( tree[i] != null ) {
                    result[iHeight][index++] = tree[i];
                }
                i++;
            }
            addition<<=1;
            iHeight++;
        }

        return result;

    }

    /*
        因为所给的
     */
    public static List<int[]> levelTravel2(Integer[] tree ) {

        if( tree == null || tree.length == 0 ) return new LinkedList<>();

        //构件层次遍历链表
        LinkedList<Integer> queue = new LinkedList<>();
        int levelCount = 1,currentCount = 0;
        int nextLevelCount = 0;
        queue.addLast( 0 );
        LinkedList<int[]> result = new LinkedList<>();
        int[] levelArr = new int[1];
        while ( !queue.isEmpty()  ) {
            Integer integer = queue.removeFirst();
            if( integer*2 + 1 < tree.length && tree[integer*2 + 1 ] != null ) {
                nextLevelCount++;
                queue.addLast( integer*2 + 1 );
            }
            if(  integer*2 + 2 < tree.length && tree[integer*2 + 2 ] != null ) {
                nextLevelCount++;
                queue.addLast( integer*2 + 2 );
            }
            levelArr[currentCount++] = tree[integer];
            if( currentCount == levelCount ) {
                result.addLast( levelArr );
                levelCount = nextLevelCount;
                levelArr = new int[levelCount];
                currentCount = 0;
                nextLevelCount = 0;
            }
        }
        return result;
    }
}
