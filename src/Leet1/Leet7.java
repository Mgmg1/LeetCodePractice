package Leet1;
/*
    反转整数
    对输入的 数组 判正负
    并得到对应符号的int最值(-2^31 或 2^31-1 )
    将输入值每位的数字取下放到数组A
    将最值每位数字放到数组B
    存储时统一变成正数

    新思路：将数组A反转，然后和 数组B从 尾部 ~ 0 比较字符串大小
    数组A小则说明没溢出，大于则溢出了 。
    数组A反转后可能导致高位为0的情况，最后转为对应的数字时要先跳过。


   总结：一般来说，检查溢出是靠 移位得到特殊值，
   再比较 所给int每位数字 （像字符串比较大小一样）来判断是否溢出
   过程中有必要 将负数转为正数来处理，结尾时转回去
 */
public class Leet7 {

    public static void main(String[] args) {

        System.out.println( reverseInt(-147483647) );
    }

    /*
        可以尝试直接使用字符串匹配。而不是使用繁杂的下标加减来匹配

        里面有很多多余的操作。。。。
     */
    public static int reverseInt( int num ) {

        // 000000。。。 反转为  111111。。。
        //通过位运算得到 int 的最大值和最小值
        int i = ~0;
        int j = 1<<31;
//        int min = j,max = i^j;
        int extreme = num < 0 ? j : i^j;
        //将每一位放到int上，从低位到高位
        int[] extremeInts= new int[15];
        int k = 0;  //因为不清楚多少位十进制，记录位数
        while ( extreme != 0  ) {
            //每一位统一取为正数，便于进行比较
            extremeInts[k++] = Math.abs(extreme % 10);
            extreme = extreme / 10;
        }
        int h = 0;
        int[] numInts = new int[15];
        int cNum = num;
        while ( cNum != 0  ) {
            numInts[h++] = Math.abs(cNum % 10);
            cNum = cNum / 10;
        }

        //只有位数相同时，才有可能发生溢出的情况                //// 这一步是多余的
        if( h == k ) {
            int count = 0;
            //当字符串长度不相等时，一定不匹配
            //字符串匹配思路，下标同时减少并且 判是否相等。跳出循环，判断是否下标已经越出，在不一起越出的情况跳出则说明不相等
            while ( --k > 0 && count < h && extremeInts[k] == numInts[h] );
            if( k > -1 && extremeInts[k] < numInts[count] ) {
                return 0;
            }
        }
        int l = 0;
        int result = 0;
        while ( l < h ) {
            //由于之前统一取为正数，现在需要取回正负值
            int numInt = num < 0 ? numInts[l] * -1 : numInts[l];
            result = result*10 + numInt;
            l++;
        }
        return result;
    }

}
