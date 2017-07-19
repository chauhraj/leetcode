package com.github.chauhraj.leetcode;

import java.util.ArrayList;
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

    public List<List<Integer>> permute0(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}