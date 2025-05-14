package com.vladproduction.app02.foundations;

public class Implementation implements Contract{
    //implementation of abstract methods from Contract Interface

    @Override
    public void term1() {
        System.out.println("Implementation: term1");
    }

    @Override
    public void term2() {
        System.out.println("Implementation: term2");
    }

    @Override
    public void extendedTerm() {
        System.out.println("Implementation: extended");
    }
}
