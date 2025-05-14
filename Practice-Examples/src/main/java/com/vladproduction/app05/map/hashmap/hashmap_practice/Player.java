package com.vladproduction.app05.map.hashmap.hashmap_practice;

import java.util.Objects;

public class Player {
        private String name;
        private int number;
        private double weight;

        public Player(String name, int number, double weight) {
            this.name = name;
            this.number = number;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }

        public double getWeight() {
            return weight;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Player player = (Player) object;
            return number == player.number && Double.compare(weight, player.weight) == 0 && Objects.equals(name, player.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, number, weight);
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    ", weight=" + weight +
                    '}';
        }
    }