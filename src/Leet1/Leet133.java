package Leet1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
    克隆图:
    只给出图中一个节点的引用，要求克隆:因为不清楚到底有多少个节点，所以使用hashmap记录 label 到  VertexNode 的映射
 */
public class Leet133 {

    public static class VertexNode {
        List<VertexNode> neighbors = new LinkedList<>();
        int label; //标签，按照图的数据结构，这个是不重复的，代表下标
    }

    public static void main(String[] args) {
        String str = "{0,1,2#1,2#2,2}";
        String[] split = str.substring(1, str.length() - 1).split("#");
        HashMap<String, VertexNode> inputMap = new HashMap<>();
        VertexNode origin = null;
        for (String spiltStr :
                split) {
            String[] ele = spiltStr.split(",");

            VertexNode vertexNode = null;
            if( !inputMap.containsKey( ele[0] ) ) {
                vertexNode = new VertexNode();
                vertexNode.label = Integer.parseInt( ele[0] ) ;
                inputMap.put( ele[0],vertexNode );
            }else {
                vertexNode = inputMap.get(ele[0]);
            }

            vertexNode.label = Integer.parseInt(ele[0]);

            for (int i = 1; i < ele.length; i++) {
                if( inputMap.containsKey( ele[i] ) ) {
                    vertexNode.neighbors.add( inputMap.get( ele[i] ) );
                }else {
                    VertexNode vertexNode1 = new VertexNode();
                    vertexNode1.label = Integer.parseInt(ele[i]);
                    inputMap.put( ele[i],vertexNode1 );
                    vertexNode.neighbors.add( vertexNode1 );
                }
            }
        }
        origin = inputMap.get( Integer.toString(0) );
        VertexNode clone = clone(origin);
    }

    static HashMap<Integer, VertexNode> map = new HashMap<>();
    public static VertexNode clone( VertexNode origin  ) {
        if( origin == null ) return null;
        VertexNode result = new VertexNode();
        result.label = origin.label;
        map.put( origin.label,origin );
        for (VertexNode child:
             origin.neighbors) {
            if( !map.containsKey( child.label ) ) {
                result.neighbors.add( clone(child) );
            }else {
                result.neighbors.add( map.get(child.label) );
            }
        }
        return result;
    }

}
