package Leet1;

/*
    删除排序数组中的重复项II
    要求：重复项不能超过两个，超过则删除
    数组本身是有序的

    因为给的一个有序数组，所以很简单

 */
public class Leet80 {

    public static void main(String[] args) {

        int[] arr = {0,0,1,1,1,1,2,3,3};
        int length = removeDuplicateItemsII(arr);
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            System.out.println( arr[i] );
        }

    }

    /*
        一次遍历，边遍历边判断，留有一个最新的指针用于指示
        下一个元素的存放位置
     */
    public static int removeDuplicateItemsII( int[] arr ) {

        if( arr == null || arr.length == 0 ) {
            return 0;
        }
        int i = 0;
        int count = 1;
        for (int j = 1; j < arr.length; j++) {
            //统计连续的重复项个数
            if( arr[j] == arr[j-1] ) {
                count++;
            }else {
                //说明前后不相等，arr[j] 是遍历到的新元素
                count = 1;
            }
            //当重复项不超过2时，
            // 移动位置，并更新指针
            if( count <= 2 ) {
                arr[i++] = arr[j];
            }
        }
//        return i + 1;  i 已经代表了长度了，不需要 + 1
        return i;
    }
}
