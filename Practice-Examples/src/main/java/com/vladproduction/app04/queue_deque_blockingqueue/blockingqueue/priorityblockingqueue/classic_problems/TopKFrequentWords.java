package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.priorityblockingqueue.classic_problems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Problem 3: Top K Frequent Elements
 * Scenario: Find the k most frequent words or numbers from a dataset.
 * */
public class TopKFrequentWords {
    public static void main(String[] args) {

        String[] words = {
                //"the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog"
                "apple", "banana", "apple", "cherry", "banana", "banana"
        };

        //create map to store word frequencies
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        //iterating through words
        for (String word : words) {
            //increment frequency
            wordFrequencyMap.merge(word, 1, Integer::sum);
        }
        //System.out.println(wordFrequencyMap);

        //creating priority queue
        PriorityBlockingQueue<WordFrequency> pq = new PriorityBlockingQueue<>(3, Comparator.comparingInt(WordFrequency::frequency));

        //iterating over map and adding elements to priority queue
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            pq.offer(new WordFrequency(entry.getKey(), entry.getValue()));
            if(pq.size() > 2){
                pq.poll(); // keep only top 2 elements
            }
        }

        while (!pq.isEmpty()){
            WordFrequency current = pq.poll();
            System.out.println(current.word + " : " + current.frequency);
        }

        System.out.println("====");

        //task solution2:
        solution2();

    }

    private static void solution2() {
        String[] words = {"apple", "banana", "apple", "cherry", "banana", "banana"};

        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        PriorityBlockingQueue<WordFreq> pq = new PriorityBlockingQueue<>(3, Comparator.comparingInt(w -> w.count));

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            pq.offer(new WordFreq(entry.getKey(), entry.getValue()));
            if (pq.size() > 2) {
                pq.poll(); // Keep size to top 2
            }
        }

        while (!pq.isEmpty()) {
            WordFreq wf = pq.poll();
            System.out.println(wf.word + ": " + wf.count);
        }
    }

    //model class for solution 2
    static class WordFreq {
        String word;
        int count;

        WordFreq(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    record WordFrequency(String word, int frequency) { }
}
