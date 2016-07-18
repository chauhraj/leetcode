
package com.github.chauhraj.leetcode.pb322;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private int amount;
    private int[] coins;
    private int expected;
    public SolutionTest(int[] input, int amount, int expected) {
        this.coins = input;
        this.amount = amount;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {a(1, 2), 1, 1},
                {a(1), 0, 0},
                {a(186,419,83,408), 6249, 20},
                {a(25, 10, 5), 30, 2},
                {a(9, 6, 5, 1), 11, 2},
                {a(2), 3, -1},
                {a(1,2, 5), 11, 3}
        });
    }

    @Test
    public void executeCoinChangeTest() {
        Solution solution = new Solution();
        assertThat(solution.coinChange(coins, amount), is(expected));
    }

    private static int[] a(int... n) {
        return n;
    }
}
        