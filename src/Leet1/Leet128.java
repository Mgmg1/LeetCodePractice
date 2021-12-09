package Leet1;

import java.util.*;

/*
    最长连续序列
 */
public class Leet128 {

    public static void main(String[] args) {
        int[] arr = new int[]{100, 4, 200, 1, 3, 2};
//        System.out.println( longestConsecutive( arr ) );
//        System.out.println( longestConsecutive2( arr ) );
        System.out.println( longestConsecutive3( arr ) );
    }

    /*
        这个方法是 集合操作相关
        主要操作是
        map映射翻转，排除重复的元素：
        连接路径，而不是压缩路径，让元素加入 同一个区间的集合
     */
    public static int longestConsecutive(int[] arr) {
        int[] ids = new int[arr.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
        UnionFind unionFind = new UnionFind(ids);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            if( map.containsKey( arr[i] ) ) {
                continue;
            }
            map.put( arr[i],i);
            if( map.containsKey( arr[i] - 1) ) {
                unionFind.union( i,map.get(arr[i]-1) );
            }
            if( map.containsKey( arr[i] + 1) ) {
                unionFind.union( i,map.get(arr[i]+1) );
            }
        }
        return unionFind.maxUnion();
    }

    private static class UnionFind {

        int[] ids;

        public UnionFind(int[] ids) {
            this.ids = ids;
        }

        public int find(int id) {
            while ( id != ids[id] ) {
                ids[id] = ids[ids[id]];
                id = ids[id];
            }
            return id;
        }

        public void union( int id1,int id2) {
            int i = find(id1);
            int j = find(id2);
            ids[i] = j;
        }

        public int maxUnion( ) {
            for (int i = 0; i < ids.length; i++) {
                find(i);
            }
            int[] count=  new int[ids.length];
            int max = 0;
            for (int i = 0; i < ids.length; i++) {
                count[ids[i]]++;
                max = Math.max( max,count[ids[i]]);
            }
            return max;
        }

    }


    /*
        使用HashSet
     */
    public static int longestConsecutive2( int[] arr ) {
        if( arr == null || arr.length == 0 ) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int ele :
                arr) {
            set.add( ele );
        }
        int maxLength = 0;
        int currentLength = 0;
        for (int ele :
                set) {
            if( !set.contains(ele-1) ) {
                currentLength = 1;
                for (int i = ele + 1; set.contains(i) ; i++) {
                    currentLength++;
                }
                maxLength = Math.max( maxLength,currentLength );
            }
        }
        return maxLength;
    }

    /*
        使用hashMap
     */
    public static int longestConsecutive3( int[] arr ) {
        if( arr == null || arr.length == 0 ) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele :
                arr) {
            if( map.containsKey( ele - 1 ) && map.containsKey( ele + 1 ) ) {
                int v1 = map.remove( ele - 1 );
                int v2 = map.remove( ele + 1 );
                map.put( v2,v1 );
                map.put( v1,v2 );
            }else if( map.containsKey( ele - 1 ) ) {
                int v1 = map.remove( ele - 1 );
                map.put( v1,ele );
                map.put( ele,v1 );
            }else if( map.containsKey( ele + 1 ) ) {
                int v2 = map.remove( ele + 1 );
                map.put( v2,ele );
                map.put( ele,v2 );
            }else {
                map.put( ele,ele );
            }
        }
        int max = 0;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry :
                entries) {
            max = Math.max( Math.abs(entry.getValue() - entry.getKey()) + 1 ,max) ;
        }
        return max;
    }
}
