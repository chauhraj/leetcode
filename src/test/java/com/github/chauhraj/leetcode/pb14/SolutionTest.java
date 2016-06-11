
package com.github.chauhraj.leetcode.pb14;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private String[] input;
    private String expected;
    public SolutionTest(String[] input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[] {"flower","flow","flight"}, "fl"},
                {new String[] {"aa", "ab"}, "a"},
                {new String[] {"Satya", "Satyam", "Satyamev"}, "Satya"},
                {new String[] {"Satya", "Satyam", "", "Satyamev"}, ""}
        });
    }

    @Test
    public void executelongestCommonPrefixTest() {
        Solution solution = new Solution();
        assertThat(solution.longestCommonPrefix(input), is(expected));
    }

}
        