package com.github.chauhraj.leetcode.pb85;

import static java.lang.Math.max;

public class Solution {

    static class Q {
        private int h;
        private int v;
        private int r;
        private int l;
        private int g;

        private Q(int h, int v, int r, int l, int g) {
            this.h = h;
            this.v = v;
            this.r = r;
            this.l = l;
            this.g = g;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d, %d, %d, %d)", h, v, r, l, g);
        }

    }

    static Q mq(int h, int v, int r, int l, int g) {
        return new Q(h, v, r, l, g);
    }

    public int maximalRectangle(char[][] A) {
        int m = A.length + 1;
        int n = A[0].length + 1;
        Q[][] C = new Q[m][n] ;
        Q zero = mq(0, 0, 0, 0, 0); setAll(C, zero);
        int global = 0, local0 = 0;
        for(int col = 0; col < n - 1; col++) {
            if(A[0][col] == '1') {
                if(col > 0 && A[0][col - 1] == '1') {
                    // simply extending the region of the rectangle by 1.
                    local0 += 1; global = max(local0, global);
                    C[1][col + 1] = mq(1 + C[1][col].h, 1, 1 + C[1][col].h, local0, global);
                } else {
                    local0 = 1; global = max(local0, global);
                    C[1][col + 1] = mq(1, 1, 1, local0, global);
                }
            } else {
                local0 = 0;
                C[1][col + 1] = mq(0, 0, 0, local0, global);
            }
        }

        local0 = C[1][1].l; global = C[1][1].g;
        for(int row = 1; row < m - 1; row++) {
            if(A[row][0] == '1') {
                if(A[row - 1][0] == '1') {
                    // simply extending the region of the rectangle by 1.
                    local0 += 1; global = max(local0, global);
                    C[row + 1][1] = mq(1, 1 + C[row][1].v, 1 + C[row][1].v, local0, global);
                } else {
                    local0 = 1; global = max(local0, global);
                    C[row + 1][1] = mq(1, 1, 1, local0, global);
                }
            } else {
                local0 = 0;
                C[row + 1][1] = mq(0, 0, 0, local0, global);
            }
        }

        System.out.println("Only populating C's 1st row and 1st column");
        System.out.printf("A: %n%s %n", deepToString(A));
        System.out.printf("C: %n%s %n", deepToString(C));

        local0 = C[1][1].l;
        global = C[1][1].g;
        for(int row = 1; row < m - 1; row++) {
            for(int col = 1; col < n - 1; col++) {
                char cur = A[row][col], v = A[row - 1][col], h = A[row][col - 1], d = A[row - 1][col -1];
                System.out.printf("%nA[%d][%d] = %s%n", row, col, cur);
                if(cur == '1') {
                    if(h == '0' && v == '0') { // d doesn't matter
                        local0 = 1; global = max(local0, global);
                        C[row + 1][col + 1] = mq(1, 1, 1, local0, global);
                    } else if (h == '0' && v == '1') { // d doesn't matter
                        local0 = 1 + C[row][col + 1].v; global = max(local0, global);
                        C[row + 1][col + 1] = mq(1, 1 + C[row][col+1].v, local0, local0, global);
                    } else if (h == '1' && v == '0') { // d doesn't matter
                        local0 = 1 + C[row][col].h; global = max(local0, global);
                        C[row + 1][col + 1] = mq(1 + C[row+1][col].h, 1, local0, local0, global);
                    } else if (h == '1' && v == '1' && d == '0') {
                        local0 = 1 + max(C[row+1][col].h, C[row][col+1].v); global = max(local0, global);
                        C[row + 1][col + 1] = mq(1 + C[row+1][col].h, 1 + C[row][col+1].v, local0, local0, global);
                    } else if (h == '1' && v == '1' && d == '1') {
                        local0 = 1 + (C[row+1][col].l + C[row][col+1].l - C[row][col].l); global = max(local0, global);
                        C[row + 1][col + 1] = mq(1 + C[row+1][col].h, 1 + C[row][col+1].v, local0, local0, global);
                    }

                } else {
                    local0 = 0;
                    C[row + 1][col + 1] = mq(0, 0, 0, local0, global = max(C[row][col + 1].g, C[row+1][col].g));
                }
                System.out.printf("After A[%d][%d] = %s%n, C is now %s%n", row, col, cur, deepToString(C));
                System.out.printf("A%n%s%n", deepToString(A));
            }
        }
        System.out.printf("After C: %n%s %n", deepToString(C));
        return C[m - 1][n - 1].g;
    }

    private void setAll(Q[][] c, Q zero) {
        for(int i = 0; i < c.length; i++) {
            for(int j = 0; j < c[i].length; j++)
                c[i][j] = zero;
        }
    }

    private String deepToString(Object[][] a) {
        StringBuilder b = new StringBuilder();
        b.append("[").append(System.getProperty("line.separator"));
        int maxIdx = a.length - 1;
        for(int i = 0; i <= maxIdx - 1; i++) {
            b.append("[");
            int maxInIdx = a[i].length - 1;
            for(int j = 0; j <= maxInIdx - 1; j++) {
                b.append(a[i][j]).append(',');
            }
            b.append(a[i][maxInIdx]).append(']').append(System.getProperty("line.separator"));
        }
        b.append("[");
        int maxInIdx = a[maxIdx].length - 1;
        for(int j = 0; j <= maxInIdx - 1; j++) {
            b.append(a[maxIdx][j]).append(',');
        }
        b.append(a[maxIdx][maxInIdx]).append(']').append(System.getProperty("line.separator")).append("]");
        return b.toString();
    }

    private String deepToString(char[][] a) {
        StringBuilder b = new StringBuilder();
        b.append("[").append(System.getProperty("line.separator"));
        int maxIdx = a.length - 1;
        for(int i = 0; i <= maxIdx - 1; i++) {
            b.append("[");
            int maxInIdx = a[i].length - 1;
            for(int j = 0; j <= maxInIdx - 1; j++) {
                b.append(a[i][j]).append(',');
            }
            b.append(a[i][maxInIdx]).append(']').append(System.getProperty("line.separator"));
        }
        b.append("[");
        int maxInIdx = a[maxIdx].length - 1;
        for(int j = 0; j <= maxInIdx - 1; j++) {
            b.append(a[maxIdx][j]).append(',');
        }
        b.append(a[maxIdx][maxInIdx]).append(']').append(System.getProperty("line.separator")).append("]");
        return b.toString();
    }
}
        