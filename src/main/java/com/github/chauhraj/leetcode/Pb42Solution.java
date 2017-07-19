
package com.github.chauhraj.leetcode;

import static java.lang.Integer.max;

public class Pb42Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = height[left++], rightMax = height[right--];

        int water = 0, t  = 0;
        while(left <= right) {
            if(leftMax <= rightMax){
                if(height[left] < leftMax) {
                    t += (leftMax - height[left]);
                } else {
                    leftMax = max(height[left], leftMax);
                    water += t;
                    t = 0;
                }
                left++;
            } else {
                if(height[right] < rightMax) {
                    t += (rightMax - height[right]);
                } else {
                    rightMax = max(height[right], rightMax);
                    water += t;
                    t = 0;
                }
                right--;
            }
        }


        return water + t;
    }
}
        