package com.vladproduction.app01.array.excercises_array;

public class InitArrayDemo {
    public static void main(String[] args) {
        InitArrayDemo initArrayDemo = new InitArrayDemo();
        initArrayDemo.run();
    }

    private void run() {
        // Create []:
        int[] nums={1,3,7,2,8,5,9};
        char[] symbs={'A','Z','Q','R'};
        String[] txts={"red","white","black"};
        // show content []:
        System.out.println("[] nums:");
        for(int k=0;k<nums.length;k++){
            System.out.print(nums[k]+" ");
        }
        System.out.println("\n[] symbs:");
        for(int k=0;k<symbs.length;k++){
            System.out.print(symbs[k]+" ");
        }
        System.out.println("\n[] txts:");
        for(int k=0;k<txts.length;k++){
            System.out.print(txts[k]+" ");
        }
        System.out.println("");

    }
}
