package Leet1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
    被围绕的区域
    似乎深度优先遍历和广度优先遍历都能使用
 */
public class Leet130 {

    public static void main(String[] args) {
        String[] strMap = {
                "XXXO",
                "XOOX",
                "XXOX",
                "X0XX",
        };
        char[][] map = new char[4][];
        for (int i = 0; i < map.length; i++) {
            map[i] = strMap[i].toCharArray();
        }
        way1( map );
        for (char[] chars :
                map) {
            System.out.println(  Arrays.toString( chars ));
        }
    }

    public static void way1( char[][] map ) {
        //上下左右
        int[][] direc = new int[4][2];
        //上 i j
        direc[0][0] = -1;direc[0][1] = 0;
        //右
        direc[1][0] = 0;direc[1][1] = 1;
        //下
        direc[2][0] = 1;direc[2][1] = 0;
        //左
        direc[3][0] = 0;direc[3][1] = -1;

        LinkedList<Integer> queueI = new LinkedList<>();
        LinkedList<Integer> queueJ = new LinkedList<>();
        for (int j = 0; j < map[0].length; j++) {
            way1Child( map,direc,queueI,queueJ,0,j );
            way1Child( map,direc,queueI,queueJ,map.length-1,j );
        }
        for (int i = 0; i < map.length; i++) {
            way1Child( map,direc,queueI,queueJ,i,0 );
            way1Child( map,direc,queueI,queueJ,i,map[0].length-1);
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if( map[i][j] == 'O' ) {
                    map[i][j] = 'X';
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if( map[i][j] == '+' ) {
                    map[i][j] = 'O';
                }
            }
        }

    }

    public static void way1Child(char[][] map,int[][] direc,
                                 LinkedList<Integer> queueI,
                                 LinkedList<Integer> queueJ,
                                 int i,int j) {
        if( map[i][j] == 'O' ) {
            queueI.addLast( i );
            queueJ.addLast( j );
            while ( !queueI.isEmpty() ) {
                Integer mI = queueI.removeFirst();
                Integer mJ = queueJ.removeFirst();
                map[mI][mJ] = '+';
                int newI = mI,newJ = mJ;
                for (int k = 0; k < direc.length; k++) {
                    newI = mI + direc[k][0];
                    newJ = mJ + direc[k][1];
                    if( newI <0 || newI >= map[0].length || newJ < 0 || newJ >= map.length ) {
                        continue;
                    }
                    if( map[newI][newJ] == 'O' ) {
                        queueI.addLast( newI );
                        queueJ.addLast( newJ );
                    }
                }
            }
        }


    }

}
