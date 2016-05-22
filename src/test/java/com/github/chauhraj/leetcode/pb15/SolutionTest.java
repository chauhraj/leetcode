
package com.github.chauhraj.leetcode.pb15;

import static org.hamcrest.CoreMatchers.*;

import org.apache.commons.collections4.CollectionUtils;
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
                {a(-2,-4,-5,-5,-2,-3,-5,-4,-2), ll()},
                {a(-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0), ll(l(-5,1,4),l(-4,0,4),l(-4,1,3),l(-2,-2,4),l(-2,1,1),l(0,0,0))},
                {a(-2,0,0,2,2), ll(l(-2, 0, 2))},
                {a(0, 0, 0, 0), ll(l(0, 0, 0))},
                {a(0, 0, 0), ll(l(0, 0, 0))},
                {a(-1, 0, 1, 2, 0, -1, -4, 0), ll(l(-1, 0, 1), l(-1, -1, 2), l(0, 0, 0))},
                {a(-1, 0, 1, 2, -1, -4), ll(l(-1, 0, 1), l(-1, -1, 2))},
                {a(5, 4, -3, -4, -2, 0, -1, -1, 2), ll(l(-4,-1,5),l(-4,0,4),l(-3,-2,5),l(-3,-1,4),l(-2,0,2),l(-1,-1,2))},
                {a(-1, 2, 1), ll()},
                {a(0, 0), ll()},
                {a(-1, 2, -1), ll(l(-1, -1, 2))},
                {a(-1, 1, 2, -1, -4), ll(l(-1, -1, 2))},
        });
    }

    @Test
    public void executethreeSumTest() {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.threeSum(input);
        assertThat(result.size(), is(expected.size()));
        Collections.sort(expected, SolutionTest::comparator);
        Collections.sort(result, SolutionTest::comparator);
        for(int i = 0; i < result.size(); i++) {
            assertTrue(CollectionUtils.isEqualCollection(result.get(i), expected.get(i)));
        }
    }

    private static int comparator(List<Integer> l1, List<Integer> l2) {
        int x1 = l1.get(0), y1 = l1.get(1), z1 = l1.get(2);
        int x2 = l2.get(0), y2 = l2.get(1), z2 = l2.get(2);
        if(x1 < x2) {
            return -1;
        } else if(x1 == x2) {
            if(y1 < y2) {
                return -1;
            } else if(y1 == y2) {
                if(z1 < z2) {
                    return -1;
                } else if(z1 == z2) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
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
        