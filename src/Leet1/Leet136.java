package Leet1;
/*
    只出现一次的数字
 */
public class Leet136 {

    public static void main(String[] args) {
        System.out.println(onceOccurNumber(new int[]{ 2,2,1 })  );
        System.out.println(onceOccurNumber(new int[]{ 4,1,2,1,2 })  );
    }

    public static int onceOccurNumber( int[] arr ) {

        int m = 0;
        for (int num :
                arr) {
            m ^= num;
        }
        return m;
    }

}
