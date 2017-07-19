package com.github.chauhraj.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.github.chauhraj.Utils.a;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Pb42SolutionTest {
    private int[] input;
    private int expected;
    public Pb42SolutionTest(int[] height, int water) {
        this.input = height;
        this.expected = water;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
          {a(5,5,1,7,1,1,5,2,7,6), 23},
          {a(0,1,0,2,1,0,1,3,2,1,2,1), 6},
          {a(), 0},
          {a(2,0,2), 2},
          {a(4,2,3), 1},
        });
    }

    @Test
    public void executetrapTest() {
        Pb42Solution solution = new Pb42Solution();
        assertThat(solution.trap(input), is(expected));
    }

}
        