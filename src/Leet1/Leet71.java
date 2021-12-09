package Leet1;

/*
    简化路径
 */

import java.util.LinkedList;

public class Leet71 {

    public static void main(String[] args) {
        System.out.println( simplifyPath( "/..//./a/./b/../../c" ) );
        System.out.println( simplifyPath( "/home//foo/d/../../" ) );
    }

    public static String simplifyPath(String path) {

        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();
        int begin = 0 , end = 1 ;
        for (; end < path.length()  ; end++ ) {
            if( path.charAt(end) == '/' ) {
                if( end == begin + 1 || (end == begin + 2 && path.charAt(begin + 1) == '.') ) {
                }else if( end == begin + 3 && path.charAt( begin + 1) =='.' && path.charAt( begin + 2) =='.' ) {
                    if( queue.size() != 0 ) {
                        sb.delete( queue.getLast(), sb.length() );
                        queue.removeLast();
                    }
                }else {
                    queue.addLast( sb.length() );
                    sb.append( path.substring(begin,end) );
                }
                begin = end;
            }
        }
        if( end != begin + 1 ) {
            sb.append( path.substring( begin, end ) );
        }

        return sb.toString();
    }

}
