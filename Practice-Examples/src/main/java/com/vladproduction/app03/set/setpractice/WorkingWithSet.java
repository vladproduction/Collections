package com.vladproduction.app03.set.setpractice;

import java.util.*;

public class WorkingWithSet {
    public static void main(String[] args) {
        //Set<Ball> balls = new EnumSet<>();
        //Set<Ball> balls = new TreeSet<>(); //order guaranteed (new TreeMap)
        Set<Ball> balls = new HashSet<>(); //no order here (new HashMap)
        balls.add(new Ball("red"));
        balls.add(new Ball("black"));
        balls.add(new Ball("white"));
        balls.add(new Ball("red"));
        balls.remove(new Ball("black"));
        System.out.println(balls.size()); //no duplicate
        balls.forEach(System.out::println); //no order
    }

    static class Ball{ //size()=4 and after overridden equals&hashCode size()=3 again
        private final String color;
        public Ball(String color) {
            this.color = color;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Ball ball = (Ball) object;
            return Objects.equals(color, ball.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }
        //to be readable override toString();

        @Override
        public String toString() {
            return "Ball{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }

//        record Ball(String color){} //size()=3
}
