package Leet1;

/*
    二进制求和
    c语言解法返回字符串需要带上\0，当字符串的最大长度不确定时，从确定的最低长度开始填充  \0,
    必要时，可以让 指针s++ 来偏移开始值
    由于左边是高位，运算遍历需要从高往低，输出字符串时也是左边位高位，因此插入时采用头插入方式，或者返回时调用reverse
 */
public class Leet67 {

    public static void main(String[] args) {
        System.out.println(addBinary( "11","1" ));
        System.out.println(addBinary( "1010","1011" ));
    }

    public static String addBinary(String a,String b){
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() -1;
        int j = b.length() - 1;
        while ( i >= 0 ||  j >= 0 || carry != 0  ){
            int addResult =  carry + ( i >= 0 ? ( a.charAt( i ) - '0') : 0 ) +
                    ( j >= 0 ? ( b.charAt( j ) - '0') : 0 ) ;
            carry = addResult >> 1 ;
            sb.insert( 0,addResult % 2 );
            i--;
            j--;
        }
        return sb.toString();
    }

}
