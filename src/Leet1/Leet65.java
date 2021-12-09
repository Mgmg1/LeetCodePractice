package Leet1;

/*
    有效数字
    算法逻辑完全是缝缝补补出来的，上下文的在想法上的的关联性不大，它的做法就像是在先写出是否是整数，在这基础上补上浮点，再补上科学计数法数
    本题的思想是 在char不符合条件时，i会被卡住，无法到达length，若到达length，也要检查是否已经扫描到了有效数字
 */
public class Leet65 {

    public static void main(String[] args) {
        System.out.println(isValidNum("0"));
        System.out.println(isValidNum(" 0.1"));
        System.out.println(isValidNum("abc"));
        System.out.println(isValidNum("1 a"));
        System.out.println(isValidNum("2e10"));
        System.out.println(isValidNum("2.e2"));
        System.out.println(isValidNum(" 2.0e2 d "));
    }

    public static boolean isValidNum(String numStr){

        int i = 0;
        while( i < numStr.length() && numStr.charAt(i) == ' ')i++;
        if ( i < numStr.length() &&  ( numStr.charAt(i) == '-' || numStr.charAt(i) == '+' )   )i++;
        boolean findNum = false;
        while ( i < numStr.length() && numStr.charAt(i) >= '0' && numStr.charAt(i)<='9' ){
            findNum = true;
            i++;
        }
        if( i < numStr.length()  && findNum &&  numStr.charAt(i) == '.' ){
            i++;
        }
        while (i < numStr.length() && numStr.charAt(i) >= '0' && numStr.charAt(i)<='9' ){
            findNum = true;
            i++;
        }
        if( i < numStr.length() && findNum && numStr.charAt(i) == 'e' && numStr.charAt(i-1) != '.' ){
            i++;
            findNum = false;
            while (i < numStr.length() && numStr.charAt(i) >= '0' && numStr.charAt(i)<='9' ){
                findNum = true;
                i++;
            }
        }
        while( i < numStr.length() && numStr.charAt(i) == ' ') i++;
        return i == numStr.length() && findNum  ;
    }

}
