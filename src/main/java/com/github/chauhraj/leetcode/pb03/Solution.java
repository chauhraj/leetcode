package com.github.chauhraj.leetcode.pb03;

class Solution {

    int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int i = 1, max = 0, startOfRun = 1;
        for(char c : s.toCharArray()) {
            if(startOfRun <= map[c]) {
                if((i - startOfRun) > max) {
                    max = (i - startOfRun);
                }
                startOfRun = map[c] + 1;
            }
            map[c] = i++;
        }
        if((s.length() - startOfRun + 1) > max) {
            max = s.length() - startOfRun + 1;
        }
        return max;
    }

}