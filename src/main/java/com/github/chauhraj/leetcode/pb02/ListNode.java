package com.github.chauhraj.leetcode.pb02;

/**
 * Created by chauhraj on 3/27/16.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        if(next == null) {
            return "[" + val + "]";
        } else {
            return "" + val + ", " + next;
        }
    }
}
