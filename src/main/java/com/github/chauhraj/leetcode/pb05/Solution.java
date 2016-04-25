
package com.github.chauhraj.leetcode.pb05;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    public String longestPalindrome(String s) {
        if (s.isEmpty()) return "";
        if (s.length() == 1) return s;
        int min_start = 0, max_len = 1;
        for (int i = 0; i < s.length(); ) {
            if (s.length() - i <= max_len / 2) break;
            int j = i, k = i;
            while (k < s.length() - 1 && s.charAt(k + 1) == s.charAt(k)) ++k; // Skip duplicate characters.
            i = k + 1;
            while (k < s.length() - 1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
                ++k;
                --j;
            } // Expand.
            int new_len = k - j + 1;
            if (new_len > max_len) {
                min_start = j;
                max_len = new_len;
            }
        }
        return s.substring(min_start, min_start + max_len);
    }

    public String longestPalindrome0(String input) {
        char[] cs = input.toCharArray();

        int maxLength = 1, max1 = 0;
        AtomicInteger memorizer = new AtomicInteger(1);
        for(int i = 0; i < cs.length; i++) {
            int maxPossibleLength = (cs.length - i);
            if(maxPossibleLength < maxLength) {
                break;
            }
            for(int j = cs.length - 1; j >= i; j -= memorizer.get()) {
                int possibleLength = (j - i + 1);
                if(possibleLength < maxLength) {
                    break;
                }
                if(isPalindrome(cs, i, j, memorizer)) {
                    if(possibleLength > maxLength) {
                        maxLength = possibleLength;
                        max1 = i;
                        break;
                    }
                }

            }
        }
        return new String(cs, max1, maxLength);
    }

    private boolean isPalindrome(char[] cs, int start, int end, AtomicInteger memorizer) {
        int endl = end;
        while (start <= end) {
            if (cs[start++] == cs[end--]) {
                continue;
            } else {
                if(endl - 1 == end) {
                    memorizer.set(endl - end);
                } else {
                    memorizer.set(endl - end - 1);
                }
                return false;
            }
        }
        return true;
    }
}
        