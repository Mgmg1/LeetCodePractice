package Leet1;

/*

 */
public class Leet139 {


    public static void main(String[] args) {
//        String str = "leetcode";
        String str = "catsandog";
//        wordDict = new String[]{"leet","code"};
        wordDict = new String[]{"cats","dog","sand","and","cat"};
//        System.out.println( recursiveDivideWord(str,0) );
        System.out.println( divideWord(str) );
    }
    static String[] wordDict;
    /*
        递归
     */
    public static boolean recursiveDivideWord(String str,int start ) {
        if( start == str.length() ) return true;
        for (int i = 0; i < wordDict.length; i++) {
            if( str.startsWith( wordDict[i],start ) ) {
                if( recursiveDivideWord( str,start + wordDict[i].length() ) ) return true;
            }
        }
        return false;
    }

    /*
        动态规划
     */
    public static boolean divideWord(String str ) {
        boolean[] dp = new boolean[str.length()+1];
        dp[0] = true;
        for (int i = 1; i <= dp.length; i++) {
            if( dp[i-1] ) {
                for (int j = 0; j < wordDict.length; j++) {
                    // 注意dp数组可能越界
                    if( i - 1 + wordDict[j].length() <= str.length() &&  !dp[i - 1 + wordDict[j].length()] && str.startsWith( wordDict[j],i-1 )  ) {
                        dp[i - 1 + wordDict[j].length()] = true;
                    }
                }
            }
        }
        return dp[str.length()];
    }
}
