package com.github.chauhraj.leetcode.pb61;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by chauhraj on 3/18/16.
 */
@RunWith(Parameterized.class)
public class PB61Test {

    private final String input;
    private final boolean result;

    public PB61Test(String input, boolean result) {
        this.input = input;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"   ", false},
                {"7e57.", false},
                {".", false},
                {"1 .", false},
                {".2.", false},
                {"e9", false},
                {".e1", false},
                {"1e.", false},
                {"53K", false},
                {" -.", false},
                {" -e58 ", false},
                {"-1.23456e+10 1234", false},
                {"-1.23456e", false},
                {"1.", true},
                {"12345", true},
                {"+12345", true},
                {"-1.23456", true},
                {"-1.23456e+10", true},
                {"-1.23456e-10", true},
        });
    }

    @Test
    public void validateStringIfValidNumber() {
        assertThat("Failed for input:" + input, new Solution().isNumber(input), is(result));
    }
}