package Leet1;

import java.util.Arrays;

/*
    旋转图像
    顺时针90 为 左对角线 翻转，再左右翻转
    逆时针90 为 左对角线 翻转，再上下翻转
    左右翻转和上下翻转，对称元素下标分别为    [i][image[0].length - j - 1] ， [image.length - i - 1][j]

 */
public class Leet48 {

    public static void main(String[] args) {
        int[][] image = new int[6][6];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j] = i * image[0].length + j + 1;
            }
        }
        clockwiseRotate( image );
        for (int[] row:
             image) {
            System.out.println(Arrays.toString( row ));
        }
    }

    /*
       顺时针旋转90°
       ......//逆时针则第二个循环为上下翻转
     */
    public static void clockwiseRotate( int[][] image ) {

        int temp = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < i; j++) {
                temp = image[i][j];
                image[i][j] = image[j][i];
                image[j][i] = temp;
            }
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length / 2; j++) {
                temp = image[i][j];
                image[i][j] = image[i][image[0].length - j - 1];
                image[i][image[0].length - j - 1] = temp;
            }
        }
    }

}
