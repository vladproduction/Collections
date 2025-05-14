package com.vladproduction.app12.queue;

import com.vladproduction.app11.list.Guest;

import java.util.ArrayDeque;
import java.util.Queue;

public class MainApp_ArrayDeque {
    public static void main(String[] args) {

        Guest john = new Guest("John", "Doe", false);
        Guest bob = new Guest("Bob", "Doe", false);
        Guest sonia = new Guest("Sonia", "Doe", true); //loyalty program
        Guest siri = new Guest("Siri", "Doe", true); //loyalty program

        //create a queue
        Queue<Guest> checkInQueue = new ArrayDeque<>();
//        Guest guest = checkInQueue.remove(); //NoSuchElementException
//        System.out.println(guest);

        //offer
        checkInQueue.offer(john); //adding element to the queue
        checkInQueue.offer(bob); //adding element to the queue
        checkInQueue.offer(sonia); //adding element to the queue
        checkInQueue.offer(siri); //adding element to the queue
        print(checkInQueue);

        //poll
        Guest guest = checkInQueue.poll();//retrieve and remove head
        print(checkInQueue);
        System.out.println("pool (removed): " + guest); //null if no elements in the queue

        //peek
        Guest guest1 = checkInQueue.peek();//retrieve only and not remove
        print(checkInQueue);
        System.out.println("peek (not removed): " + guest1);

        //output:
        /*--Queue Contents--  //here we added 4 elements
        0: John Doe (Head)
        1: Bob Doe
        2: Sonia Doe
        3: Siri Doe


        --Queue Contents--  //first one was removed by poll
        0: Bob Doe (Head)
        1: Sonia Doe
        2: Siri Doe

        John Doe --> is polled element

        --Queue Contents-- //first element remain because we used peek
        0: Bob Doe (Head)
        1: Sonia Doe
        2: Siri Doe

        Bob Doe --> stood in the queue (not deleted)
        */

    }

    /**
     * Prints the contents of the given queue of Guest objects to the console.
     * <p>This method formats the output to display each Guest in the queue
     * along with its index. The first element of the queue is labeled as
     * the "Head" for clarity.</p>
     * @param queue The queue containing Guest objects to be printed.
     *              This queue should not be null.
     */
    public static void print(Queue<Guest> queue) {
        System.out.format("%n--Queue Contents--%n");
        int x = 0;
        for (Guest guest : queue) {
            System.out.format("%x: %s %s %n", x++, guest.toString(), x == 1 ? "(Head)" : "");
        }
        System.out.println();
    }

}
