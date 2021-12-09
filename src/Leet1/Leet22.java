package Leet1;

import java.util.*;

/*
    括号生成
    根据已给例子可以发现，都是不重复的括号
    需要注意！！！ 非递归遍历树前序和后序，当进入的子节点是父节点的右节点时，父节点会被出栈！！！
    //也就是说，在遍历树时，当左节点是null则父节点出栈进入右节点！！
    利用栈前序和中序模拟遍历树，关键点是  模拟出 节点为null 的条件！！！本题条件为
    p.lCount > p.rCount || p.lCount < 0 || p.rCount < 0
 */
public class Leet22 {

    private static class Node {
        String str;
        int lCount;
        int rCount;
        public Node() {}
        public Node(String str, int lCount, int rCount) {
            this.str = str;
            this.lCount = lCount;
            this.rCount = rCount;
        }
    }

    public static void main(String[] args) {
        List<String> strings1 = new ArrayList<>();
        int n = 4;
        bracketsProduced(strings1,"",n,n);
        List<String> strings2 = bracketsProduced(n);
        System.out.println(Arrays.toString( strings1.toArray() ));
        System.out.println(Arrays.toString( strings2.toArray() ));
    }

    /*
        递归方式
     */
    private static void bracketsProduced(List<String> result,String str,int lCount,int rCount ) {

        if( lCount == 0 && rCount == 0 ) {
            result.add( str );
            return;
        }
        if( lCount <= rCount && lCount != 0 ) {
            bracketsProduced(result,str + '(',lCount-1,rCount);
        }
        if( lCount < rCount && rCount != 0 ) {
            bracketsProduced(result,str + ')',lCount,rCount-1);
        }

    }

    /*
        栈方式
        一般栈用于替代递归遍历树，虽然没有树，但是可以根据 lCount 和 rCount 模拟出一棵 括号 的树的操作
        想象是在遍历它。栈遍历树时，分不清弹出来的节点是不是父节点，因此无法分清遍历的节点是父节点的右子树还是左子树
        (因为弹出来的不一定是父节点)
        递归转化为栈时，栈要保存递归的状态,这体现在参数等
     */
    private static List<String> bracketsProduced(int n) {

        LinkedList<Node> stack = new LinkedList<>();
        ArrayList<String> strings = new ArrayList<>();
        Node p = new Node("",n,n);
        while ( !stack.isEmpty() || p != null ) {
            //先判断是否叶子节点
            if( p.lCount == 0 && p.rCount == 0 ) {
                strings.add( p.str );
            }
            //null节点 关键点是 等价null节点的条件
            if( p.lCount > p.rCount || p.lCount < 0 || p.rCount < 0 ) {
                if( stack.isEmpty() ) {
                    p = null;
                }else {
                    p = stack.removeLast();
                    p = new Node(p.str+")",p.lCount,p.rCount-1);
                }
            }else {
                stack.addLast(p);
                 p = new Node(p.str+"(",p.lCount-1,p.rCount);
            }
        }
        return strings;
    }
}
