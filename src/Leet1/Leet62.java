package Leet1;

/*
    不同路径
    从左上角到右下角，每次只能向右或向下走
 */
public class Leet62 {

    public static void main(String[] args) {
        System.out.println(uniquePath1(3,2));
        System.out.println(uniquePath1(7,3));
        System.out.println(uniquePath2(3,2));
        System.out.println(uniquePath2(7,3));
    }

    //递归版,可转动态规划
    public static int uniquePath1(int m,int n){
        if( m==1&&n==1 ){
            return 1;
        }
        int result = 0;
        if( m!=1 ){
            result += uniquePath1(m-1,n) ;
        }
        if( n!=1 ){
            result += uniquePath1(m,n-1) ;
        }
        return result;
    }

    //动态规划
    public static int uniquePath2(int m,int n){
        int[][] dpArr = new int[n][m];
        for (int i = 0; i < m; i++) {
            dpArr[n-1][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            dpArr[i][m-1] = 1;
        }
        for (int i = n-2; i >= 0 ; i--) {
            for (int j = m-2 ; j >= 0; j--) {
                dpArr[i][j] = dpArr[i+1][j] + dpArr[i][j+1];
            }
        }
        return dpArr[0][0];
    }

}
