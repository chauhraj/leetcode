
package com.github.chauhraj.leetcode.pb05;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private String input;
    private String expected;
    public SolutionTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"aaabaaaa", "aaabaaa"},
                {"1234aaabcdedcb4321", "bcdedcb"},
                {"abcba", "abcba"},
                {"a", "a"}
        });
    }

    @Test
    public void executeLongestPalindromeTest() {
        Solution solution = new Solution();
        assertThat(String.format("Failed for input: %s", input), solution.longestPalindrome(input), is(expected));
    }

}
        