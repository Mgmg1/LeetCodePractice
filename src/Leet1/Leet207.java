package Leet1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/*
    课程表
 */
public class Leet207 {

    private static class GNode {
        int data;
        GNode[] next;
        boolean visited;

        public GNode(int data, GNode[] next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

//        System.out.println(canFinish( 2,new int[][]{{1,0},{0,1}} ));
//        System.out.println(canFinish( 2,new int[][]{{1,0}} ));
        simplePostTravel();

    }

    //这是leetcode上的标准方法，也是算法设计课本上的标准方法之一
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] prereq : prerequisites) {
            indegree[prereq[0]]++;
        }
        Set<Integer> zeroDegree = new HashSet();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                zeroDegree.add(i);
            }
        }
        if (zeroDegree.isEmpty()) {
            return false;
        }

        while (!zeroDegree.isEmpty()) {
            Iterator<Integer> it = zeroDegree.iterator();
            int course = it.next();
            zeroDegree.remove(course);
            for (int[] prereq : prerequisites) {
                if (prereq[1] == course) {
                    indegree[prereq[0]]--;
                    if (indegree[prereq[0]] == 0) {
                        zeroDegree.add(prereq[0]);
                    }
                }
            }
        }

        for (int i : indegree) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }


    //根据算法设计课本上后后序遍历确定先后顺序的思想改编的算法。关键点是确定是否存在指向当前栈中的父节点的节点
    public static void simplePostTravel(  ) {
        GNode gNode1 = new GNode(3,null);
        GNode gNode2 = new GNode(2,null);
        GNode gNode3 = new GNode(1,new GNode[]{gNode1,gNode2});
        Stack<GNode> stack = new Stack<>();
        GNode[] v = {gNode3, gNode2, gNode1};
        boolean flag = true;
        for (GNode node :
                v) {
            stack.push( node );
            flag = flag && postTravel( node,stack );
            stack.pop();
        }
        System.out.println( flag );
    }

    public static boolean postTravel(GNode g , Stack<GNode> stack) {
        if( g.next == null ) {
            return true;
        }
        boolean flag = true;
        for (GNode next :
                g.next) {
            if( next != null  ) {
                if( stack.contains( next ) ) {
                    return false;
                }
                if( !next.visited ) {
                    stack.push( next );
                    next.visited = true;
                    flag = flag && postTravel( next,stack );
                }
            }
        }
        stack.pop();
        return flag;
    }

}
