package Leet1;

/*
    岛屿的个数
 */
public class Leet200 {

    public static void main(String[] args) {
        int[][] islands = {
            {1,1,1,1,0},
            {1,1,0,1,0},
            {1,1,0,0,0},
            {0,0,0,0,0},
        };
        System.out.println( countOfIsland( islands ) );
    }

    /**
     * 先深度优先遍历所有的岛屿，计数，再将岛屿恢复
     * @return
     */
    private static int countOfIsland( int[][] islands ) {
        if( islands == null || islands.length == 0 || islands[0] == null || islands[0].length == 0 ) { return 0; }
        int result = 0;
        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if( islands[i][j] == 1 ) {
                    result++;
                    travel( islands,i,j );
                }
            }
        }
        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if( islands[i][j] == -1 ) {
                    islands[i][j] = 1;
                }
            }
        }

        return result;
    }
    /*
        不断对四个方向遍历，遍历过的要做标记
     */
    private static void travel( int[][] islands,int i,int j) {
        if( i < 0 || i >= islands.length || j < 0 || j >= islands[0].length || islands[i][j] != 1 ) { return; }
        islands[i][j] = -1;
        travel(islands,i + 1, j );
        travel(islands,i + 1, j );
        travel(islands,  i ,j + 1);
        travel(islands,  i ,j - 1);
    }

}
