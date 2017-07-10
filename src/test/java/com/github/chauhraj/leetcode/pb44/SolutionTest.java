
package com.github.chauhraj.leetcode.pb44;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SolutionTest {
    private final String pattern;
    private String text;
    private boolean expected;
    public SolutionTest(String text, String pattern, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", "*", true},
                {"aa", "a*", true},
                {"aa", "a", false},
        });
    }

    @Test
    public void executeisMatchTest() {
        Solution solution = new Solution();
        assertThat(solution.isMatch(text, pattern), is(expected));
    }

}
        