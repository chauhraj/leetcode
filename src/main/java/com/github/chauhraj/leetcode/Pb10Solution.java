package com.github.chauhraj.leetcode;

import static com.github.chauhraj.Utils.deepToString;

public class Pb10Solution {

    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        //populate the first row when empty string matches the pattern 0..i
        for (int i = 1; i <= p.length(); i++) {
            match[0][i] = i > 1 && p.charAt(i - 1) == '*' ? match[0][i - 2]: false;
        }

        for(int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    match[i][j] = match[i-1][j-1];
                } else if(j > 1 && p.charAt(j - 1) == '*') {
                    boolean m = match[i][j - 2]; // matches 0 of the character of a* pattern
                    if(p.charAt(j -2) == '.') {
                        m |= match[i - 1][j] || match[i][j - 1];
                    } else if(p.charAt(j -2) == s.charAt(i - 1)) {
                        m |= match[i - 1][j - 1] || match[i][j - 1];
                    }
//                    else if(i > 1 && p.charAt(j -2) != s.charAt(i - 1)) {
//                        m |= match[i - 1][j - 2];
//                    }
                    match[i][j] = m;
                }
            }
        }
        System.out.println(deepToString(match));
        return match[s.length()][p.length()];
    }

}
        