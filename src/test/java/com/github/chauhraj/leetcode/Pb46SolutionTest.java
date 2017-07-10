
package com.github.chauhraj.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.github.chauhraj.Utils.a;
import static com.github.chauhraj.Utils.l;
import static com.github.chauhraj.Utils.ll;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Pb46SolutionTest {
    private int[] input;
    private List<List<Integer>> expected;
    public Pb46SolutionTest(int[] input, List<List<Integer>> expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
          {a(1, 2, 3), ll(l(1,2,3), l(1, 3, 2), l(2, 1, 3), l(2,3,1), l(3,1, 2), l(3,2,1))}
        });
    }

    @Test
    public void executepermuteTest() {
        Pb46Solution solution = new Pb46Solution();
        List<List<Integer>> result = solution.permute(input);
        assertThat(result, is(expected));
    }

}
        