package com.vladproduction.app04.queue_deque_blockingqueue.blockingqueue.priorityblockingqueue.classic_problems;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Problem 2: Merge K Sorted Streams (like arrays or files)
 * Scenario: Merge sorted arrays using a min-heap style approach.
 * */
public class MergeKSorted {
    public static void main(String[] args) {
        int[][] arrays = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        PriorityBlockingQueue<StreamEntry> pq = new PriorityBlockingQueue<>();

        for (int i = 0; i < arrays.length; i++) {
            pq.add(new StreamEntry(arrays[i][0], i, 0));
        }

        while (!pq.isEmpty()) {
            StreamEntry current = pq.poll();
            System.out.print(current.value + " ");

            int nextIdx = current.elementIndex + 1;
            if (nextIdx < arrays[current.arrayIndex].length) {
                pq.add(new StreamEntry(
                        arrays[current.arrayIndex][nextIdx], current.arrayIndex, nextIdx));
            }
        }
    }

    static class StreamEntry implements Comparable<StreamEntry> {
        int value, arrayIndex, elementIndex;

        StreamEntry(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(StreamEntry o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
