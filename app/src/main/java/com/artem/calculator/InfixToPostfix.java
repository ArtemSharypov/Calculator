package com.artem.calculator;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class InfixToPostfix {

    private String infix;
    private Set<String> operations;
    private Set<String> functions;

    public InfixToPostfix(String infix){
        this.infix = infix;

        operations = new HashSet<>();
        operations.add("+");
        operations.add("−");
        operations.add("*");
        operations.add("/");

        functions = new HashSet<>();
        functions.add("sin");
        functions.add("cos");
        functions.add("tan");
        functions.add("log");
        functions.add("ln");
        functions.add("√");
        functions.add("³√");
        functions.add("e^");
        functions.add("^");
    }

    public String convertToPostfix() throws EmptyStackException{
        Stack<String> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        String[] infixSplit = infix.split(" ");

        //Uses Shunting Yard Algorithm
        //Adds any numbers & decimals to postfix, any matching bracket set(s) are removed
        //Any operations / functions are done in precedence
        for(String token: infixSplit){
            if(operations.contains(token)){
                while(!stack.isEmpty() && comparePrecedence(token, stack.peek()))
                    postfix.append(stack.pop() + " ");

                stack.push(token);
            }else if(token.equals("(")){
                stack.push(token);
            }else if(token.equals(")")){
                while(!stack.peek().equals("("))
                    postfix.append(stack.pop() + " ");
                stack.pop(); //pops the "(" off the stack
            }else if(functions.contains(token)){
                stack.push(token);
            }else{
                postfix.append(token + " ");
            }
        }

        while(!stack.isEmpty()){
            postfix.append(stack.pop() + " ");
        }
        return postfix.toString();
    }

    private boolean comparePrecedence(String operationOne, String operationOnStack){
        return precedence(operationOne) <= precedence(operationOnStack);
    }

    private int precedence(String operation){

        if(operation.equals("^") || operation.equals("%"))
            return 3;
        else if(operation.equals("*") || operation.equals("/"))
            return 2;
        else if(operation.equals("+") || operation.equals("−"))
            return 1;
        else //Any remaining functions, any that are within function Set or are not the above
            return 0;
    }
}
