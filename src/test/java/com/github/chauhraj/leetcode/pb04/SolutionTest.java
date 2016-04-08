
package com.github.chauhraj.leetcode.pb04;

import static org.hamcrest.CoreMatchers.*;

import org.apache.commons.math3.stat.StatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private final int[] array1;
    private final int[] array2;
    private double expected;
    public SolutionTest(int[] array1, int[] array2, double expected) {
        this.array1 = array1;
        this.array2 = array2;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> list = new LinkedList<>();
        int examples = 1000;
        for(int i = 1; i <= examples; i++) {
            list.add(createData());
        }
        return list;
    }

    @Test
    public void executeMedianSortedArraysTest() {
        Solution solution = new Solution();
        String msg = String.format("Failed for arrays \n A: %s, \n B: %s", Arrays.toString(array1), Arrays.toString(array2));
        assertThat(msg, solution.findMedianSortedArrays(array1, array2), is(expected));
    }

    private static int[] a(int... array) {
        return array;
    }

    private static Random arraySizeRNG = new Random(1);
    private static Random arrayRNG = new Random(2);

    private static Object[] createData() {
        int[] a1 = generateArray();
        int[] a2 = generateArray();
        List<Integer> f = new ArrayList<>();
        for(int n : a1) f.add(n); for(int n : a2) f.add(n);
        double[] f0 = toArray0(f);
        Arrays.sort(f0);
        int size = a1.length + a2.length, index = size / 2;
        double expected = Double.NaN;
        if (size > 0) {
            try {
                if(size % 2 == 0) {
                    expected = (f0[index - 1] + f0[index]) * 0.5;
                } else {
                    expected = f0[index];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }
        return new Object[] {a1, a2, expected};
    }
    private static int[] generateArray() {
        int size = arraySizeRNG.nextInt(10);
        Set<Integer> array = new HashSet<>();
        while(array.size() < size) {
            array.add(arrayRNG.nextInt(500));
        }
        return toArray(array);
    }

    private static int[] toArray(Collection<Integer> array) {
        int[] result = new int[array.size()];
        int count = 0;
        for(int n : array) {
            result[count++] = n;
        }
        Arrays.sort(result);
        return result;
    }

    private static double[] toArray0(Collection<Integer> array) {
        double[] result = new double[array.size()];
        int count = 0;
        for(int n : array) {
            result[count++] = n;
        }
        return result;
    }
}
        