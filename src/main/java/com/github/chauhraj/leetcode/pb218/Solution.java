package com.github.chauhraj.leetcode.pb218;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private static <T> List<T> l(T... nos) {
        return Arrays.asList(nos);
    }

    private static int[] a(int... nos) {
        return nos;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return Collections.emptyList();
        }
        return getSkyline(buildings, 0, buildings.length - 1);
    }

    private List<int[]> getSkyline(int[][] buildings, int l, int r) {
        if (l == r) {
            int[] bldg = buildings[l];
            return l(a(bldg[0], bldg[2]), a(bldg[1], 0));
        }
        int m = l + (r - l) / 2;
        List<int[]> left = getSkyline(buildings, l, m);
        List<int[]> right = getSkyline(buildings, m + 1, r);
        return mergeBuildings(left, right);
    }

    private List<int[]> mergeBuildings(List<int[]> first, List<int[]> second) {
        List<int[]> results = new LinkedList<>();

        int l = 0, r = 0, currentSkyline = 0, skyLine1 = 0, skyLine2 = 0;
        while (l < first.size() || r < second.size()) {
            if (r < second.size() && l < first.size()) {
                int[] leftBldg = first.get(l);
                int[] rightBldg = second.get(r);
                if (leftBldg[0] < rightBldg[0]) { // x1 < x2
                    if (leftBldg[1] > currentSkyline) {
                        skyLine1 = leftBldg[1];
                        results.add(leftBldg);
                    } else {
                        if (skyLine1 >= currentSkyline && leftBldg[1] < skyLine1) {
                            skyLine1 = leftBldg[1];
                            int prevSkyLine = currentSkyline;
                            currentSkyline = Integer.max(skyLine1, skyLine2);
                            if (prevSkyLine != currentSkyline) {
                                results.add(a(leftBldg[0], currentSkyline));
                            }
                        } else {
                            skyLine1 = leftBldg[1];
                        }
                    }
                    l++;
                } else if (leftBldg[0] == rightBldg[0]) {
                    skyLine1 = leftBldg[1];
                    skyLine2 = rightBldg[1];
                    int prevSkyLine = currentSkyline;
                    currentSkyline = Integer.max(skyLine1, skyLine2);
                    if (prevSkyLine < currentSkyline && leftBldg[1] >= currentSkyline) {
                        results.add(leftBldg);
                    } else if (prevSkyLine < currentSkyline && rightBldg[1] >= currentSkyline) {
                        results.add(rightBldg);
                    } else if (prevSkyLine > currentSkyline) {
                        results.add(a(leftBldg[0], currentSkyline));
                    } else if(leftBldg[1] == 0 && rightBldg[1] == 0) {
                        results.add(leftBldg);
                    }
                    l++;
                    r++;
                } else {
                    if (rightBldg[1] > currentSkyline) {
                        skyLine2 = rightBldg[1];
                        results.add(rightBldg);
                    } else {
                        if (skyLine2 >= currentSkyline && rightBldg[1] < skyLine2) {
                            skyLine2 = rightBldg[1];
                            int prevSkyLine = currentSkyline;
                            currentSkyline = Integer.max(skyLine1, skyLine2);
                            if (prevSkyLine != currentSkyline) {
                                results.add(a(rightBldg[0], currentSkyline));
                            }
                        } else {
                            skyLine2 = rightBldg[1];
                        }
                    }
                    r++;
                }
                currentSkyline = Integer.max(skyLine1, skyLine2);
            } else if (l < first.size()) {
                results.add(first.get(l++));
            } else if (r < second.size()) {
                results.add(second.get(r++));
            }
        }
        return results;
    }
}
        