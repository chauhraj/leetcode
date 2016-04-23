
package com.github.chauhraj.leetcode.pb05;

public class Solution {
    public int reverse(int x) {
        int sign = (x < 0) ? -1 : 1;
        int n = sign * x;
        int result = 0;
        while(n != 0) {
            int factor = Integer.MAX_VALUE / (result == 0 ? 1 : result);
            if(factor >= 10) {
                result = (result * 10) + n % 10;
            } else {
                result = 0;
                break;
            }
            n = n / 10;
        }
        return sign * result;

    }
}
        