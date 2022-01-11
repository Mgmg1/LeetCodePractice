package Leet1;

import java.util.*;

/**
 * 课程表II
 * 相比Leet207课程表I，这次需要输出可能的安排顺序
 */
public class Leet210 {

    public static void main(String[] args) {
//        int[][] courses = {
//                {1,0}
//        };
//        int[][] courses = {
//                {1,0},{2,0},{3,1},{3,2},{5,6}
//        };
        int[][] courses = {
                {1,0},{2,0},{0,2},{3,2},{5,6}
        };
//        System.out.println(Arrays.toString( courseTable( 6,courses ) ));
        System.out.println(Arrays.toString( coursesTable2( 6,courses ) ));
    }

    /**
     * 有向图节点，本题不涉及边的权值，可以简化定义
     */
    private static class Vertex {
        List<Integer> nexts = new ArrayList<>(); //
    }



    /**
     * 采用不断消除入度为0的节点的方法
     * @param courses [1,0]：这表示课程0需要先学习课程1
     * @return
     */
    private static int[] courseTable( int n,int[][] courses ) {
        if( courses == null || n == 0 ) { return new int[0]; }
        //计算最大课程号
        int max = 0;
        for (int i = 0; i < courses.length; i++) {
            max = Math.max( courses[i][0],courses[i][1] );
        }
        //得到最大的课程个数
        max++;
        //构建有向图,类似于邻接表的思路
        //同时计算每个节点的入度
        int[] inDegree = new int[max];
        Vertex[] nodes = new Vertex[max];
        for (int i = 0; i < courses.length; i++) {
            if( nodes[courses[i][0]] == null ) {
                nodes[courses[i][0]] = new Vertex();
            }
            if( nodes[courses[i][1]] == null ) {
                nodes[courses[i][1]] = new Vertex();
            }
            inDegree[courses[i][0]]++;
            nodes[courses[i][1]].nexts.add( courses[i][0] );
        }
        //搜索所有的入度为0的节点，并删去所连接的边
        int[] order = new int[n];
        int i = 0;
        boolean flag = false;
        do {
            flag = false;
            for (int j = 0; j < inDegree.length; j++) {
                //node[j] == null 说明不存在该课程，即提供的课程号是不连续的
                if( nodes[j] != null && inDegree[j] == 0 ) {
                    flag = true;
                    for (Integer child :
                            nodes[j].nexts) {
                        inDegree[child]--;
                    }
                    order[i++] = j;
                    inDegree[j] = - 1;
                }
            }
        }while ( flag );
        return i == n ? order : new int[0];
    }

    /**
     * 使用类似于后序遍历的方式，假如存在指向栈中节点的环，则没有可行的顺序
     * @param n
     * @param courses
     * @return
     */
    private static int[] coursesTable2( int n,int[][] courses ) {
        if( courses == null || n == 0 ) { return new int[0]; }
        //可到最大课程号+1
        int max = 0;
        for (int i = 0; i < courses.length; i++) {
            max = Math.max( courses[i][0],courses[i][1] );
        }
        max++;


        //构建邻接表
        Vertex[] nodes = new Vertex[max];
        for (int i = 0; i < courses.length; i++) {
            if( nodes[courses[i][0]] == null ) {
                nodes[courses[i][0]] = new Vertex();
            }
            if( nodes[courses[i][1]] == null ) {
                nodes[courses[i][1]] = new Vertex();
            }
            nodes[courses[i][1]].nexts.add( courses[i][0] );
        }
        LinkedList<Integer> result = new LinkedList<>();
        //进行后序遍历
        Stack<Integer> nStack = new Stack<>();
        Stack<Integer> iStack = new Stack<>();
        boolean[] visited = new boolean[max];
        for (int i = 0; i < nodes.length; i++) {
            if( nodes[i] != null && !visited[i] ) {
                nStack.empty();
                iStack.empty();
                int p = i;
                int k = 0;
                while ( true ) {
                    visited[p] = true;
                    while ( k < nodes[p].nexts.size() && visited[nodes[p].nexts.get(k)] ) {
                        //查找是否有指向栈内的节点
                        if( nStack.contains(nodes[p].nexts.get(k)) || nodes[p].nexts.get(k) == p  ) {
                            return new int[0];
                        }
                        k++;
                    }
                    if( k != nodes[p].nexts.size() ) {
                        nStack.push( p );
                        iStack.push( k );
                        p = nodes[p].nexts.get(k);
                        k = 0;
                    }else {
                        //输出
//                        System.out.println( p );
                        result.add( p );
                        while ( !nStack.isEmpty() && iStack.peek() == nodes[nStack.peek()].nexts.size() - 1 ){
                            p = nStack.pop();
                            k = iStack.pop();
                            //输出
//                            System.out.println( p );
                            result.add( p );
                        }
                        if( nStack.isEmpty() ) {
                            break;
                        }else {
                            p = nStack.pop();
                            k = iStack.pop() + 1;
                        }
                    }
                }
            }
        }
        int[] arr = new int[result.size()];
        int j = arr.length - 1;
        for (Integer i :
                result) {
            arr[j--] = i;
        }
        return arr;
    }
}
