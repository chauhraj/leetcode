package com.github.chauhraj.leetcode.pb02;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       if(l1 == null) {
           return l2;
       } else if (l2 == null) {
           return l1;
       } else if(l1 == null && l2 == null) {
           return null;
       } else {
           int carry = 0;

           ListNode head = null, current = null;
           while (l1 != null || l2 != null) {
               int sum = getData(l1) + getData(l2) + carry;
               if (sum >= 10) {
                   carry = (sum >= 10) ? 1 : 0;
                   sum = sum - 10;
               } else {
                   carry = 0;
               }
               ListNode temp;
               if (head == null) {
                   temp = new ListNode(sum);
                   head = temp;
                   current = head;
               } else {
                   temp = new ListNode(sum);
                   current.next = temp;
                   current = temp;
               }
               l1 = next(l1);
               l2 = next(l2);
           }

           if (carry == 1) {
               current.next = new ListNode(carry);
           }
           return head;
       }
    }

    private int getData(ListNode l) {
        if(l == null) {
            return 0;
        } else {
            return l.val;
        }
    }

    private ListNode next(ListNode l) {
        if(l == null) {
            return null;
        } else {
            return l.next;
        }
    }

}