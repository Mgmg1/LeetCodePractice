package Leet1;
/*
    实现strStr()
    找出needle串在haystack中的起始位置下标，无则返回-1
 */
public class Leet28 {


    public static void main(String[] args) {

        char[] str = "awdaabaabacawadd".toCharArray();
        char[] needle = "abaabaca".toCharArray();
        System.out.println( strStrKMP(str,needle ));

    }

    /*

     */
    public static int strStr( char[] str,char[] needle ) {

        for (int i = 0; i < str.length; i++) {
            int k =i;
            int j = 0;
            for (; j < needle.length; j++) {
                if( k >= str.length || str[k] != needle[j] ) {
                    break;
                }
                k++;
            }
            if( j == needle.length ) {
                return i;
            }
        }
        return -1;
    }

    /*
        KMP算法匹配
        不匹配时，pattern[i]是下一个使得 str[i] == needle[pattern[j]]
        并  <=j 的needle元素匹配str
     */
    public static int strStrKMP( char[]str,char[]needle ) {

        if( str == null ) return -1;
        if( needle == null  ) return -1;
        if( needle.length == 0 ) return 0;

        int[] pattern = new int[needle.length];
        //pattern[0] 是起点
        pattern[0] = -1;
        int j = 1;
        //获得模式串的关键
        //当needle[j]!=str[i]时  pattern[j] 意味着，
        //若pattern[j] == -1 needle[0] ！= str[i]
        // 令j = pattern[j] + 1,i++;

        //若 0 <= pattern[j] 时，有 needle[0~pattern[j]]匹配str子串，此时恰 needle[pattern[j]] == str[i],
        //令 j = pattern[j] + 1,i++
        while ( j < pattern.length ) {
            int k = pattern[j-1] + 1;
            while ( k >= 0 && needle[k] != needle[j] ) {
                //当 k == 0 时，k-1越界，此时可以直接判断，needle[k=0] != needle[j]，k退一步只能等于 -1了
                //k > 0 时，needle[0~pattern[k-1]]匹配needle子串,此时恰 pattern[k-1] == needle[j-1]
                //但是，pattern[k] != needle[j],只能寻找下一个k使得 pattern[k-1] == needle[j-1]
                k = k != 0 ? pattern[k-1] + 1 : -1;
            }
            pattern[j] = k;
            j++;
        }
        int i = 0,k = 0;
        while ( i < str.length ) {
            int l = i;
            while ( l < str.length && k < needle.length && str[l] == needle[k] ) {
                l++;
                k++;
            }
            if( l == str.length ) {
                if( k == needle.length ) {
                    return i;
                }
                return -1;
            }
            if( k == needle.length ) {
                return i;
            }
            k = pattern[k] + 1;
            i++;
        }
        return -1;
    }

}
