package com.vladproduction.app04.queue_deque_blockingqueue.deque.linkedlistdeque.classic_problems;

import java.util.LinkedList;

public class PalindromeLinkedListChecker {

    public static boolean isPalindrome(String input) {
        LinkedList<Character> deque = new LinkedList<>();

        // Add characters ignoring non-alphanumeric and lowercase for uniformity
        for (char c : input.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                deque.addLast(Character.toLowerCase(c));
            }
        }

        // Compare front and back until we meet in the middle
        while (deque.size() > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) {
                return false;
            }
        }

        return true;
    }

    // Demo
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(isPalindrome("racecar"));                         // true
        System.out.println(isPalindrome("LinkedList"));                     // false
    }

}
