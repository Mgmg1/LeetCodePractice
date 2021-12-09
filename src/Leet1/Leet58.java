package Leet1;

/*
    最后一个单词的长度
    熟悉字符串操作即可
 */
public class Leet58 {

    public static void main(String[] args) {
        System.out.println(LengthOfLastWord(" Hello World "));
        System.out.println(LengthOfLastWord2(" Hello World "));
    }

    //使用java api的简易版
    public static int LengthOfLastWord(String str){
        String[] s = str.trim().split(" ");
        return  s[s.length-1].length();
    }

    //复杂版，直接操作char
    public static int LengthOfLastWord2(String str){

        if( str == null || str.length() == 0 ) {
            return 0;
        }

        int begin = 0;
        int end = str.length() - 1;
        for (int i = 0; i < str.length(); i++) {
            if( str.charAt(i) != ' '){
                begin = i;
                break;
            }
        }
        for (int i = str.length() -1 ; i >= 0; i--) {
            if( str.charAt(i) != ' '){
                end = i;
                break;
            }
        }
        int i = end;
        for ( i = end ; i>=begin && str.charAt(i)!=' ';i--);
        if( i < begin ) {
            return  end - begin + 1;
        }else {
            return end - i;
        }
    }
}
