package com.github.chauhraj.leetcode.pb322;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        //if(amount == 0) return 0;
        //sort(coins);
        //if(amount < coins[0]) return -1;
        int[] A = new int[amount + 1];
        fill(A, -1); A[0] = 0;
        for (int c : coins) if (c <= amount) A[c] = 1;
        for (int a = 1; a <= amount; a++) {
            int currentMin = MAX_VALUE;
            for (int c : coins) {
                int v = a - c;
                if (v >= 0 && A[v] >= 0) {
                    // if coin doesn't exist then that A[v] = -1
                    int newValue = A[c] + A[a - c];
                    if (newValue < currentMin) {
                        currentMin = newValue;
                    }
                }
            }
            if(currentMin != MAX_VALUE) {
                A[a] = currentMin;
            }
        }
        return A[amount];
    }
}
        