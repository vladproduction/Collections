package com.vladproduction.app02.foundations;

public class App {
    public static void main(String[] args) {

        Contract contract = new Implementation();
        printTerms(contract);

    }

    private static void printTerms(Contract contract){

        contract.term1();
        contract.term2();
        contract.extendedTerm();

    }
}
