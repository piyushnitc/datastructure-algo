package problems;

/**
 * Given an integer array X[] of size n, write a program to find all the leaders in the array X[]. An element is a
 * leader if it is strictly greater than all the elements to its right side.
 *
 * The last element of an array is a leader by default.
 * The largest element of an array is also a leader by default.
 * Suppose all the array elements are unique.
 * Ordering in the output doesn’t matter.
 *
 * Example 1
 * Input: X[] = [16, 17, 4, 3, 5, 2], Output: [17, 5, 2]
 * Explanation: Element 2 is the rightmost element, so it is a leader by default. 17 and 5 are also leader elements
 * because they are greater than all the elements on their right side.
 *
 * Example 2
 * Input: X[] = [6, 5, 4, 3, 2, 1], Output: [6, 5, 4, 3, 2, 1]
 * Explanation: All elements are leaders because they are greater than all the elements on their right side.
 * Element 1 is the rightmost element, so it is a leader by default.
 *
 * Example 3
 * Input: X[] = [1, 2, 3, 4, 5, 6], Output: [6]
 * Explanation: Element 6 is the rightmost element, which is a leader by default. Otherwise, all elements are less than
 * all on their right side.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Approach Discussion
 * 1. Brute Force - Compare each element against all the elements to the right side. T.C = n * (n-1)
 *
 * 2. Scan the element from right side. Add the first element in the output and track maximum so far.
 *    If the next element is greater than maximum - update maximum and add the element in the output
 */
public class LeadersInArray {


    public static void main(String args[]) {
        int array1[] = {6,5,4,3,2,1};
        System.out.println(getLeaders(array1).stream().collect(Collectors.toList()));
        int array2[] = {1,2,3,4,5,6};
        System.out.println(getLeaders(array2).stream().collect(Collectors.toList()));
        int array3[] = {16, 17, 4, 3, 5, 2};
        System.out.println(getLeaders(array3).stream().collect(Collectors.toList()));

        int array4[] = {6,5,4,3,2,1};
        System.out.println(getLeadersScanRight(array4).stream().collect(Collectors.toList()));
        int array5[] = {1,2,3,4,5,6};
        System.out.println(getLeadersScanRight(array5).stream().collect(Collectors.toList()));
        int array6[] = {16, 17, 4, 3, 5, 2};
        System.out.println(getLeadersScanRight(array6).stream().collect(Collectors.toList()));
    }


    public static List<Integer> getLeaders(int array[]){
        ArrayList<Integer> leaderList = new ArrayList<Integer>();
        for(int i=0; i<array.length; i++) {
            boolean addToList = true;
            for(int j=i+1; j<array.length; j++) {
                if(array[i] < array[j]) {
                    addToList = false;
                    break;
                }
            }
            if(addToList && i < array.length-1) {
                leaderList.add(array[i]);
            }
        }
        leaderList.add(array[array.length-1]);
        return leaderList;
    }

    public static List<Integer> getLeadersScanRight(int array[]){
        var n = array.length;
        var max = array[n-1];
        ArrayList<Integer> leaders = new ArrayList<Integer>();
        leaders.add(max);

        for(int i=n-2; i>-1; i--) {
            if(array[i] > max) {
                leaders.add(array[i]);
                max = array[i];
            }
        }
        return leaders;
    }
}
