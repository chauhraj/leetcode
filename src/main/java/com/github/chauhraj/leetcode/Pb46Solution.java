package com.github.chauhraj.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pb46Solution {
    public List<List<Integer>> permute(int[] nums) {
        return permute(new LinkedList<>(), IntStream.of(nums).boxed().collect(Collectors.toList()));
    }

    private List<List<Integer>> permute(List<List<Integer>> acc, List<Integer> nums) {
        if(nums.size() == 1) {
            acc.add(nums);
        } else {
            int head = nums.get(0);
            List<List<Integer>> rest = permute(new LinkedList<>(), nums.subList(1, nums.size()));
            int size = rest.get(0).size();
            for(int i = 0; i <= size; i++) {
                for(List<Integer> ll: rest) {
                    LinkedList<Integer> l = new LinkedList<>();
                    l.addAll(ll);
                    l.add(i, head);
                    acc.add(l);
                }
            }
        }
        return acc;
    }
}
        