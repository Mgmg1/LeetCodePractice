package Leet1;

/*
    罗马数字转整数
    同样基于贪心法，将对应的罗马数字从大到小排列，不存在匹配错误情况
 */
public class Leet13 {

    public static void main(String[] args) {

        System.out.println( romeToInt( "MCMXCIV" ) );

    }

    private static int romeToInt( String rome ) {

        if( rome == null || rome.length() == 0 ) return 0;
        String[] romes = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] ints = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int result = 0;
        for (int i = 0; i < romes.length ; i++) {
            while ( rome.length() > 0 ) {
                if( rome.startsWith(romes[i]) ) {
                    rome = rome.substring(romes[i].length());
                    result+=ints[i];
                }else {
                    break;
                }
            }
        }
        return result;
    }

}
