package com.github.chauhraj.leetcode.pb218;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Stream;

import static com.github.chauhraj.Utils.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private int[][] input;
    private List<int[]> expected;
    public SolutionTest(int[][] input, List<int[]> expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {as(a(0,5,7),a(5,10,7),a(5,10,12),a(10,15,7),a(15,20,7),a(15,20,12),a(20,25,7)), l(a(0,7),a(5,12),a(10,7),a(15,12),a(20,7),a(25,0))},
                {as(a(2,4,7),a(2,4,5), a(2,4,6)), l(a(2, 7), a(4, 0))},
                {as(a(0,2,3),a(2,5,3)), l(a(0, 3), a(5, 0))},
                {as(a(4,10,10),a(5,10,9),a(6,10,8),a(7,10,7),a(8,10,6),a(9,10,5)), l(a(4, 10), a(10, 0))},
                {as(a(0,3,3),a(1,5,3),a(2,4,3),a(3,7,3)), l(a(0, 3), a(7, 0))},
                {as(a(1,2,1),a(1,2,2),a(1,2,3)), l(a(1, 3), a(2, 0))},
                {as(a(2, 9, 10), a(3, 7, 15)), l(a(2, 10), a(3, 15), a(7, 10), a(9, 0))},
                {as(a(2, 9, 10), a(3, 7, 15), a(4, 8, 8)), l(a(2, 10), a(3, 15), a(7, 10), a(9, 0))},
                {as(a(2, 9, 10), a(3, 7, 15), a(5, 12, 12), a(15, 20, 10), a(19, 24, 8)), l(a(2, 10), a(3, 15), a(7, 12), a(12, 0), a(15, 10), a(20, 8), a(24, 0))},
        });
    }

    @Test
    public void executeSkylineTest() {
        Solution solution = new Solution();
        List<int[]> result = solution.getSkyline(input);
        assertThat(message(result, expected), result.size(), is(expected.size()));
        for(int i = 0; i < result.size(); i++) {
            assertTrue(String.format("%s != %s", Arrays.toString(result.get(i)), Arrays.toString(expected.get(i))), Arrays.equals(result.get(i), expected.get(i)));
        }

    }

    private String message(List<int[]> result, List<int[]> expected) {
        return String.format("Found %s, expected %s", toString0(result), toString0(expected));
    }

    static String toString0(List<int[]> l) {
        StringBuilder b = new StringBuilder("[");
        for(int[] ia: l) {
            b.append(String.format("%s", Arrays.toString(ia))).append(",");
        }
        b.append("]");
        return b.toString();
    }
}
        