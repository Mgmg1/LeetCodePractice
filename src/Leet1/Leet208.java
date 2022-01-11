package Leet1;

import java.util.LinkedList;

/**
 * 实现前缀树
 * 思路：相同的前缀合并，插入时，最后一个字符进行标记
 */
public class Leet208 {

    private static Node[] roots = new Node[26];
    private static class Node {
        char c;
        Node[] children = new Node[26];
        boolean flag = true;
        public Node(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        insert( "apple" );
        System.out.println( search("apple") );
        System.out.println( search("app") );
        System.out.println( startWith("app") );
        insert("app");
        System.out.println( search("app") );
    }

    private static void insert( String str ) {
        if( str == null || str.length() == 0 ) { return; }
        int i = str.charAt(0) - 'a';
        if(  roots[i] == null ) {
            roots[i] = new Node(str.charAt(0));
        }
        int k;
        Node p = roots[i];
        for (int j = 1; j < str.length(); j++) {
            k = str.charAt(j) - 'a';
            if( p.children[k] == null ) {
                p.children[k] = new Node(str.charAt(j));
            }
            p = p.children[k];
        }
        p.flag = false;
    }
    private static boolean search( String str ) {
        if( str == null || str.length() == 0  ) { return false; }
        int i = str.charAt(0) - 'a';
        if(  roots[i] == null ) {return false;}
        int j;
        int k;
        Node p = roots[i];
        for (j = 1; j < str.length(); j++) {
            k = str.charAt(j) - 'a';
            if( p.children[k] == null || p.children[k].c != str.charAt(j) ) {
                break;
            }
            p = p.children[k];
        }
        return j == str.length() && !p.flag;
    }

    private static boolean startWith( String str ) {
        if( str == null || str.length() == 0  ) { return false; }
        int i = str.charAt(0) - 'a';
        int j = 0;
        Node p = roots[i];
        while ( j < str.length() && p != null && p.c == str.charAt(j) ) {
            j++;
            if( j >= str.length() ) {
                break;
            }
            p=p.children[str.charAt(j) - 'a'];
        }
        return j == str.length();
    }
}
