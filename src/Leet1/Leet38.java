package Leet1;

/*
    报数
    观察得到 下一个序列等于  相同数字的个数+该数字

    写 while 循环时，一定要记得更新变量  ！！！
    写完程序一定要检查 边界性输入 ， 特别时 输入规模为0，1，2时。
    有输入上限时也要测试
 */
public class Leet38 {

    public static void main(String[] args) {

        for (int i = 1; i < 10; i++) {
            System.out.println( tellSequence( i ) );
        }

    }

    public static String tellSequence( int n ) {

        String prior = "1";
//        String result = null;  错误的，当 n = 1 时，result 返回为 null
        String result = "1";
        for (int i = 1; i < n; i++) {
            int j = 1;
            int k = 1;
            result = "";
            while ( j < prior.length() ) {
                if( prior.charAt(j-1) == prior.charAt( j ) ) {
                    k++;
                }else {
                    result = result + k + prior.charAt(j - 1) ;
                    k = 1;
                }
                j++;
            }
            result = result + k + prior.charAt(j - 1) ;
            prior = result;
        }
        return result;
    }

}
