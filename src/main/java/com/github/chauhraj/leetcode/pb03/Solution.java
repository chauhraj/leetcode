package com.github.chauhraj.leetcode.pb03;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Character, Integer> map = new HashMap<>();

    public int lengthOfLongestSubstring(String s) {
        int max = 0, count = 0, startOfRun = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                int lastIndex = map.put(c, i);
                if(startOfRun <= lastIndex) {
                    count = (i - lastIndex);
                    if(count > max) {
                        max = count;
                    }
                    startOfRun = i;
                } else {
                    count++;
                }
            } else {
                map.put(c, i);
                count++;
            }
        }
        if(count > max) {
            max = count;
        }
        return max;
    }

}
        