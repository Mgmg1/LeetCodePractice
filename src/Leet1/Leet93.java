package Leet1;

import java.util.LinkedList;
import java.util.List;

/*
    复原ip地址
    思路：
    用一个链表记录
 */
public class Leet93 {


    public static void main(String[] args) {

        String ip = "20020511135";
        LinkedList<List<Integer>> lists = new LinkedList<>();
        LinkedList<Integer> record = new LinkedList<>();
        recurRestoreIpAddress( ip.toCharArray(),0,lists,record );
        System.out.println( lists );

    }

    /*
        递归方式
        思路：找到可能的有效的 三位内的ip就，用链表记录下来，当有四个有效ip时，放入result。
     */
    public static void recurRestoreIpAddress(char[] ips, int n,
                                             List<List<Integer>> result, LinkedList<Integer> record) {
        if( record.size() == 4 || n == ips.length ) {
            if( record.size() == 4 && n == ips.length ) {
                result.add((List<Integer>) record.clone());
            }
            return;
        }
        if( ips[n] == '0' ) {
            record.addLast(0);
            recurRestoreIpAddress( ips,n+1,result,record );
            record.removeLast();
            return;
        }
        int addr = 0;
        for (int i = n; i < ips.length && i < n + 3; i++) {
            addr*=10;
            addr+=ips[i] - '0';
            if( addr < 256 ) {
                record.addLast(addr);
                recurRestoreIpAddress( ips,i + 1,result,record );
                record.removeLast();
            }
        }
    }


}
