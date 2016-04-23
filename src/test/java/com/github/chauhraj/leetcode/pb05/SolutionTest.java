
package com.github.chauhraj.leetcode.pb05;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private int input;
    private int expected;
    public SolutionTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100, 1},
                {0, 0},
                {1534236469, 0},
                {Integer.MIN_VALUE/2, 0}
        });
    }

    @Test
    public void executeReverseTest() {
        Solution solution = new Solution();
        assertThat(solution.reverse(input), is(expected));
    }

}
        