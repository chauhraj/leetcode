package com.github.chauhraj.leetcode.pb03;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private String input;
    private int expected;
    public SolutionTest(String input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"abbcdaefbcbb", 6},
                {"", 0},
                {"bbbbb", 1},
                {"a bcdA.,BCDe", 12},
                {"dvdf", 3}
        });
    }

    @Test
    public void executeTest() {
        Solution s = new Solution();
        assertThat(s.lengthOfLongestSubstring(input), is(expected));
    }

}
        