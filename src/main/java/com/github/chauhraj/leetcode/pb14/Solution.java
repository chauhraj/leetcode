package com.github.chauhraj.leetcode.pb14;

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }

        char[] leastCommon = strs[0].toCharArray();
        int length = leastCommon.length;
        for(int i = 1; i < strs.length; i++) {
            int j = 0;

            String s = strs[i];
            int iLen = s.length();
            length = Math.min(iLen, length);
            while(j < length && leastCommon[j] == s.charAt(j)){
                j++;
            }
            length = j;
        }

        return new String(leastCommon, 0, length);
    }


}