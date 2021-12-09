package Leet1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    n 皇后
    map 大小为 n * n
    以皇后为中心的九宫格不能有其它的皇后，且x，y轴方向上不能用皇后

    循环的大概思路：
        1.判断循环中的所有值是否满足某个要求 （这种需要设置flag为true，不符合时变成false，break退出判断）
            此时若只需要考虑边界内的数据，则直接加上 边界条件&&条件
            例子：九宫格区域扫描
        2.判断循环中是否存在某个值符合某个要求 （设置flag为null,符合则赋值 flag，break直接退出判断）
        3.需知道循环中所有符合要求的数据。此时需要一次完整的遍历

        4.对于遍历时主要越界便不予考虑的情况，只需要在取值前加上 判断越界语句即可

 */
public class Leet51ToDo {

    public static void main(String[] args) {

        LinkedList<char[][]> chars = new LinkedList<>();
        int n = 5;
        char[][] map = new char[n][n];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill( map[i],'.' );
        }
        nQueen(chars,map,0);
        for (char[][] aMap :
                chars) {
            for (char[] aaMap :
                    aMap) {
                System.out.println( Arrays.toString(aaMap ) );
            }
            System.out.println();
        }
    }

    public static void nQueen( List<char[][]> result,char[][] map,int n) {

        if( n == map.length ) {
            char[][] ele = new char[n][];
            for (int i = 0; i < n; i++) {
                ele[i] = Arrays.copyOf( map[i],n );
            }
            result.add( ele );
            return;
        }
        for (int i = 0; i < map[0].length; i++) {
            //九宫格的条件判断结构
            boolean flag = true;
            for (int j = i-1; j <= i+1 ; j++) {
                if( j >= 0 && j < map[0].length && n - 1 >= 0 && map[n-1][j] != '.'  ) {
                    flag = false;
                    break;
                 }
            }
            for (int j = 0; j < n; j++) {
                if( map[j][i] != '.' ) {
                    flag = false;
                    break;
                }
            }
            if( flag ) {
                map[n][i] = '@';
                nQueen(result, map, n + 1);
                map[n][i] = '.';
            }
        }

    }

    /*
        如何使用分支界限法？？？？
     */
}
