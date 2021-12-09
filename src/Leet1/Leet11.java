package Leet1;

/*
    盛最多水的容器
 */
public class Leet11 {

    public static void main(String[] args) {

        System.out.println( containMostWater(new int[]{1,8,6,2,5,4,8,3,7}) );

    }

    public static int containMostWater( int[] lines ) {

        if( lines == null || lines.length <= 1 ) return 0;

        int i = 0,j = lines.length -1;
        int max = 0;
        while ( i < j ) {
            // 两根柱子，不占空间，，每两个柱子间隔1
            max = Math.max( max,( j - i ) * Math.min( lines[i],lines[j] ) );
            if( lines[i] < lines[j] ) {
                i++;
            }else {
                j--;
            }
        }
        return max;
    }


}
