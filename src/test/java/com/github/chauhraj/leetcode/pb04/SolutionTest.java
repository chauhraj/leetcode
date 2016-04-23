
package com.github.chauhraj.leetcode.pb04;

import static java.lang.Double.NaN;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private static final int MAX_ARRAY_SIZE = 50;
    private static final int MAX_ARRAY_ELEMENT = 1000;
    private static final int MAX_ITERATIONS = 1000;
    private final int[] array1;
    private final int[] array2;
    private final double first;
    private final double second;
    private double expected;
    public SolutionTest(int[] array1, int[] array2, double expected, double f, double s) {
        this.array1 = array1;
        this.array2 = array2;
        this.expected = expected;
        this.first = f;
        this.second = s;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> list = new LinkedList<>();
        int examples = MAX_ITERATIONS;
        for(int i = 1; i <= examples; i++) {
            list.add(createData());
        }
        return list;
    }

    @Test
    public void executeMedianSortedArraysTest() {
        Solution solution = new Solution();
        String msg = String.format("Failed for arrays \n A: %s, \n B: %s \n with median elements(%s, %s)\n", Arrays.toString(array1), Arrays.toString(array2), first, second);
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
        List<Integer> f2 = new ArrayList<>();
        for(int n : a1) f2.add(n); for(int n : a2) f2.add(n);
        double[] f0 = toArray0(f2);
        Arrays.sort(f0);
        int size = a1.length + a2.length, index = size / 2;
        double expected = NaN;
        double f = NaN, s = NaN;
        if (size > 0) {
            try {
                if(size % 2 == 0) {
                    expected = ((f = f0[index - 1]) + (s = f0[index])) * 0.5;
                } else {
                    expected = (f = f0[index]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return new Object[] {a1, a2, expected, f, s};
    }
    private static int[] generateArray() {
        int size = arraySizeRNG.nextInt(MAX_ARRAY_SIZE);
        Set<Integer> array = new HashSet<>();
        while(array.size() < size) {
            array.add(arrayRNG.nextInt(MAX_ARRAY_ELEMENT));
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
        