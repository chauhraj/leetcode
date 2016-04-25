package com.github.chauhraj.leetcode.pb05;

/**
 * Created by chauhraj on 4/24/16.
 */
public class Manacher {

    String longestPalindrome(String s) {
        char[] input = s.toCharArray();
        char[] T = preprocess(input);
        int[] p = new int[T.length];
        int c = 0, r = 0;
        for(int i = 1; i < T.length - 1; i++) {
            int mirror = 2*c - i;
            p[i] = (r > i) ? Math.min(r-i, p[mirror]) : 0;

            while(T[i + p[i] + 1] == T[i - p[i] - 1]) {
                p[i] = p[i] + 1;
            }
            if(i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }

        int maxLength = Integer.MIN_VALUE;
        int cOfMax = -1;
        for(int i = 0; i < p.length; i++) {
            if(p[i] > maxLength) {
                maxLength = p[i];
                cOfMax = i;
            }
        }

        return new String(input, (cOfMax - 1 - maxLength)/2, maxLength);
    }

    public static void main(String[] args) {
        Manacher m = new Manacher();

        System.out.println("Longest palindrome is:" + m.longestPalindrome("aaabaaaa"));

    }

    private char[] preprocess(char[] s) {
        char[] input = new char[2*s.length + 1 + 2];
        input[0] = '^';
        input[1] = '#';
        input[input.length - 1] = '$';
         for(int i = 0; i < s.length; i++) {
             input[2*i+2] = s[i];
             input[2*i+3] = '#';
         }
        return input;
    }
}
