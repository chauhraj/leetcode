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

    public static List<Integer> l(Integer... nos) {
        return Arrays.asList(nos);
    }

    public static int[] a(int... nos) {
        return nos;
    }

    public static String deepToString(boolean[][] a) {
        StringBuilder b = new StringBuilder();
        b.append("[").append(System.getProperty("line.separator"));
        int maxIdx = a.length - 1;
        for(int i = 0; i <= maxIdx - 1; i++) {
            b.append("[");
            int maxInIdx = a[i].length - 1;
            for(int j = 0; j <= maxInIdx - 1; j++) {
                b.append(a[i][j] == true ? 'T' : 'F').append(',');
            }
            b.append(a[i][maxInIdx] == true ? 'T' : 'F').append(']').append(System.getProperty("line.separator"));
        }
        b.append("[");
        int maxInIdx = a[maxIdx].length - 1;
        for(int j = 0; j <= maxInIdx - 1; j++) {
            b.append(a[maxIdx][j] == true ? 'T' : 'F').append(',');
        }
        b.append(a[maxIdx][maxInIdx] == true ? 'T' : 'F').append(']').append(System.getProperty("line.separator")).append("]");
        return b.toString();
    }


}
