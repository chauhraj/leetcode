package com.github.chauhraj.misc;

import java.math.BigDecimal;

/**
 * Created by chauhraj on 5/29/17.
 */
public class Main {

    private static BigDecimal[] array;
    public static void main(String[] args) {
        int N = 2017;
        array = new BigDecimal[N + 1];
        array[1] = BigDecimal.valueOf(2);
        array[2] = BigDecimal.valueOf(1);
        array[3] = BigDecimal.valueOf(3);
        for(int i = 4; i <= N; i++)
            array[i] = array[i-1].add(array[i-2]).add(array[i-3]);
        System.out.printf("N=%d value is:[%s] %n", N, array[N]);

    }

//    private void tribonnaci(BigDecimal f, BigDecimal s, BigDecimal t)
}
