package Leet1;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的右视图
 * 思路：深度优先遍历（从右子树开始遍历记录下当前深度，当更新最大深度时边放入链表中）
 *      广度优先均可（ 每次更新深度后，就把节点放入链表，同样从右子树开始 ）
 */
public class Leet199 {

    private static class BSNode {
        int data;
        BSNode lChild;
        BSNode rChild;
        public BSNode(BSNode lChild, BSNode rChild,int data) {
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {
        /*
            1
          2    3
           4  5
         */
        BSNode bsNode = new BSNode(null,null,4);
        BSNode bsNode1 = new BSNode(null,null,5);
        BSNode bsNode2 = new BSNode(null,bsNode,2);
        BSNode bsNode3 = new BSNode(bsNode1,null,3);
        BSNode root = new BSNode(bsNode2,bsNode3,1);

//        List<BSNode> bsNodes = rightViewOfTree2(root);

        LinkedList<BSNode> bsNodes = new LinkedList<>();
        rightViewOfTree1( root,0,bsNodes );

        StringBuilder sb = new StringBuilder();
        bsNodes.forEach( node ->{
               sb.append( node.data + "  ");
        });
        System.out.println( sb );

    }


    private static void rightViewOfTree1( BSNode bsNode,
                                         int height,
                                         List<BSNode> container) {
        if( bsNode == null ) { return; }
        if( height == container.size() ) {
            container.add( bsNode );
        }
        rightViewOfTree1( bsNode.rChild,height + 1,container );
        rightViewOfTree1( bsNode.lChild,height + 1,container );
    }

    private static List<BSNode> rightViewOfTree2( BSNode bsNode ) {

        LinkedList<BSNode> result = new LinkedList<>();
        if( bsNode == null ) { return result; }

        LinkedList<BSNode> queue = new LinkedList<>();
        queue.addLast( bsNode );
        int currentLevelCount = 1,nextLevelCount = 0,currentCount = 0;
        while ( !queue.isEmpty() ) {
            BSNode out = queue.removeFirst();
            if( currentCount == 0 ) {
                result.add( out );
            }
            if( out.rChild != null ) {
                nextLevelCount++;
                queue.add( out.rChild );
            }
            if( out.lChild != null ) {
                nextLevelCount++;
                queue.add( out.lChild );
            }
            currentCount++;
            if( currentCount == currentLevelCount ) {
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
                currentCount = 0;
            }
        }
        return result;
    }

}
