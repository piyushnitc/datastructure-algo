package problems;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same. Then return the number of
 * unique elements in nums.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 *
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */

public class RemoveDuplicatesFromArray {

    public static void main(String args[]) {
        // approach 1 example
        int[] nums1 = {1,1,2};
        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};

        System.out.println(removeDuplicates(nums1));
        System.out.println(removeDuplicates(nums2));
    }

    /**
     * Approach 1 - iterate array and track the max since its a sorted array. return the max count
     * O(n) = n -- one full iteration of input array
     *
     * if i need to return the modified array - there are two options
     *  A) - create an empty array of input size array and keep adding elements
     *
     */
    public static int removeDuplicates(int[] nums) {
        //do empty check null array etc

        int uniqueCount = 0;
        int maxNumber = 0;
        int output[] = new int[nums.length];
        output[0] = maxNumber;

        for(int i =0; i< nums.length; i++) {
            if(i ==0) {
                uniqueCount++;
                maxNumber = nums[0];
            }

            if(nums[i] > maxNumber) {
                maxNumber = nums[i];
                output[uniqueCount]= maxNumber;
                uniqueCount++;
            }
        }
        System.out.println(Arrays.stream(output).toArray().toString());
        return uniqueCount;
    }

}
