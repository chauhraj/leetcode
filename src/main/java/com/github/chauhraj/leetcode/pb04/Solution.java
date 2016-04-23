package com.github.chauhraj.leetcode.pb04;

import static java.lang.Math.*;

class Solution {

    double findMedianSortedArrays(int[] A, int[] B) {
        int total = (A.length + B.length + 1) / 2;
        if(A.length == 0 && B.length == 0) {
            return Double.NaN;
        } else if(A.length > B.length) {
            return findMedian(B, A, 0, B.length, total);
        } else {
            return findMedian(A, B, 0, A.length, total);
        }
    }

    private double findMedian(int[] A, int[] B, int l, int r, int total) {
        int i = (l + r) / 2;
        int j = total - i;
        int n = B.length;
        int m = A.length;
        if(j > 0 && i < m && B[j - 1] > A[i]) {
            return findMedian(A, B, i + 1, r, total);
        } else {
            if(i > 0 && j < n && A[i - 1] > B[j]) {
                return findMedian(A, B, l, i, total);
            } else {
                int maxLeft;
                if(i == 0) {
                    maxLeft = B[j -1];
                } else if(j == 0) {
                    maxLeft = A[i -1];
                } else {
                    maxLeft = max(A[i -1], B[j -1]);
                }

                if((m + n) % 2 == 1) {
                    System.out.printf("(%s)\n", maxLeft);
                    return maxLeft;
                } else {
                    int minRight;
                    if(i == m) {
                        minRight = B[j];
                    } else if(j == n) {
                        minRight = A[i];
                    } else {
                        minRight = min(A[i], B[j]);
                    }
                    System.out.printf("(%s, %s)\n", maxLeft, minRight);
                    return (maxLeft + minRight) * 0.5;
                }

            }
        }

    }
}
        