package com.github.chauhraj.leetcode.pb04;

import static java.lang.Math.*;

import java.util.Arrays;

class Solution {

    double findMedianSortedArrays(int[] array1, int[] array2) {
        if(array1.length == 0 && array2.length == 0) {
            return Double.NaN;
        }
        if(array1.length == 0) {
            int mid = array2.length/2;
            if(isEven(array2.length)) {
                return (array2[mid] + array2[mid - 1]) * 0.5;
            } else {
                return array2[mid];
            }
        }
        if(array2.length == 0) {
            int mid = array1.length/2;
            if(isEven(array1.length)) {
                return (array1[mid] + array1[mid - 1]) * 0.5;
            } else {
                return array1[mid];
            }
        }
        int midOfA = valueAtMid(array1, 0, array1.length - 1);
        int midOfB = valueAtMid(array2, 0, array2.length - 1);
        if(midOfA == midOfB) {
            return midOfA;
        } else if(midOfA < midOfB) {
            return findMedian(array1, array2, midOfB);
        } else {
            return findMedian(array2, array1, midOfA);
        }
    }

    private double findMedian(int[] array1, int[] array2, int mid) {
        int totalSize = array1.length + array2.length;
        int midIndexA = midIndex(0, array1.length - 1);
        int midIndexB = midIndex(0, array2.length - 1);
        int keyIndex = binarySearch(array1, midIndexA, array1.length, mid)  - 1;
        int key = array1[keyIndex];
        int l = binarySearch(array2, 0, midIndexB, key); // it is the index of key in array2
        int totalElementsBeforeKey = keyIndex + (l);
        int noOfElementsBeforeMedian = (totalSize / 2);
        int noOfElementsMoreToConsider = (noOfElementsBeforeMedian - totalElementsBeforeKey);
        if(noOfElementsMoreToConsider == 0) {
            if(isEven(totalSize)) {
                int second = Math.max( valueAt(array1, keyIndex - 1),
                                       valueAt(array2, (l - 1)));
                return (key + second) * 0.5;
            } else {
                return key;
            }
        } else if(noOfElementsMoreToConsider == 1) {
            if(isEven(totalSize)) {
                int second = Math.min( valueAt(array1, keyIndex + 1),
                                       valueAt(array2, l));
                return (key + second) * 0.5;
            } else {
                return Math.min( valueAt(array1, keyIndex + 1),
                                 valueAt(array2, l));
            }
        }  else if(noOfElementsMoreToConsider == -1) {
            if(isEven(totalSize)) {
                int f, s;
                int v1, v2;
                if( (v1 = valueAt(array1, keyIndex - 1)) > (v2 = valueAt(array2, l - 1))) {
                    f = v1;
                    s = max(v2, valueAt(array1, keyIndex - 2));
                } else {
                    f = v2;
                    s = max(v1, valueAt(array2, l - 2));
                }
                return (f + s) * 0.5;
            } else {
                return Math.max( valueAt(array1, keyIndex - 1),
                                 valueAt(array2, l - 1));
            }
        } else if(noOfElementsMoreToConsider >= 0){
            if(isEven(totalSize)) {
                int newIndex = l - 1 + (noOfElementsMoreToConsider - 1);
                int first = array2[newIndex];
                int second = Math.min( valueAt(array2, newIndex + 1),
                                       valueAt(array1, keyIndex + 1));
                return (first + second) * 0.5;
            } else {
                return Math.min( valueAt(array2, l + (noOfElementsMoreToConsider - 1)),
                        valueAt(array1, keyIndex + (noOfElementsMoreToConsider - 1)));
            }
        } else {
            if(isEven(totalSize)) {
                int f, s;
                int v1, v2;
                int firstIndex = totalSize / 2;
                int secondIndex = firstIndex + 1;
                if( (v1 = valueAt(array1, keyIndex + noOfElementsMoreToConsider)) > (v2 = valueAt(array2, l + noOfElementsMoreToConsider))) {
                    f = v1;
                    s = max(v2, valueAt(array1, keyIndex + (noOfElementsMoreToConsider - 1)));
                } else {
                    f = v2;
                    s = max(v1, valueAt(array2, l + (noOfElementsMoreToConsider - 1)));
                }
                return (f + s) * 0.5;
            } else {
                if(l == 0) {
                    return valueAt(array1, keyIndex - abs(noOfElementsMoreToConsider));
                } else {
                    int f, s;
                    int v1, v2;
                    int index1 = (keyIndex - 1), index2 = l - 1;
                    while(totalElementsBeforeKey > noOfElementsBeforeMedian + 1) {
                        totalElementsBeforeKey--;
                        if ((v1 = valueAt(array1, index1)) >= (v2 = valueAt(array2, index2))) {
                            index1--;
                        } else {
                            index2--;
                        }
                    }
                    return max(valueAt(array1, (index1)), valueAt(array2, index2));
                }
            }
        }
    }

    private int valueAt(int[] array, int index) {
        if(index >= array.length) {
            return Integer.MAX_VALUE;
        } else if(index < 0) {
            return Integer.MIN_VALUE;
        } else {
            return array[index];
        }
    }

    private static boolean isOdd(int n) {
        return !isEven(n);
    }
    private static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    private int binarySearch(int[] array1, int fromIndex, int toIndex, int key) {
        int k;
        int index = Arrays.binarySearch(array1, fromIndex, toIndex, key);
        if(index < 0) {
            if(index == -1) {
                k = 0;
            } else {
                int insertionIndex = -(index + 1);
                k = insertionIndex;
            }
        } else {
            k = index;
        }
        return k;
    }

    int valueAtMid(int[] array, int start, int end) {
        int midIndex = midIndex(start, end);
        return array[midIndex];
    }

    private int midIndex(int start, int end) {
        int numberOfElements = (end - start + 1);
        if((numberOfElements & 1) == 1) {
            return start + (numberOfElements >> 1);
        } else {
            return start + (numberOfElements >> 1) - 1;
        }
    }
}
        