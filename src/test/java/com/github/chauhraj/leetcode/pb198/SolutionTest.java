package com.github.chauhraj.leetcode.pb198;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private int[] input;
    private int expected;
    public SolutionTest(int[] input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {a(), 0},
            {a(10), 10},
            {a(10, 20), 20},
            {a(30, 20, 10, 40), 70},
            {a(10, 20, 30, 40), 60},
            {a(2,7,9,3,1), 12},
        });
    }

    @Test
    public void executeRobTest() {
        Solution solution = new Solution();
        assertThat(solution.rob(input), is(expected));
    }

    private static int[] a(int... nums) {
        return nums;
    }

}
        