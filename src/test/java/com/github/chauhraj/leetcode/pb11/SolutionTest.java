
package com.github.chauhraj.leetcode.pb11;

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
                {a(1, 2, 3, 4, 5, 6), 9},
                {a(6, 4, 5, 3, 2, 1), 9},
                {a(1, 1), 1},
                {a(1, 1, 1), 2}
        });
    }

    @Test
    public void executeMaxAreaTest() {
        Solution solution = new Solution();
        assertThat(solution.maxArea(input), is(expected));
    }

    private static int[] a(int... nos) {
        return nos;
    }
}
        