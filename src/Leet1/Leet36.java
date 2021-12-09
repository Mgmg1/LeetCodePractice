package Leet1;

import java.util.Arrays;

/*
    有效的数独
 */
public class Leet36 {

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
        System.out.println( isValidSudoku(map) );
    }

    /*
        比较笨的做法!!!
        遍历了三次 map
     */
    public static boolean isValidSudoku(char[][] map) {

        // 0 ~ 9
        boolean[] record = new boolean[10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '.') {
                    if (record[map[i][j] - '0']) {
                        //已经存在了
                        return false;
                    }
                    record[map[i][j] - '0'] = true;
                }
            }
            Arrays.fill(record, false);
        }
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] != '.') {
                    if (record[map[j][i] - '0']) {
                        //已经存在了
                        return false;
                    }
                    record[map[j][i] - '0'] = true;
                }
            }
            Arrays.fill(record, false);
        }
        for (int i = 0; i < map.length; i = i + 3) {
            for (int j = 0; j < map[0].length; j = j + 3) {
                for (int k = i; k < j + 3; k++) {
                    for (int l = j; l < +3; l++) {
                        if (map[k][l] != '.') {
                            if (record[map[k][l] - '0']) {
                                //已经存在了
                                return false;
                            }
                            record[map[j][i] - '0'] = true;
                        }
                    }
                }
                Arrays.fill(record, false);
            }
        }
        return true;
    }

}
