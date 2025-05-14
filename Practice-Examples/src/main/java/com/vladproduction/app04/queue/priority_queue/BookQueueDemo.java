package com.vladproduction.app04.queue.priority_queue;



import java.util.PriorityQueue;
import java.util.Queue;

public class BookQueueDemo {
    public static void main(String[] args) {

        Book b1 = new Book(15, "Spain", "John", "EU", 20);
        Book b2 = new Book(32, "Geman", "Dereck", "EU", 12);
        Book b3 = new Book(27, "United States", "Garry", "NA", 27);
        Book b4 = new Book(755, "Brazil", "Dung", "SA", 20);
        Book b5 = new Book(68, "India", "Draws", "EA", 20);

        Queue<Book> queue = new PriorityQueue<>();
        queue.add(b4);
        queue.add(b2);
        queue.add(b1);
        queue.add(b5);
        queue.add(b3);

        for (Book book: queue) {
            System.out.println(book);
        }
        queue.remove();
        System.out.println("after remove:");
        for (Book book: queue) {
            System.out.println(book);
        }
        queue.remove();
        System.out.println("after remove:");
        for (Book book: queue) {
            System.out.println(book);
        }





    }
}
