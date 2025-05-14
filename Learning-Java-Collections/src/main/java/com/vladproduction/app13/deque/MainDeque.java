package com.vladproduction.app13.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class MainDeque {
    public static void main(String[] args) throws InterruptedException {

        Deque<String> messageStack = new ArrayDeque<>();

        messageStack.push("Message1");
        print(messageStack);
        TimeUnit.SECONDS.sleep(1);

        messageStack.push("Message2");
        print(messageStack);
        TimeUnit.SECONDS.sleep(1);

        messageStack.push("Message3");
        print(messageStack);
        TimeUnit.SECONDS.sleep(1);

        messageStack.push("Message4");
        print(messageStack);
        TimeUnit.SECONDS.sleep(1);


    }

    public static void print(Deque<String> deque) {

        System.out.format("%n--Deque Contents--%n");

        int x = 0;
        for(String msg : deque) {
            System.out.format("%x: %s %s %n", x++, msg, x == 1 ? "(Top)":"");
        }

        System.out.println("");

    }
}
