package com.vladproduction.app04.queue_deque_blockingqueue.queue.priorityqueue.classic_problems;

import java.util.PriorityQueue;

/**
 * Kth smallest elements
 */
public class KSmallestElements {
    public static void main(String[] args) {


        int[] result = findKSmallest(new int[]{7, 10, 4, 3, 20, 15, -2}, 3);

        System.out.println("3 Smallest: ");

        for (int num : result) {
            System.out.println(num); //printing Kth smallest elements
        }

    }

    public static int[] findKSmallest(int[] nums, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove largest in top k
            }
        }

        return maxHeap.stream().mapToInt(i -> i).toArray();
    }


}
