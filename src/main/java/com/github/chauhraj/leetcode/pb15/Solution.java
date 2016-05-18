
package com.github.chauhraj.leetcode.pb15;

import static java.util.Arrays.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] input) {
        if(input.length < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> results = new LinkedList<>();
        sort(input);
        threeSum(input, results);
        return results;
    }

    private void threeSum(int[] A, List<List<Integer>> results) {
        int nonNegIdx = findFirstNonNegIdx(A);
        if(nonNegIdx == 0) {
            // there are no negative numbers. So the rest of the numbers are >= 0
            // So only possible solution is when a[0] = a[1] = a[2] = 0
            if(A[0] == 0 && A[0] == A[1] && A[1] == A[2]) {
                results.add(l(0, 0, 0));
                return;
            }
        }
        for(int i = nonNegIdx; i < A.length; i++) {
            for(int j = nonNegIdx - 1; j >= 0; j--) {
                int sum = (A[i] + A[j]);
                if(sum < 0) {
                    //+# < -# for eg => (2, -4)
                    if(-sum > A[i]) {
                        int idx = binarySearch(A, i + 1, A.length, -sum);
                        if (idx >= 0) {
                            // found the solution....
                            results.add(l(A[j], A[i], A[idx]));
                        }
                    } else if(-sum == A[i] && i != A.length - 1) {
                        results.add(l(A[j], A[i], A[i]));
                        break;
                    }
                } else {
                    //+# > -# for eg => (2, -1)
                    if(-sum < A[j]) {
                        int idx = binarySearch(A, 0, j, -sum);
                        if (idx >= 0) {
                            // found the solution....
                            results.add(l(A[idx], A[j], A[i]));
                            break;
                        }
                    }else if(-sum == A[j] && j != 0) {
                        results.add(l(A[j], A[j], A[i]));
                        break;
                    }

                }
                while(j > 0 && A[j] == A[j - 1]) j--;
            }
            while(i < A.length - 1 && A[i] == A[i + 1]) i--;
        }
    }

    private List<Integer> l(Integer... az) {
        return asList(az);
    }

    private int findFirstNonNegIdx(int[] A) {
        int nonNegIdx = binarySearch(A, 0);
        if(nonNegIdx < 0) {
            // Didn't find 0 and it doesn't matter because then it will return the index of the lowest +ve number
            nonNegIdx = -(nonNegIdx + 1);
        } else {
            while(nonNegIdx > 0 && A[nonNegIdx] == A[nonNegIdx - 1]) nonNegIdx--;
        }
        return nonNegIdx;
    }
}
        