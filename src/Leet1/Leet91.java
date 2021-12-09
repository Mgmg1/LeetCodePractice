package Leet1;

/*
    解码方法
    A - > 1
    B - > 2
    ....
    Z - > 26
    给定一行数字字符串，输出共有多少中种编码方法 (输入不为空)
 */
public class Leet91 {

    public static void main(String[] args) {

//        System.out.println( recurEncodeCount("22010".toCharArray(),0) );
        System.out.println( dpEncodeCount("22010".toCharArray()) );

    }

    /*
        递归形式
        显然存在重复的 重复的子问题，可以转动态规划

        思路：
            正确的编码方式 应该保证 str[n] ！=‘0’，因此应该作为递归出口条件 ！！！
            只有 ( str[n] == '2' || str[n] == '1' ) && n + 1 < str.length && str[n + 1] <= '6' 才能进入下一个队规
            此时可以保证 str[n]str[n+1] 对应一个正确的字母
     */
    public static int recurEncodeCount( char[] str,int n ) {

        if(str == null || str.length <= n ) {
            return 0;
        }
        //需要两个递归出口，可带入验证
        if( str.length - 1 == n ) {
            return str[n] == '0' ? 0 : 1;
        }
        if( str[n] == '0' ) {
            return 0;
        }
        if( str.length - 2 == n ) {
            if((  str[str.length - 2] == 1 && str[str.length - 1] != '0'  ) ||
            str[str.length - 1] == 2 && str[str.length - 1] > '0' && str[str.length -1] <= '6') {
                return 2;
            }
            return str[n + 1] == '0' ? 0 : 1;
        }

        //排除0开头的,0 开头说明是非法的!!!
        //正确的编码方式应该保证，每次进入时，str[n] 不是0
        int count = 0;
        // 1，2 开头
        if( n + 1 < str.length && ( str[n] == '1' || (str[n] == '2' && str[n + 1] <= '6') )) {
            count+= recurEncodeCount( str,n+2 );
        }
        count+= recurEncodeCount(str,n+1);
        return count;
    }


    /*
        递归方式
     */
    public static int dpEncodeCount( char[] str ) {

        if( str == null || str.length == 0 ) return 0;
        if( str.length == 1 ) return str[0] == '0' ? 0 : 1;

        int[] dp = new int[str.length];
        dp[str.length - 1] = str[str.length - 1] == '0' ? 0 : 1;
        if( str[str.length - 2] == 0 ) {
            dp[str.length - 2] = 0;
        }else {
            //要注意到所有非法编码的情况！！！
            dp[str.length - 2] =
                    str[str.length - 2] == '1' || ( str[str.length - 2] == '2' &&
                            str[str.length-1] <= '6' )? dp[str.length-1] + 1 : dp[str.length - 1] ;
        }
        for (int i = str.length - 3; i >= 0; i--) {
            if( str[i] == '0' ) {
                dp[i] = 0;
                continue;
            }
            if( str[i] == '1'|| ( str[i] == '2' && str[i+1] <= '6' ) ) {
                dp[i]+=dp[i+2];
            }
            dp[i]+=dp[i+1];
        }
        return dp[0];
    }
}
