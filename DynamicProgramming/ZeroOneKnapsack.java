package DynamicProgramming;

import static java.lang.Math.max;

public class ZeroOneKnapsack {

    public static void main(String args[]) {
         int[] weight = {4,5,1};
         int[] profit = {1,2,3};
         int capacity = 4;
         int noOfItems= weight.length;


         int maxProfitFromRecursion = recursion(weight,profit,noOfItems,capacity);
         System.out.println("maxProfitFromRecursion-----"+maxProfitFromRecursion);

        int maxProfitFromTopDown = initTopDownKnapsack(weight,profit,noOfItems,capacity);
        System.out.println("maxProfitFromTopDown-----"+maxProfitFromTopDown);

        int maxProfitFromBottomUp = bottomUpKnapsack(weight,profit,noOfItems,capacity);
        System.out.println("maxProfitFromBottomUp-----"+maxProfitFromBottomUp);

    }

    /**
     * Brute force regression method.
     * @param weight
     * @param profit
     * @param noOfItems
     * @param capacity
     * @return
     */
    public static Integer recursion(int[] weight, int[] profit, int noOfItems, int capacity) {
        int profitInclude=0;
        int profitExclude=0;
        // what's the base case?? when n= 0 or capacity = 0
        if(noOfItems==0 || capacity==0) {
            return 0;
        }
        // if nth item is chosen. the below statement will be true only if capacity-w[] >=0
        if(capacity-weight[noOfItems-1] > 0) {
            // if nth item is included
            profitInclude = profit[noOfItems - 1] + recursion(weight, profit, noOfItems - 1, capacity - weight[noOfItems - 1]);
        } else {
            // if nth item is excluded
            profitExclude = recursion(weight, profit, noOfItems - 1, capacity);
        }

        return max(profitInclude, profitExclude);
    }

    public static Integer initTopDownKnapsack(int[] weightArr, int[] profitArr, int n, int capacity) {
        // what is the matrix size ? - variables n (items = 7) and capacity(=15). so (7+1)*(15+1) = 8*16 matrix
        Integer profitMatrix[][] = new Integer[n+1][capacity+1];

        // initialize the matrix with -1
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=capacity; j++) {
                profitMatrix[i][j] = -1;
            }
        }
        return topDownKnapsack(weightArr,profitArr,profitMatrix, n,capacity);
    }

    /**
     * Using memoization (TopDown Approach)
     */
    public static Integer topDownKnapsack(int[] weightArr, int[] profitArr, Integer[][] profitMatrix,
                                          int noOfItems, int capacity) {
        int profitInclude=0;
        int profitExclude=0;
        // base case when noOfItems=0 || capacity = 0 - initialize the matrix
        if(noOfItems==0 || capacity==0) {
            return 0;
        }

        if(profitMatrix[noOfItems][capacity] != -1) {
            return profitMatrix[noOfItems][capacity];
        }

        if(profitMatrix[noOfItems][capacity] == -1) {
            if(capacity-weightArr[noOfItems-1] > 0) {
                profitInclude = profitArr[noOfItems - 1] + topDownKnapsack(weightArr, profitArr, profitMatrix,
                        noOfItems - 1, capacity - weightArr[noOfItems - 1]);
            } else {
                profitExclude = topDownKnapsack(weightArr,profitArr, profitMatrix,noOfItems-1, capacity);
            }
            profitMatrix[noOfItems][capacity] = Math.max(profitInclude,profitExclude);
        }
        return profitMatrix[noOfItems][capacity];
    }

    /**
     * Bottom Up Approach
     */
    public static Integer bottomUpKnapsack(int[] weightArr, int[] profitArr, int n, int capacity) {
        Integer[][] profitMatrix = new Integer[n+1][capacity+1];
        //decide the matrix size variable n(items) and capacity. matrix size = (n+1)*(capacity+1)
        // base case when n=0 or capacity = 0
        for(int i=0; i<=n; i++) {
            profitMatrix[i][0] = 0;
        }
        for(int j = 0; j <= capacity; j++) {
            profitMatrix[0][j] = 0;
        }

        for(int i = 1; i <=n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weightArr[i-1] > j) {
                    profitMatrix[i][j] = profitMatrix[i - 1][j];
                } else {
                    profitMatrix[i][j] = Math.max(profitArr[i-1] + profitMatrix[i - 1][j - weightArr[i - 1]], profitMatrix[i - 1][j]);
                }
            }
        }
        return profitMatrix[n][capacity];
    }
}
