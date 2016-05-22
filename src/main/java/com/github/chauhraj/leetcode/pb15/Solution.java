package com.github.chauhraj.leetcode.pb15;
import static java.util.Arrays.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] input) {
        if(input.length < 3) {
            return Collections.emptyList();
        } else if(input.length == 3) {
            int sum = input[0] + input[1] + input[2];
            if(sum == 0) {
                sort(input);
                List<List<Integer>> result = new LinkedList<>();
                List<Integer> l = new LinkedList<>();
                l.add(input[0]);
                l.add(input[1]);
                l.add(input[2]);
                result.add(l);
                return result;
            } else {
                return Collections.emptyList();
            }
        } else {
            List<List<Integer>> results = new LinkedList<>();
            sort(input);
            threeSum(input, results);
            return results;
        }
    }

    private void threeSum(int[] A, List<List<Integer>> results) {
        int nonNegIdx = binarySearch0(A, 0, A.length - 1, 0);
        if(nonNegIdx >= A.length) {
            return ;
        }
        int negRegionBoundIdx = nonNegIdx, posRegionBoundaryIdx = nonNegIdx;
        if(A[nonNegIdx] == 0) {
            while(posRegionBoundaryIdx < A.length && A[posRegionBoundaryIdx] == 0) posRegionBoundaryIdx++; posRegionBoundaryIdx--;
            while(negRegionBoundIdx >= 0 && A[negRegionBoundIdx] == 0) negRegionBoundIdx--; negRegionBoundIdx++;
        }
        findSolutionsOnNegativePartition(A, negRegionBoundIdx, results);
        if((posRegionBoundaryIdx - negRegionBoundIdx + 1) >= 3) {
            List<Integer> l = new LinkedList<>();
            l.add(0);
            l.add(0);
            l.add(0);
            results.add(l);
        }
        findSolutionsOnPositivePartition(A, posRegionBoundaryIdx, results);
    }

    private void findSolutionsOnNegativePartition(int[] A, int boundaryIdx, List<List<Integer>> results) {
        if(A[0] >= 0) {
            return;
        }
        int solnSpcStartIdx = boundaryIdx,
                solnSpcEndIdx = A.length - 1;
        for(int i = 0; i < boundaryIdx; i++) {
            while(i < boundaryIdx - 1 && A[i+1] == A[i]) i++;
            int current = A[i],
                    keyIndex = binarySearch0(A, solnSpcStartIdx, solnSpcEndIdx, -current),
                    firstIndex = solnSpcStartIdx,
                    lastIndex = Integer.min(keyIndex, A.length - 1);
            solnSpcEndIdx = lastIndex;
            while(firstIndex < lastIndex) {
                int sum = A[firstIndex] + A[lastIndex] + current;
                if(sum == 0) {
                    List<Integer> l = new LinkedList<>();
                    l.add(current);
                    l.add(A[firstIndex]);
                    l.add(A[lastIndex]);
                    results.add(l);
                    while(firstIndex < lastIndex - 1 && A[firstIndex] == A[firstIndex + 1]) firstIndex++;
                    firstIndex++;
                } else if(sum > 0) {
                    while(lastIndex > firstIndex + 1 && A[lastIndex] == A[lastIndex - 1]) lastIndex--;
                    lastIndex--;
                } else {
                    while(firstIndex < lastIndex - 1 && A[firstIndex] == A[firstIndex + 1]) firstIndex++;
                    firstIndex++;
                }
            }
        }
    }
    private void findSolutionsOnPositivePartition(int[] A, int boundaryIdx, List<List<Integer>> results) {
        int solnSpcStartIdx = 0,
                solnSpcEndIdx = boundaryIdx;
        for(int i = A.length - 1; i >= boundaryIdx; i--) {
            while(i > boundaryIdx - 1 && A[i] == A[i - 1]) i--;
            int current = A[i];
            int keyIndex = binarySearch0(A, solnSpcStartIdx, solnSpcEndIdx, -current),
                    lastIndex = boundaryIdx - 1,
                    firstIndex = keyIndex;
            solnSpcStartIdx = keyIndex;
            if(A[firstIndex] == -current) {
                while(firstIndex < boundaryIdx - 1 && A[firstIndex] == A[firstIndex + 1]) firstIndex++;
                firstIndex++;
            }
            while(firstIndex < lastIndex) {
                int sum = A[firstIndex] + A[lastIndex] + current;
                if(sum == 0) {
                    List<Integer> l = new LinkedList<>();
                    l.add(A[firstIndex]);
                    l.add(A[lastIndex]);
                    l.add(current);
                    results.add(l);
                    while(lastIndex > firstIndex + 1 && A[lastIndex] == A[lastIndex - 1]) lastIndex--;
                    lastIndex--;
                } else if(sum > 0) {
                    while(lastIndex > firstIndex + 1 && A[lastIndex] == A[lastIndex - 1]) lastIndex--;
                    lastIndex--;
                } else {
                    while(firstIndex < lastIndex - 1 && A[firstIndex] == A[firstIndex + 1]) firstIndex++;
                    firstIndex++;
                }
            }
        }
    }

    private int binarySearch0(int[] A, int solnSpcStartIdx, int solnSpcEndIdx, int key) {
        int index = binarySearch(A, solnSpcStartIdx, solnSpcEndIdx + 1, key);
        if(index < 0) {
            index += 1;
            index = -index;
        }
        return index;
    }

}
