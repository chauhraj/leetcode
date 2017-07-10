
package com.github.chauhraj.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Pb10SolutionTest {
    private String pattern;
    private String text;
    private boolean expected;

    public Pb10SolutionTest(String text, String pattern, boolean expected) {
        this.text = text;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", "*", false},
                {"aa", "a", false},
                {"aa", "a*", true},
                {"aa", "aa", true},
                {"aa", ".*", true},
                {"ab", ".*", true},
                {"aaa", ".*", true},
                {"aaa", "ab*a*c*a", true},
                {"aaa", "ab*a", false},
                {"aa", "ab*", false},
                {"aab", "c*a*b", true},
                {"aa", "a", false},
                {"a", "c*", false},
        });
    }

    @Test
    public void executeisMatchTest() {
        Pb10Solution solution = new Pb10Solution();
        assertThat(String.format("Failed for input[%s, %s]", text, pattern), solution.isMatch(text, pattern), is(expected));
    }

}
        