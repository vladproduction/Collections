package com.vladproduction.app05.map.concurrenthashmap.classic_problems;

import java.util.concurrent.ConcurrentHashMap;

public class WordCounter {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};

        ConcurrentHashMap<String, Integer> wordFreq = new ConcurrentHashMap<>();

        for (String word : words) {
            wordFreq.merge(word, 1, Integer::sum); // atomic update
        }

        System.out.println(wordFreq);
    }
}
