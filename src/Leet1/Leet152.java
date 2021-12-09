package Leet1;

/*
    乘积最大子序列
 */
public class Leet152 {

    public static void main(String[] args) {
        int[] sequence = { -2,0,1 };
//        int[] sequence = { 0,3,-2,4 };
//        System.out.println( maxMultiplySequence( sequence ) );
//        System.out.println( recursiveMaxMultiplySequence( sequence,0,sequence.length - 1 ) );
        System.out.println( maxMultiplySequenceDp( sequence) );
    }

    /*
        O(n)的时间复杂度
     */
    private static int maxMultiplySequence( int[] sequence ) {
        if( sequence == null || sequence.length == 0 ) {return 0;}
        int result = sequence[0];
        if( sequence.length == 1) { return result; }

        boolean existZero = false;
        int negativeIntCount = 0;
        int totalMultiply;
        int leftMultiply;
        int j = -1;
        for (int i = 0; i <= sequence.length; ) {
            if( i == sequence.length || sequence[i] == 0 ) {
                if( i < sequence.length && sequence[i] == 0 ) {
                    existZero = true;
                }
                //取j + 1 ~ i - 1的区间计算负值的个数，当i-j+1 == 0时，说明区间为空
                if( i - j - 1 > 0 ) {
                    //计算 totalMultiply
                    totalMultiply = 1;
                    for (int k = j + 1; k < i; k++) {
                        totalMultiply*=sequence[k];
                    }
                    result = Math.max( result,totalMultiply );
                    if( negativeIntCount % 2 != 0 ){
                        leftMultiply = 1;
                        //边计算leftMultiply，并更新结果
                        for (int k = j + 1; k < i - 1; k++) {
                            if( sequence[k] < 0 ) {
                                if( k == 0 ) {
                                    result = Math.max( result,totalMultiply / sequence[k] );
                                }else {
                                    result = Math.max( result,leftMultiply );
                                    result = Math.max( result,totalMultiply / sequence[k] / leftMultiply );
                                }
                            }
                            leftMultiply*=sequence[k];
                        }
                    }
                    negativeIntCount = 0;
                    j = i;
                    i++;
                }
            }else {
                if( sequence[i] < 0 ) {
                    negativeIntCount++;
                }
                i++;
            }
        }
        return Math.max( result, existZero ? 0 : result );
    }

    /*
        递归的解法。
        时间复杂度为O(nlogn)
     */
    private static int recursiveMaxMultiplySequence( int[] values,int start,int end) {
        if( start == end ) {
            return values[start];
        }

        int middle = ( start + end ) / 2;
        int result = values[middle];

        if( result != 0 ) {
            int leftMultiply = 1;
            int rightMultiply = 1;

            int lMax = 1;
            int lMin = 1;
            int rMax = 1;
            int rMin = 1;

            boolean existLZero = false;
            boolean existRZero = false;
            for (int i = middle - 1; i >= start; i--) {
                if( values[i] == 0 ) {
                    existLZero = true;
                    break;
                }
                leftMultiply*=values[i];
                lMax = Math.max( lMax,leftMultiply );
                lMin = Math.min( lMin,leftMultiply );
            }
            for (int i = middle + 1; i <= end; i++) {
                if( values[i] == 0 ) {
                    existRZero = true;
                    break;
                }
                rightMultiply*=values[i];
                rMax = Math.max( rMax,rightMultiply );
                rMin = Math.min( rMin,rightMultiply );
            }
            //核心的判断
            if( values[middle] > 0 ) {
                int l = getSignedNum(lMax, lMin, true);
                int r = getSignedNum(rMax,rMin,true);
                result = Math.max( result,values[middle] * l * r );
                l = getSignedNum(lMax, lMin, false);
                r = getSignedNum(rMax,rMin,false);
                result = Math.max( result, values[middle] * l * r);
            }else {
                int l = getSignedNum(lMax, lMin, true);
                int r = getSignedNum(rMax,rMin,false);
                result = Math.max( result,values[middle] * l * r );
                l = getSignedNum(lMax, lMin, false);
                r = getSignedNum(rMax,rMin,true);
                result =  Math.max( result, values[middle] * l * r);
            }
            result = Math.max( result, existLZero || existRZero ? 0 : result );
        }
        if( start < middle ) {
            result = Math.max( result,recursiveMaxMultiplySequence(values,start,middle - 1) );
        }
        if( middle < end ) {
            result = Math.max( result,recursiveMaxMultiplySequence(values,middle + 1,end) );
        }
        return result;
    }
    /*
        得到对应正负性质的数字：
            position为true时，返回最大正数字
            false时返回最小负数字
            没有符合情况的则返回1
     */
    private static int getSignedNum(int a,int b,boolean positive ) {
        int max = Math.max( a,b );
        int min = Math.min( a,b );

        if (positive) {
            return max > 0 ? max : 1;
        }
        return min < 0 ? min : 1;
    }

    /*
        动态规划的做法，比较精巧
        最大乘积[0~i] = max{ sequence[i], ( sequence[i] >= 0 ? 最大乘积[0~i-1] : 最小乘积[0~i-1] ) }
        最小乘积[0~i] = max{ sequence[i], ( sequence[i] >= 0 ? 最小乘积[0~i-1] : 最大乘积[0~i-1] ) }
        最大乘积[0~i]和最小乘积[0~i] 均是 包含sequence[i]的乘积
     */
    private static int maxMultiplySequenceDp( int[] sequence ) {
        if( sequence == null || sequence.length == 0 ) { return 0; }
        if( sequence.length == 1 ) { return sequence[0]; }

        int maxSequence = sequence[0];
        int minSequence = sequence[0];
        int temp;
        int max = sequence[0];
        for (int i = 1; i < sequence.length; i++) {
            temp = maxSequence;
            maxSequence = Math.max( sequence[i], sequence[i]  * ( sequence[i] >= 0 ? maxSequence : minSequence )  );
            minSequence = Math.min( sequence[i],sequence[i] * ( sequence[i] >= 0 ? minSequence : temp ) );
            max = Math.max( max,maxSequence );
        }
        return max;
    }
}
