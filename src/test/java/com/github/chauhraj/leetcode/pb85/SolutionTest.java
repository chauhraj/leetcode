
package com.github.chauhraj.leetcode.pb85;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private char[][] input;
    private int expected;
    public SolutionTest(char[][] input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        char[][] input = new char[][]{"10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()
        };
        return Arrays.asList(new Object[][]{
                {input, 6},
        });
    }

    @Test
    public void executemaximalRectangleTest() {
        Solution solution = new Solution();
        assertThat(solution.maximalRectangle(input), is(expected));
    }

}
        