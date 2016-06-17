
package com.github.chauhraj.leetcode.pb11;

import static java.lang.Math.*;

public class Solution {
    public int maxArea(int[] height) {
        int currentMax = Integer.MIN_VALUE;
        int l = 0, r = height.length - 1, lr = r, ll = 0, lastMinHeight = Integer.MIN_VALUE;
        while(l < r) {
            int minHeight = min(height[l], height[r]);
            if(minHeight > lastMinHeight) {
                int w = minHeight * (r - l);
                lastMinHeight = minHeight;
                if(currentMax < w) {
                    currentMax = w;
                    lr = r;
                    ll = l;
                }

            }
            if(height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return currentMax;
    }
}
        