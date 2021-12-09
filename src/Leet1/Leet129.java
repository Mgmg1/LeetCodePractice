package Leet1;

/*
    求根到叶子节点数字之和

    等到叶子节点再处理累加值
    可以使用递归完成，
    也可以使用队列（需要记下下个节点的累加和）

 */
public class Leet129 {


    public static void main(String[] args) {

        int[] arr = { 4,9,0,5,1 };
//        int[] arr = { 1,2,3 };
//        recursiveSum(arr,0,0);
        wSum(arr);
        System.out.println( pathSum );

    }


    private static int pathSum = 0;
    /*
        关于递归，一般来说，如果不访问null节点，那么要设置两个if来确实 左节点 或者 右节点 为 null
     */
    public static void recursiveSum( int[]arr,int i,int sum) {

        if( arr == null || arr.length == 0) return;
        /*
            说明是叶子节点
         */
        if( 2*i + 1 >= arr.length && 2*i + 2 >= arr.length ) {
            pathSum+=( sum + arr[i]  );
            return;
        }

        if( 2*i + 1 < arr.length  ) {
            recursiveSum( arr, 2*i + 1, ( sum + arr[i] ) * 10);
            //里面不能有return 语句
        }
        if( 2*i + 2 < arr.length ) {
            recursiveSum( arr, 2*i + 2, ( sum + arr[i] ) * 10);
        }
    }


    /*
        简陋版广度优先遍历
     */

    public static void wSum(int[] arr) {

        if( arr == null || arr.length == 0 ) return;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = ( arr[(i-1)/2] * 10 ) + arr[i];
            if( 2*i + 1 >= arr.length && 2*i + 2 >= arr.length ) pathSum+=arr[i];
        }
    }
}
