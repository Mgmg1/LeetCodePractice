package Leet1;

/*
    单词搜索
 */
public class Leet79ToDo {

    public static void main(String[] args) {
        char[][] map = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'},
        };
//        System.out.println(way1(map,"ABCCED"));
        System.out.println(way1(map,"SEE"));
        System.out.println(way1(map,"ABCB"));
    }
    public static boolean way1(char[][] map,String word) {

        if( map ==null || map.length == 0 ) {
            return false;
        }
        boolean[][] flags = new boolean[map.length + 2][map[0].length + 2];
        for (int i = 0; i <= map.length + 1; i++) {
            flags[i][0] = flags[i][map[0].length + 1 ] = true;
        }
        for (int i = 0; i <= map[0].length + 1; i++) {
            flags[0][i] = flags[map.length + 1][i] = true;
        }
        for (int i = 1; i <= map.length ; i++) {
            for (int j = 1; j <= map[0].length; j++) {
                if( way11( map,flags,i,j,word,0 ) ) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean way11( char[][] map,
                              boolean[][] flags,
                              int i, int j,
                              String word,int n) {

        if( n == word.length() ) {
            return true;
        }
        if( !( map[i-1][j-1] == word.charAt(n)) ) {
            return false;
        }
        flags[i][j] = true;
        if( !flags[i][j-1] && way11( map,flags,i,j-1,word,n+1 )){
            return true;
        }
        if( !flags[i][j+1] &&  way11( map,flags,i,j+1,word,n+1 ) ) {
            return true;
        }
        if( !flags[i+1][j] && way11( map,flags,i+1,j,word,n+1 )) {
            return true;
        }
        if( !flags[i-1][j] &&  way11( map,flags,i-1,j,word,n+1 )  ) {
            return true;
        }
        flags[i][j] = false;
        return false;
    }

}
