package com.github.chauhraj;

import java.util.*;
public class LRUCache {

    private int capacity;

    private Pair head = new Pair(-1, -1);
    private Pair tail = new Pair(-1, -1);
    private Map<Integer, Pair> cache = new HashMap<>();

    int size;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.prev = tail;
        head.next = tail;
        tail.next = head;
        tail.prev = head;
    }

    public int get(int key) {
        Pair value = cache.get(key);
        if(value == null) {
            return -1;
        } else {
            moveToHead(value);
            return value.v;
        }
    }

    public void set(int key, int value) {
        Pair v = cache.get(key);
        if(v == null) {
            Pair p = new Pair(key, value);
            addAtHead(p);
            cache.put(key, p);
            size++;
            if(size > capacity) {
                Pair r = removeLast();
                //System.out.printf("Current Size:%d, capacity:%d, Inserting(%d, %d), Removing(%d, %d) %n", nodes.size(), capacity, key, value, p.k, p.v);
                cache.remove(r.k);
                size--;
            }
        } else {
            moveToHead(v);
            v.v = value;
        }
    }

    private void moveToHead(Pair p) {
        removeItself(p);
        addAtHead(p);
    }

    private void addAtHead(Pair p) {
        Pair headNext = head.next;
        head.next = p;
        p.prev = head;

        p.next = headNext;
        headNext.prev = p;
    }

    private Pair removeLast() {
        Pair p = tail.prev;
        removeItself(tail.prev);
        return p;
    }

    private void removeItself(Pair p) {
        if(p != head) {
            Pair prev = p.prev;
            Pair next = p.next;

            p.next = null;
            p.prev = null;

            prev.next = next;
            next.prev = prev;
        }
    }

    static class Pair {
        int k, v;
        Pair prev, next;

        Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public int hashCode() {
            return Integer.hashCode(k);
        }

        public boolean equals(Object o) {
            if(o instanceof Pair) {
                Pair that = (Pair)o;
                return that.k == k;
            } else return false;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1);
        cache.set(2, 1);
        System.out.println(cache.get(2));
        cache.set(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}