package com.vladproduction.app02.list.stack;

import java.util.Stack;

public class BalancedParenthesesByStack {
    public static void main(String[] args) {

        System.out.println(isBalanced("(a+b)")); // true
        System.out.println(isBalanced("((a+b)")); // false

    }

    private static boolean isBalanced(String str){
        Stack<Character> stack = new Stack<>();
        char[] charArray = str.toCharArray();
        for (char ch : charArray) {
            if(ch == '('){
                stack.push(ch);
            } else if (ch == ')') {
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}
