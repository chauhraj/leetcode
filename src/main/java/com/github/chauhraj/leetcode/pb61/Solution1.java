package com.github.chauhraj.leetcode.pb61;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {

    private static Character SPC = ' ';
    private static Character DOT = '.';
    private static Character E = 'e';
    private static Character PLUS = '+';
    private static Character MINUS = '-';
    private boolean hasSeenNumber;


    public boolean isNumber (String s) {
        String leftOver = s.trim();
        if (leftOver.length() == 0) {
            return false;
        } else {
            Set<Character> disallowed = new HashSet<>();
            disallowed.add(SPC);
            char[] chars = leftOver.toCharArray();
            if(chars.length == 1) {
                return isNumber(chars[0]);
            } else {
                if (chars[0] == E) {
                    return false;
                } else if (chars[chars.length - 1] == MINUS || chars[chars.length - 1] == PLUS || chars[chars.length - 1] == E) {
                    return false;
                } else {
                    return isValidNumber(chars, 0, disallowed);
                }
            }
        }
    }

    private boolean isValidNumber(char[] chars, int currentIdx, Set<Character> disallowed) {
        char current = chars[currentIdx];
        if(currentIdx == chars.length - 1) {
            if(disallowed.contains(DOT) && current == DOT) {
                return false;
            } else if(!disallowed.contains(DOT) && current == DOT) {
                return hasSeenNumber;
            } else if(Character.isDigit(current)) {
                return true;
            } else {
                return false;
            }
        } else {
            if(disallowed.contains(current)) {
                return false;
            } else {
                if (isNumber(current)) {
                    hasSeenNumber = true;
                    return isValidNumber(chars, currentIdx + 1, disallowed);
                } else if (current == '.') {
                    disallowed.add(DOT);
                    return isValidNumber(chars, currentIdx + 1, disallowed);
                } else if (current == PLUS || current == MINUS) {
                    if(currentIdx > 0 && chars[currentIdx - 1] == E) {
                        disallowed.add(current);
                        return isValidNumber(chars, currentIdx + 1, disallowed);
                    } else if(currentIdx == 0) {
                        return isValidNumber(chars, currentIdx + 1, disallowed);
                    } else {
                        return false;
                    }
                } else if (current == E) {
                    if(!hasSeenNumber) {
                        return false;
                    } else {
                        disallowed.add(E);
                        disallowed.add(DOT);
                        return isValidNumber(chars, currentIdx + 1, disallowed);
                    }
                } else {
                    return false;
                }
            }
        }
    }

    boolean isNumber(char current) {
        return Character.isDigit(current);
    }

}
