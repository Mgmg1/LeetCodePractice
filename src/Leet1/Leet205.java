package Leet1;

/**
 * 同构字符串
 */
public class Leet205 {

    public static void main(String[] args) {
//        String str1  = "egg";
//        String str2  = "add";
//        String str1  = "foo";
//        String str2  = "bar";
        String str1  = "paper";
        String str2  = "title";
        System.out.println( sameStructureStr( str1,str2 ) );
    }

    /**
     * 思路：利用一个数组映射字符，然后检查后面的字符是否符合已映射结果即可
     * @param str1
     * @param str2
     * @return
     */
    private static boolean sameStructureStr( String str1,String str2 ) {
        if( str1 == null || str2 == null ) { return false; }
        if( str1.length() != str2.length() ) { return false;}

        int[] mapping = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            if( mapping[str1.charAt(i)] == 0 ) {
                //因为是双射关系，这个循环可以再用一个映射数组优化掉，即以空间换时间
                for (int j = 'a'; j <= 'z'; j++) {
                    if( mapping[j] == str2.charAt(i) ) {
                        return false;
                    }
                }
                mapping[str1.charAt(i)] = str2.charAt(i);
            }else if( mapping[str1.charAt(i)] != str2.charAt(i) ) {
                return false;
            }
        }
        return true;
    }
}
