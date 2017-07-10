package com.github.chauhraj.leetcode.pb44;

public class Solution {

    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        //populate the first row when empty string matches the pattern 0..i
        for (int i = 1; i <= p.length(); i++) {
            match[0][i] = p.charAt(i - 1) == '*' ? match[0][i - 1]: false;
        }

        for(int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    match[i][j] = match[i-1][j-1];
                } else if(p.charAt(j - 1) == '*') {
                    match[i][j] = match[i-1][j] || match[i][j - 1];
                }
            }
        }
        return match[s.length()][p.length()];
    }

}
        