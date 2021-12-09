package Leet1;

/*
    合并两个有序数组
    思路：
        假如有新数组可以放入元素，则可以从0放置
        如在原有数组上放置且数组上已经有元素了，则要倒着放
 */
public class Leet88 {

    public static void main(String[] args) {

        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};
        int m = 3;
        int n = 3;
        combineArr( num1,m,num2,n );
        for (int i = 0; i < m + n; i++) {
            System.out.println( num1[i] );
        }
    }

    public static void combineArr( int[] num1,int n1,int[] num2,int n2 ) {

        int total = n1 + n2;
        int k = n1-1,j = n2-1;
        for (int i = total - 1; i >= 0; i--) {
            if( j < 0 || ( k >=0 && num1[k] > num2[j] ) ) {
                num1[i] = num1[k--];
            }else {
                num1[i] = num2[j--];
            }
        }
    }
}
