package Leet1;

import java.util.Arrays;
import java.util.Stack;

/*
    解数独

 */
public class Leet37 {

    public static void main(String[] args) {

        char[][] map = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {
                        '6', '.', '.', '1', '9', '5', '.', '.', '.'
                },
                {
                        '.', '9', '8', '.', '.', '.', '.', '6', '.'
                },
                {
                        '8', '.', '.', '.', '6', '.', '.', '.', '3'
                },
                {
                        '4', '.', '.', '8', '.', '3', '.', '.', '1'
                },
                {
                        '7', '.', '.', '.', '2', '.', '.', '.', '6'
                },
                {
                        '.', '6', '.', '.', '.', '.', '2', '8', '.'
                },
                {
                        '.', '.', '.', '4', '1', '9', '.', '.', '5'
                },
                {
                        '.', '.', '.', '.', '8', '.', '.', '7', '9'
                }
        };
        solveShuDu(map);
        for (char[] column:
             map) {
            System.out.println( Arrays.toString( column ) );
        }
    }

    /*
        递归
        回溯法
        避免每次递归都申请数组，使用一个栈来记录状态
     */
    public static void solveShuDu( char[][] map ) {
        System.out.println(solveShuDu(map,new Stack<Integer>(),new boolean[10],0,0)  );

    }

    public static boolean solveShuDu(char[][] map,
                                     Stack<Integer> stack,
                                     boolean[] flags,
                                     int i,
                                     int j) {

        /*
            原题答案直接从起点开始检测 '.'    并且直接循环 '1' ~ '9' 带入检测数独是否有效
         */
        for (int k = i; k < map.length; k++) {
//       1     int l = j;
//            //这里卡了很久，因为循环的起点发生错误。即换行时 l 本该是 0，但是 被赋值为 0
//            //这一步很重要！！！否则循环会出现错误，不能过渡到下一行的起点
//            if( k != i ) {
//                l = 0;
//            }
            //2.。。。或者直接让 l 起点为 0
            for (int l = 0; l < map[0].length; l++) {
                if( map[k][l] == '.' ) {
                    Arrays.fill( flags, false);
                    int validSize = isValid(map, k, l, stack, flags);
                    for (int m = 0; m < validSize; m++) {
                        map[k][l] = ( char ) ( stack.pop() + '0' );
                        //也可以 直接 使用参数  k  和  l 。然后改第二次循环的期待你为 0
//   1                     boolean b = solveShuDu(map, stack, flags, k + (l + 1) / map[0].length,( l + 1 )% map[0].length);
                        boolean b = solveShuDu(map, stack, flags, k , l + 1 );
                        if( b ) {
                            return true;
                        }
                    }
                    map[k][l] = '.';
                    return false;
                }
            }
        }
        return true;
    }

    /*
        返回符合条件的数字个数
     */
    private static int isValid(char[][] map,int i ,int j,Stack<Integer> stack,boolean[] flags) {

        for (int k = 0; k < map[j].length; k++) {
            if( map[i][k] != '.' ) {
                flags[ map[i][k] - '0' ] = true;
            }
        }
        for (int k = 0; k < map.length; k++) {
            if( map[k][j] != '.' ) {
                flags[ map[k][j] - '0' ] = true;
            }
        }
        for (int k = i - i%3 ; k < i - i%3 + 3; k++) {
            for (int l = j - j%3 ; l < j - j%3 + 3; l++) {
                if( map[k][l] != '.' ) {
                    flags[ map[k][l] - '0' ] = true;
                }
            }
        }
        int size = 0;
        for (int k = 1; k < flags.length; k++) {
            if( !flags[k] ) {
                stack.push( k );
                size++;
            }
        }
        return size;
    }


}
