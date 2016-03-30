
package com.github.chauhraj.leetcode.pb198;

public class Solution {

    public int rob(int[] cashPerHouse) {
        if(cashPerHouse.length == 0) {
            return 0;
        } else if(cashPerHouse.length == 1) {
            return cashPerHouse[0];
        } else {
            int[] maxGain = new int[cashPerHouse.length];
            maxGain[0] = cashPerHouse[0];
            maxGain[1] = cashPerHouse[1];
            for(int h = 2; h < cashPerHouse.length; h++) {
                maxGain[h] = cashPerHouse[h] + Math.max(maxGain[h - 2], h > 2 ? maxGain[h - 3] : 0);
            }
            return Math.max(maxGain[cashPerHouse.length - 1], maxGain[cashPerHouse.length - 2]);
        }
    }


}
        