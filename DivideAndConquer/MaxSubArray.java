package DivideAndConquer;

/**
 * Find the max sub array using
 * 1. two nested loops
 * 2. Divide and Conquer
 * 3. Dynamic Programming
 */
public class MaxSubArray {
    public static void main (String args[]) {
        int[] inputArray = {-4, 5, 7, -6, 10, -15, 3};
        bruteForceApproach(inputArray);
    }

    /**
     * Two nested loops (brute force approach
     * @param arr
     */
    public static void bruteForceApproach(int[] arr) {
        int maxSum = arr[0];
        for (int startingIndex=0; startingIndex<arr.length; startingIndex++) {
            int currentSum = arr[startingIndex];
            for(int endingIndex=startingIndex+1; endingIndex<arr.length; endingIndex++) {
                currentSum = currentSum+arr[endingIndex];
                if(currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        System.out.println("Brute Force Max Sum " +maxSum);
    }

    /**
     * Merge Sort Approach
     * Divide the
     * @param arr
     */
    public static void mergeSortApproach(int[] arr) {
        int maxSum = arr[0];
        for (int startingIndex=0; startingIndex<arr.length; startingIndex++) {
            int currentSum = arr[startingIndex];
            for(int endingIndex=startingIndex+1; endingIndex<arr.length; endingIndex++) {
                currentSum = currentSum+arr[endingIndex];
                if(currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        System.out.println("Brute Force Max Sum " +maxSum);
    }

    /**
     * Two nested loops (brute force approach
     * @param arr
     */
    public static void dynamicProgrammingApproach(int[] arr) {
        int maxSum = arr[0];
        for (int startingIndex=0; startingIndex<arr.length; startingIndex++) {
            int currentSum = arr[startingIndex];
            for(int endingIndex=startingIndex+1; endingIndex<arr.length; endingIndex++) {
                currentSum = currentSum+arr[endingIndex];
                if(currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        System.out.println("Brute Force Max Sum " +maxSum);
    }
}

