package dynamicprogramming;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String[] X = { "E", "B", "T", "B", "C", "A", "D", "F" };
        String[] Y = { "A", "B", "B", "C", "D", "6", "F" };

        /**
         * Brute force recursion method
         * Find all the subsequence in X and all the subsequence in Y and compare the subsequences and return the largest
         * Time complexity for X - there are m elements so there are 2^m combinations
         * Similarly for Y - there are n elements and hence there are 2^n combinations
         * Hence TC = 0(2^m+2^n)
         * Base case when m=0 or n=0
         *
         * If the last element of both the string array are same then that would be the part of the LCs
         * If the last element of both the string array are not same then we need to compare X-1 with Y or Y-1 with X
         *
         * Let's define a function cs which take the following arguments.
         * m = no of elements in X
         * n = no of elements in Y
         * */
        int maxCount = lcs(X, Y, X.length - 1, Y.length - 1);
        System.out.println("maxcount from recursion-----" + maxCount);

        int maxCountFromTopDown = topDown(X, Y, X.length - 1, Y.length - 1);
        System.out.println("maxCountFromTopDown-----" + maxCountFromTopDown);

        int maxCountFromBottomUp = bottomUpLCS(X, Y, X.length - 1, Y.length - 1);
        System.out.println("maxCountFromBottomUp----" + maxCountFromBottomUp);
    }

    /**
     * This is recursion method
     *
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    public static int lcs(String[] X, String[] Y, Integer m, Integer n) {
        int maxCount = 0;
        if ((m == 0) || (n == 0)) {
            return maxCount = 0;
        }

        if (X[m] == Y[n]) {
            maxCount = 1 + lcs(X, Y, m - 1, n - 1);
        }

        if (X[m] != Y[n]) {
            // compare X with Y-1 elements|
            int excludedFrom = lcs(X, Y, m - 1, n);
            int excludedFromY = lcs(X, Y, m, n - 1);

            maxCount = Math.max(excludedFrom, excludedFromY);
        }
        return maxCount;
    }


    private static int topDown(String[] X, String[] Y, int m, int n) {
        Integer[][] Z = new Integer[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Z[i][j] = -1;
            }
        }
        return topDownLCS(X, Y, Z, m, n);
    }

    /**
     * Lets solve this by Dynamic Programming using topDown approach.
     * in order to solve this by memoization - lets build the solution approach. We saw in recursion that the following choices are possible
     * 1. if last elements in both the array are matched maxCount = 1 + lcs(X, Y, m-1, n-1)
     * 2. if last elements in both the array are not matched then we are getting the max of
     * 1. Les (X, Y,m-1, n) and lcs (X, Y,m, n- 1)
     * 2. So for any i in X and any jin Y the function would become
     * Lcs (X, Y, 1-1, j) and Lc(X,Y, i,j-1)
     * <p>
     * 3. This means to calculate the value of any i in X and any j in Y, all
     * We
     * need is the values from (1-1, j) and (i, j-1) so that we can get the maximum
     * 1. Initialize an array of size (m+1)*(n+1) where there are m index in X and n index in Y
     * 2. Initialize all values with -1. If the value is -1, we need to calculate or else just get the value and compare
     * 3. since we are storing solutions for sub-problems - it will drastically reduce the time complexity as we are calculating sub problems only once
     * 4. TC = 0((m+1)*(n+1)) as there are m+1 * n+1 different sub-problems
     * 5. Base case i = 0 or j=0 return 0
     */

    public static int topDownLCS(String[] X, String[] Y, Integer[][] Z, int m, int n) {
        if ((m == 0) || (n == 0)) {
            return 0;
        }
// if the value is present in the Z[][] return else go ahead and calculate
        if (Z[m][n] != -1) {
            return Z[m][n];
        }
        if (X[m] == Y[n]) {
            Z[m][n] = 1 + topDownLCS(X, Y, Z, m - 1, n - 1);
        }
        if (X[m] != Y[n]) {
            Z[m][n] = Math.max(topDownLCS(X, Y, Z, m - 1, n), topDownLCS(X, Y, Z, m, n - 1));
        }
        return Z[m][n];
    }


    /**
     * This is bottopm up approach
     * @param X
     * @param Y
     * @param m
     * @param n
     * @return
     */
    public static int bottomUpLCS(String[] X, String[] Y, int m, int n) {
        Integer[][] Z = new Integer[m + 1][n + 1];
        //Initialize the matrix Z in the base condition m=0 or n=0
        for (int i = 0; i <= m; i++) {
            Z[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            Z[0][j] = 0;
        }
        // for every i and j, get the value form previous column
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // if last character matches
                if (X[i] == Y[j]) {
                    Z[i][j] = 1 + Z[i - 1][j - 1];
                }
                // if last character does not match
                if (X[i] != Y[j]) {
                    Z[i][j] = Math.max(Z[i - 1][j], Z[i][j - 1]);
                }
            }
        }
        return Z[m][n];
    }
}