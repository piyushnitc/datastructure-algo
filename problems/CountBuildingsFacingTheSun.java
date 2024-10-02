package problems;

/**
 * Given an input array height[] representing the heights of buildings, write a program to count the number of buildings
 * facing the sunset. It is assumed that the heights of all buildings are distinct.
 * Examples
 *
 * Input: height[] = [7, 4, 8, 2, 9], Output: 3
 * Explanation: As 7 is the first element, it can see the sunset. Similarly, 8 and 9 can see the sunset. 4 and 2 can't
 * see the sunset because 7 and 8 are hiding it.
 *
 * Input: height[] = [2, 3, 4, 5], Output: 4
 */


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Approach Discussion
 *
 * 1. with single loop- find the first element. Assign this first element to currentMax.
 * if the next element is greater than currentMax then add to the count variable. Also, update the current max to this new element.
 * if the next element is smaller than currentMax then skip
 * finally return the count.
 *
 * Time Complexity = O(n) as we are traversing the entire loop.
 */
public class CountBuildingsFacingTheSun {

    public static void main (String args[]) {
        int height[] = {7, 7, 4, 8, 2, 9};
        System.out.println(facingSunCount(height));
        System.out.println(facingSunBuildings(height).stream().collect(Collectors.toList()));
    }

    /**
     * returns the count of buildings
     * @param height
     * @return
     */
    public static int facingSunCount(int height[]) {
        //use non null array check
        int currentMax = height[0];
        int buildingCount = 1;
        for(int i=1; i<height.length; i++) {
            if(currentMax <= height[i]) {
                buildingCount++;
                currentMax = height[i];
            }
        }
        return buildingCount;
    }

    /**
     * returns the list of buildings assuming same height will not block the sun.
     * @param height
     * @return
     */
    public static List<Integer> facingSunBuildings(int height[]) {
        //use non null array check
        int currentMax = height[0];

        List<Integer> buildings = new ArrayList<Integer>();
        buildings.add(height[0]);

        for(int i=1; i<height.length; i++) {
            if(currentMax <= height[i]) {
                buildings.add(height[i]);
                currentMax = height[i];
            }
        }
        return buildings;
    }
}
