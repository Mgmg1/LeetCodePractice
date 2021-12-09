package Leet1;

/*
    两个排序数组的中位数
 */
public class Leet4 {

    public static void main(String[] args) {
        int[] arr1 = {1,3,4,5,6,7};
        int[] arr2 = {10,11,12,21};
//        int[] arr1 = {1,2};
//        int[] arr2 = {3,4};
        System.out.println( twoSortArrMedian( arr1,arr2 ) );
    }

    public static double twoSortArrMedian(int[] arr1,int[] arr2) {

        if( arr1 == null && arr2 == null ) return 0;
        //非空数组直接返回中位数
        if( arr1 == null || arr2 == null ) {
            int[] notNullArr = arr1 == null ? arr2 : arr1;
            if( notNullArr.length % 2 == 0 ) {
                return ( (double)notNullArr[notNullArr.length/2] + notNullArr[notNullArr.length/2 - 1] )/2;
            }else {
                return notNullArr[notNullArr.length/2];
            }
        }

        int[] shorterArr = arr1.length > arr2.length ?  arr2 : arr1;
        int[] longerArr = arr1.length > arr2.length ?  arr1 : arr2;

        //要求 取一半时，当length和为偶数时，取一半+1，length和为奇数时，取第一个到中位数的个数 + 1
        if( longerArr.length == 1 ) {
            if( shorterArr.length == 1 ) {
                return ( (double)longerArr[0] + shorterArr[0] ) / 2;
            }else {
                return longerArr[0];
            }
        }
        //要求long至少有两个元素
        int longI = longerArr.length / 2 - 1;  // 0 ~ longerArr.length / 2  个数为  longerArr.length / 2 + 1
        int shortI = ( shorterArr.length + longerArr.length  ) / 2 - ( longI + 1 ); // 减去  longI + 1
        // 想象 longArr占了  1 ~ longerArr.length / 2 - 1
        // shorterArr 占了 `1 ~ ( shorterArr.length + longerArr.length  ) / 2 - ( longI + 1 )
        // 再加上Arr下标为0的元素，总共占用了 ( shorterArr.length + longerArr.length  ) / 2 + 1 个元素
        // 即偶数时，占一半多一个，奇数时，刚好是中位数

        //sl：shortarr左   sr:shortarr右
        int sl = 0,sr = shorterArr.length - 1;
        boolean flag = false;
        while ( sl <= sr ) {
            if( shorterArr[shortI] <= longerArr[longI + 1] &&
                    ( shortI + 1 == shorterArr.length || longerArr[longI] <=  shorterArr[shortI + 1] ) ) {
                // shortI + 1 == shorterArr.length  当进入防止 shorterArr.length == 1时，发生空指针异常
                flag = true;
                break;
            }
            if( shorterArr[shortI] > longerArr[longI + 1] ) {
                sr = shortI - 1;
            }else {
                sl = shortI + 1;
            }

            longI = longI - ( ( sr + sl ) / 2 - shortI ) ;
            shortI = ( sr + sl ) / 2;
        }
        if( flag ) {
            if( ( shorterArr.length + longerArr.length  ) % 2 == 0 ) {
                return ( (double)shorterArr[shortI] + longerArr[longI] ) / 2;
            }
            return shorterArr[shortI] > longerArr[longI] ? shorterArr[shortI] : longerArr[longI];
        } else {
            if( ( shorterArr.length + longerArr.length  ) % 2 == 0 ) {
                if( sl == shorterArr.length ) {
                    return ( (double)shorterArr[shortI] + longerArr[longI] ) / 2;
                }else {
                    // 当short到达最左时，longI 计算没有变化,返回时 long 需要自行 + 1
                    //当 short 到达最右时 则没问题
                    return ( (double)longerArr[longI + 1] + longerArr[longI ] )/2;
                }
            }else {
                if( sl == shorterArr.length && sr == -1 ) {
                    return shorterArr[shortI] > longerArr[longI] ? shorterArr[shortI] : longerArr[longI];
                }else {
                    // 当short到达最左时，longI 计算没有变化,short仍然占着一位返回时 long 需要自行 + 1
                    //当 short 到达最右时 则没问题
                    return longerArr[longI + 1];
                }
            }
        }
    }

}
