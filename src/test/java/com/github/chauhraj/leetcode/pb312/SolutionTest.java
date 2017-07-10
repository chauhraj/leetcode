
package com.github.chauhraj.leetcode.pb312;

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
        });
    }

    @Test
    public void executemaxCoinsTest() {
        Solution solution = new Solution();
        assertThat(solution.maxCoins(input), is(expected));
    }

}
        