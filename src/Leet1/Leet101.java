package Leet1;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

/*
    对称二叉树
 */
public class Leet101 {



    public static void main(String[] args) {

        Integer[] nodes = {
                1,2,2,null,3,null,3
        };
        System.out.println( isSymmetricalTreeTravel( nodes,0,0 ) );
        System.out.println( isSymmetricalTree( nodes ) );
    }


    public static boolean isSymmetricalTree( Integer[] nodes ) {

        if( nodes == null || nodes.length == 0 ) {
            return true;
        }
        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        int i = 0;
        int j = 0;
        boolean flag1 = true;
        boolean flag2 = true;
        int left = 0,right = 0;
        stack1.addLast(0);
        stack2.addLast(0);
        while ( true ) {
            while ( left <= right ) {
                left++;
                if( flag1 ) {
                    i = i*2 + 1;
                }else {
                    flag1 = true;
                }
                if( i < nodes.length ) {
                    //访问
                    if( left == right ) {
                        if(  j >= nodes.length || !Objects.equals( nodes[i],nodes[j] )) {
                            return false;
                        }
                    }
                    stack1.addLast( i );
                }else {
                    if( left == right  ) {
                        if( j < nodes.length ) {
                            return false;
                        }
                        Integer node1 = stack1.removeLast();
                        Integer node2 = stack2.removeLast();
                        if( node1 == 0 && node2 == 0 ) {
                            return true;
                        }
                        i = node1*2 + 2;
                        j = node2*2 + 1;
                        flag1 = flag2 = false;
                    }
                }
            }
            while (  right <= left ) {
                right++;
                if( flag2 ) {
                    j = j*2 + 2;
                }else {
                    flag2 = true;
                }
                if( j < nodes.length ) {
                    //访问
                    if( left == right ) {
                        if( i >= nodes.length || !Objects.equals( nodes[i],nodes[j] ) ) {
                            return false;
                        }
                    }
                    stack2.addLast( j );
                }else {
                    if( left == right  ) {
                        if( i < nodes.length ) {
                            return false;
                        }
                        Integer node1 = stack1.removeLast();
                        Integer node2 = stack2.removeLast();
                        if( node1 == 0 && node2 == 0 ) {
                            return true;
                        }
                        i = node1*2 + 2;
                        j = node2*2 + 1;
                        flag1 = flag2 = false;
                    }
                }
            }
        }
    }

    public static boolean isSymmetricalTreeTravel(Integer[] nodes, int i,int j  ) {

        if( i >= nodes.length || j >= nodes.length  ) {
            return  i>= nodes.length && j >= nodes.length;
        }
        boolean result = Objects.equals(nodes[i],nodes[j]);
        if( !result ) {
            return false;
        }
        result = isSymmetricalTreeTravel( nodes,2*i+1,2*j + 2 );
        if( !result ) {
            return false;
        }
        if( i != 0 ) {
            result = isSymmetricalTreeTravel( nodes,2*i+2,2*j+1 );
        }
        return result;
    }


}
