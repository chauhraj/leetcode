package com.github.chauhraj;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chauhraj on 5/30/16.
 */
public class Utils {
    public static List<List<Integer>> ll(List<Integer>... al) {
        return Arrays.asList(al);
    }

    public static <T> List<T> l(T... nos) {
        return Arrays.asList(nos);
    }

    public static int[] a(int... nos) {
        return nos;
    }

    public static int[][] as(int[]... a) {
        return a;
    }

}
