package Leet1;

/*
    搜索二维矩阵II
    m*n矩阵
    1。直接地，可以对每一行进行二分查找，但是没能够利用起纵行的条件，
    因此直觉上，认为两个升序条件都要使用
 */
public class Leet240 {

    public static void main(String[] args) {
        System.out.println(findTarget( new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30},
        },26 ));

    }

    public static boolean findTarget( int[][] matrix ,int target ) {

        int x0 = 0,y0=0;
        int x1 = matrix.length-1,y1 = matrix[0].length-1;
        int i = 0,j = x1 ;
        int middle = 0 ;
        //  二分查找结束后，不管是否查找得到，i就是target的位置(查找成功或者查找失败后插入对应的位置)
        while ( x0 <= x1 && y0 <= y1 ) {
            // 两个循环分别对应  y1 x0 ,选择的原因:当矩阵不可再缩小范围时，恰好说明target不在矩阵中
            while (  i <= j ) {
                middle = ( i + j ) / 2 ;
                if( matrix[middle][y1] == target ) {
                    return true;
                }else if( matrix[middle][y1] < target ) {
                    i = middle + 1;
                }else {
                    j = middle - 1;
                }
            }
            if( i < x0 || i > x1 ) {
                return false;
            }
            x0 = i;
            i = y0; j = y1;
            while (  i <= j ) {
                middle = ( i + j ) / 2 ;
                if( matrix[x0][middle] == target ) {
                    return true;
                }else if( matrix[x0][middle] < target ) {
                    i = middle + 1;
                }else {
                    j = middle - 1;
                }
            }
            if( i < y0 || j > y1 ) {
                return false;
            }
            y1 = i - 1;
            i = x0;j = x1;
        }
        return false;
    }

}
