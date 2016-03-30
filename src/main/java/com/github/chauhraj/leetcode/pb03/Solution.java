package com.github.chauhraj.leetcode.pb03;

import java.util.HashMap;
import java.util.Map;

class Solution {

    private Map<Character, Integer> map = new HashMap<>();

    int lengthOfLongestSubstring(String s) {
        int max = 0, startOfRun = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                int lastIndex = map.put(c, i);
                if(startOfRun <= lastIndex) {
                    if((i - startOfRun) > max) {
                        max = (i - startOfRun);
                    }
                    startOfRun = lastIndex + 1;
                }
            } else {
                map.put(c, i);
            }
        }
        if(s.length() - startOfRun > max) {
            max = s.length() - startOfRun;
        }
        return max;
    }

}
        