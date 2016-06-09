package com.github.chauhraj.leetcode.pb14;

import static java.util.Arrays.*;



import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Node root = new Node();

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        for(String s : strs) {
            addToTrie(s);
        }
        if(root.map.size() > 1) {
            return "";
        } else {
            Node current = root;
            StringBuilder longestPrefix = new StringBuilder();
            while(!current.isTerminal && current.map.size() == 1) {
                Map.Entry<Character, Node> entry = current.map.entrySet().iterator().next();
                longestPrefix.append(entry.getKey());
                current = entry.getValue();
            };
            return longestPrefix.toString();
        }
    }

    private void addToTrie(String str) {
        Node current = root;
        char[] cs = str.toCharArray();
        for(char c : cs) {
            if(current.map.containsKey(c)) {
                current = current.map.get(c);
            } else {
                Node n = new Node();
                current.map.put(c, n);
                current = n;
            }
        }
        current.isTerminal = true;
    }

    private static class Node {
        boolean isTerminal;
        private Map<Character, Node> map = new HashMap<>();
    }
}