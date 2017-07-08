package com.github.chauhraj.codefight;


import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by chauhraj on 5/13/17.
 */
public class FindContiguousSubArraySum {

    public static void main(String[] args) {
        FindContiguousSubArraySum sut = new FindContiguousSubArraySum();

        int[] tc1 = {135, 101, 170, 125, 79, 159, 163, 65, 106, 146, 82, 28, 162, 92, 196, 143, 28, 37, 192, 5, 103, 154, 93, 183, 22, 117, 119, 96, 48, 127, 172, 139, 70, 113, 68, 100, 36, 95, 104, 12, 123, 134};

        int[] actuals = sut.findSubarrayBySum(468, tc1);
        assertArrayEquals(new int[]{38, 42}, actuals);

        int[] tc2 = {142, 112, 54, 69, 148, 45, 63, 158, 38, 60, 124, 142, 130, 179, 117, 36, 191, 43, 89, 107, 41, 143, 65, 49, 47, 6, 91, 130, 171, 151, 7, 102, 194, 149, 30, 24, 85, 155, 157, 41, 167, 177, 132, 109, 145, 40, 27, 124, 138, 139, 119, 83, 130, 142, 34, 116, 40, 59, 105, 131, 178, 107, 74, 187, 22, 146, 125, 73, 71, 30, 178, 174, 98, 113};
        actuals = sut.findSubarrayBySum(665, tc1);
        assertArrayEquals(new int[]{-1}, actuals);
    }

    int[] findSubarrayBySum(int s, int[] arr) {

        // Keep the two pointers marking a slice of an array whose edges are defined by
        // left and right. sum(left.. right) = s // return left, right
        //                                   < s // extend the right boundary.
        //                                   > s // since the sum overshoot, this mean
        //                                          // left..right cannot be Boolean
        int left = 0, right = 0, sum = 0;
        // System.out.printf("[%d, %d = %d]%n", left, right, sum);
        while(left <= right) {
            if(sum < s) {
                if(right < arr.length) {
                    sum += arr[right++];
                } else {
                    break;
                }
            } else if(sum == s) {
                return new int[]{left + 1, right};
            } else {
                sum -= arr[left++];
            }
            // System.out.printf("[%d, %d = %d]%n", left, right, sum);
        }
        return new int[]{-1};
    }
}
