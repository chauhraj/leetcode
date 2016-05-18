
package com.github.chauhraj.leetcode.pb15;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private int[] input;
    private List<List<Integer>> expected;
    public SolutionTest(int[] input, List<List<Integer>> expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {a(-1, 2, 1), ll(l(), l())},
                {a(0, 0), ll(l())},
                {a(0, 0, 0), ll(l(0, 0, 0))},
                {a(-1, 2, -1), ll(l(-1, -1, 2))},
                {a(-1, 1, 2, -1, -4), ll(l(-1, -1, 2))},
                {a(-1, 0, 1, 2, -1, -4), ll(l(-1, 0, 1), l(-1, -1, 2))}
        });
    }

    @Test
    public void executethreeSumTest() {
        Solution solution = new Solution();
        assertThat(solution.threeSum(input), is(expected));
    }

    private static List<List<Integer>> ll(List<Integer>... al) {
        return Arrays.asList(al);
    }

    private static List<Integer> l(Integer... nos) {
        return Arrays.asList(nos);
    }

    private static int[] a(int... nos) {
        return nos;
    }
}
        