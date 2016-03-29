package com.github.chauhraj.leetcode.pb02;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by chauhraj on 3/27/16.
 */
@RunWith(Parameterized.class)
public class SolutionTest {

    private ListNode l1, l2;

    private int[] expected;

    public SolutionTest(int[][] input, int[] expected) {
        this.l1 = l(input[0]);
        this.l2 = l(input[1]);
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {i(l(0),
                   l(0)),
                   l(0)},
                {i(l(9),
                   l(1, 2, 9)),
                   l(0, 3, 9)},
                {i(l(9, 9, 9, 9, 9),
                   l(1, 1)),
                   l(0, 1, 0, 0, 0, 1)},
        });
    }

    @Test
    public void validateSum() {
        Solution solution = new Solution();
        int[] r = a(solution.addTwoNumbers(l1, l2));
        assertTrue(Arrays.equals(r, expected));
    }

    private static ListNode l(int[] a) {
        ListNode head = new ListNode(a[0]);
        if(a.length == 1) {
            return head;
        } else {
            ListNode temp, current = head;
            for(int i = 1; i < a.length; i++) {
                temp = new ListNode(a[i]);
                current.next = temp;
                current = temp;
            }
            return head;
        }
    }

    private static int[] a(ListNode l) {
        if(l == null) {
            return new int[0];
        }

        List<Integer> l2 = new LinkedList<>();
        while(l != null) {
            l2.add(l.val);
            l = l.next;
        }

        int i = 0;
        int[] array = new int[l2.size()];
        for(Integer d: l2) {
            array[i++] = d;
        }
        return array;
    }

    private static int[][] i(int[] i1, int[] i2) {
        return new int[][]{i1, i2};
    }

    private static int[] l(int i1, int... i2) {
        int[] data = new int[i2.length + 1];

        data[0] = i1;
        System.arraycopy(i2, 0, data, 1, i2.length);
        return data;
    }
}