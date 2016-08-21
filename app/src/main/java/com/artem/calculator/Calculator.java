package com.artem.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    private TextView inputTextView;
    private TextView outputTextView;
    private boolean outputDisplayed;
    private boolean exceptionThrown;
    private StringBuilder displayText;
    private StringBuilder outputText;
    private Set<String> operations;
    private Set<String> functions;
    private Set<String> numbers;
    private boolean containsDecimal;
    private int numberSetLength;
    private int numberOfBrackets;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button one = (Button) findViewById(R.id.one);
        Button two = (Button) findViewById(R.id.two);
        Button three = (Button) findViewById(R.id.three);
        Button four = (Button) findViewById(R.id.four);
        Button five = (Button) findViewById(R.id.five);
        Button six = (Button) findViewById(R.id.six);
        Button seven = (Button) findViewById(R.id.seven);
        Button eight = (Button) findViewById(R.id.eight);
        Button nine = (Button) findViewById(R.id.nine);
        Button zero = (Button) findViewById(R.id.zero);
        Button clear = (Button) findViewById(R.id.clear);
        Button delete = (Button) findViewById(R.id.delete);
        Button add = (Button) findViewById(R.id.add);
        Button subtract = (Button) findViewById(R.id.subtract);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button divide = (Button) findViewById(R.id.divide);
        Button negative = (Button) findViewById(R.id.negative);
        Button equal = (Button) findViewById(R.id.equal);
        Button decimal = (Button) findViewById(R.id.decimal);
        Button powerOfTwo = (Button) findViewById(R.id.power_two);
        Button absoluteValue = (Button) findViewById(R.id.absolute_value);
        Button percent = (Button) findViewById(R.id.percent);
        Button oneOver = (Button) findViewById(R.id.one_over);
        Button eulersNumber = (Button) findViewById(R.id.eulers_number);
        Button pi = (Button) findViewById(R.id.pi);
        Button sin = (Button) findViewById(R.id.sin);
        Button tan = (Button) findViewById(R.id.tan);
        Button cos = (Button) findViewById(R.id.cos);
        Button exponent = (Button) findViewById(R.id.exponent);
        Button squareroot = (Button) findViewById(R.id.square_root);
        Button cuberoot = (Button) findViewById(R.id.cube_root);
        Button log = (Button) findViewById(R.id.log);
        Button naturalLog = (Button) findViewById(R.id.natural_log);
        Button leftBracket = (Button) findViewById(R.id.left_bracket);
        Button rightBracket = (Button) findViewById(R.id.right_bracket);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        negative.setOnClickListener(this);
        equal.setOnClickListener(this);
        decimal.setOnClickListener(this);
        powerOfTwo.setOnClickListener(this);
        absoluteValue.setOnClickListener(this);
        percent.setOnClickListener(this);
        oneOver.setOnClickListener(this);
        eulersNumber.setOnClickListener(this);
        pi.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        exponent.setOnClickListener(this);
        squareroot.setOnClickListener(this);
        cuberoot.setOnClickListener(this);
        log.setOnClickListener(this);
        naturalLog.setOnClickListener(this);
        leftBracket.setOnClickListener(this);
        rightBracket.setOnClickListener(this);

        inputTextView = (TextView) findViewById(R.id.input);
        outputTextView = (TextView) findViewById(R.id.output);

        displayText = new StringBuilder();
        outputText = new StringBuilder();

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
        functions.add("^");
        functions.add("e^");

        numbers = new HashSet<>();
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.one:
                displayNumber("1");
                break;
            case R.id.two:
                displayNumber("2");
                break;
            case R.id.three:
                displayNumber("3");
                break;
            case R.id.four:
                displayNumber("4");
                break;
            case R.id.five:
                displayNumber("5");
                break;
            case R.id.six:
                displayNumber("6");
                break;
            case R.id.seven:
                displayNumber("7");
                break;
            case R.id.eight:
                displayNumber("8");
                break;
            case R.id.nine:
                displayNumber("9");
                break;
            case R.id.zero:
                displayNumber("0");
                break;
            case R.id.add:
                displayOperation("+");
                break;
            case R.id.subtract:
                displayOperation("−");
                break;
            case R.id.multiply:
                displayOperation("*");
                break;
            case R.id.divide:
                displayOperation("/");
                break;
            case R.id.clear:
                clearText();
                break;
            case R.id.delete:
                deletePreviousValue();
                break;
            case R.id.left_bracket:
                formatBrackets("(");
                break;
            case R.id.right_bracket:
                formatBrackets(")");
                break;
            case R.id.negative:
                formatModifiersAndSymbols("-");
                break;
            case R.id.decimal:
                formatModifiersAndSymbols(".");
                break;
            case R.id.pi:
                formatModifiersAndSymbols("π");
                break;
            case R.id.percent:
                formatModifiersAndSymbols("%");
                break;
            case R.id.absolute_value:
                formatFunctions("abs");
                break;
            case R.id.eulers_number:
                formatFunctions("e^");
                break;
            case R.id.sin:
                formatFunctions("sin");
                break;
            case R.id.cos:
                formatFunctions("cos");
                break;
            case R.id.tan:
                formatFunctions("tan");
                break;
            case R.id.log:
                formatFunctions("log");
                break;
            case R.id.natural_log:
                formatFunctions("ln");
                break;
            case R.id.square_root:
                formatFunctions("√");
                break;
            case R.id.cube_root:
                formatFunctions("³√");
                break;
            case R.id.exponent:
                formatFunctions("^");
                break;
            case R.id.power_two:
                formatFunctions("^ 2");
                break;
            case R.id.one_over:
                formatFunctions("1 / x");
                break;
            case R.id.equal:
                findTotal();
                break;
        }
    }

    //Displays the pressed number, resets display if there is a total shown
    public void displayNumber(String numberToAdd){
        if(outputDisplayed)
            clearText();

        if(multiplicationNeeded(numberToAdd))
            displayText.append(" * ");

        displayText.append(numberToAdd);
        numberSetLength++;
        displayFixedInputText();
    }

    //True if: displayText is bigger than one, previous value is π, %, or a number set thats
    //bigger than 0 and the tokenToAdd is NOT a number.
    //False if there is a number set and the token to add is a number
    public boolean multiplicationNeeded(String tokenToAdd){
        if(displayText.length() > 0) {
            if(tokenOneBefore().equals("π") || tokenOneBefore().equals("%")) {
                return true;
            }else if(numberSetLength > 0) {
                if(!numbers.contains(tokenToAdd))
                    return true;
            }
        }
        return false;
    }

    //Finds the char / token at the end of the text
    public String tokenOneBefore(){
        String trimmedText = trimmedDisplayText();
        return trimmedText.charAt(trimmedText.length()-1) + "";
    }

    //Returns displayText without any spaces
    public String trimmedDisplayText(){
        return displayText.toString().replaceAll(" ", "");
    }

    //Trims any white space and displays the text
    public void displayFixedInputText(){
        String trimmedDisplayText = displayText.toString().replaceAll(" ", "");
        inputTextView.setText(trimmedDisplayText);
    }

    //Adds + - * / to display
    public void displayOperation(String operationToAdd){
        useTotalForCalculation();

        if(!outputDisplayed)
            resetTrackers();

        //Adds the operation if the length isn't 0 or if the previous value isnt an operation
        if(trimmedDisplayText().length() == 0 || operations.contains(tokenOneBefore()))
            return;
        else
            displayText.append(" " + operationToAdd + " ");

        displayFixedInputText();
    }

    //Checks for if a total is displayed, if it is then sets it up for an operation
    public void useTotalForCalculation(){
        if(outputDisplayed){
            resetInput();
            displayText.append(total + "");
            numberSetLength = trimmedDisplayText().length();
            numberOfBrackets = 0;
            containsDecimal = true;
            resetOutput();
            displayFixedInputText();
        }else if(exceptionThrown){
            clearText();
            exceptionThrown = false;
        }
    }

    public void resetTrackers(){
        numberSetLength = 0;
        containsDecimal = false;
    }

    //Clears any text displayed in input
    public void resetInput(){
        displayText.setLength(0);
        inputTextView.setText(displayText);
    }

    //Clears any text displayed in output
    public void resetOutput(){
        outputText.setLength(0);
        outputTextView.setText(outputText);
        outputDisplayed = false;
    }

    //Clears all values
    public void clearText(){
        resetInput();
        resetOutput();
        resetTrackers();
        outputDisplayed = false;
        numberOfBrackets = 0;
    }

    //Deletes last token
    public void deletePreviousValue(){
        if(exceptionThrown || outputDisplayed)
            clearText();

        if(displayText.length() != 0) {
            if(tokenOneBefore().equals("("))
                numberOfBrackets--;
            else if(tokenOneBefore().equals(")"))
                numberOfBrackets++;
            else if(tokenOneBefore().equals("."))
                containsDecimal = false;

            //Deletes a token 2 spots over if its after an operation with extra space, else
            //it deletes the one at the very end
            if(tokenOneBefore().equals(" "))
                displayText.deleteCharAt(displayText.length() - 2);
            else
                displayText.deleteCharAt(displayText.length() - 1);
        }
        displayFixedInputText();
    }

    //Formats brackets
    public void formatBrackets(String bracket){
        useTotalForCalculation();

        if(bracket.equals("(")){
            if(numberSetLength == 0 && !multiplicationNeeded(bracket))
                displayText.append(" " + bracket + " ");
            else
                displayText.append(" * " + bracket + " ");
            numberOfBrackets++;
        }else if(bracket.equals(")") && numberOfBrackets != 0){
            displayText.append(" " + bracket + " ");
            numberOfBrackets--;
        }
        resetTrackers();
        displayFixedInputText();
    }

    //Formats decimals, negative sign, percent, and pi
    public void formatModifiersAndSymbols(String text){
        useTotalForCalculation();

        if(text.equals(".")){
            if(containsDecimal) {
                return;
            }else if(numberSetLength == 0) {
                displayText.append("0.");
            }else {
                displayText.append(".");
            }
            containsDecimal = true;
        }else if(text.equals("-") && !outputDisplayed){
            displayText.append("-");
        }else if(text.equals("%")){
            displayText.append(" " + text + " ");
        }else if(text.equals("π")){
            if(multiplicationNeeded(text))
                displayText.append(" * " + text + " ");
            else if(numberSetLength == 0)
                displayText.append(" " + text + " ");
        }
        displayFixedInputText();
    }

    //Formats all functions, ^, 1/x, sin cos tan, abs, sqrt, cubert, log, ln, e^
    //Multiplies if the token before it has a number value
    public void formatFunctions(String token){
        useTotalForCalculation();

        if(token.equals("^ 2")) {
            displayText.append(" " + token + " ");
        }else if(token.equals("1 / x")) {
            if (numberSetLength == 0)
                displayText.append(" 1 / ( ");
            else
                displayText.append(" * 1 / ( ");
            numberOfBrackets++;
        }else if(token.equals("abs") || functions.contains(token)) {
            if (numberSetLength == 0 || token.equals("^")) {
                displayText.append(" " + token + " ( ");
            } else {
                displayText.append(" * " + token + " ( ");
            }
            numberOfBrackets++;
        }
        resetTrackers();
        displayFixedInputText();
    }

    public void findTotal(){
        while(numberOfBrackets > 0) {
            displayText.append(" )");
            numberOfBrackets--;
        }

        InfixToPostfix convert = new InfixToPostfix(displayText.toString());
        String postfix = convert.convertToPostfix();

        try {
            calculate((postfix));
            outputTextView.setText(total + "");
            outputDisplayed = true;
        }catch(EmptyStackException exception){
            outputTextView.setText("Invalid Format");
            resetTrackers();
            exceptionThrown = true;
        }
    }

    /*Calculates total, if its a number its pushed to the stack.
      if its an operation it pushes 2 numbers off the stack and applies the operation and
      pushes back to the stack.
      Any other type pops one number off the stack and applies the number.
      Calculations are in RADIANS
     */
    public void calculate(String postfix) throws EmptyStackException{
        Stack<Double> stack = new Stack<>();

        //If its a number it pushes to the stack, if its an operator then it pops two numbers off
        //the stack (One number if its a function) and applies the operation and then
        //pushes it back to the stack
        for (String token : postfix.split(" ")) {
            double firstNum;
            double secondNum;

            try {
                stack.push(Double.parseDouble(token));
            } catch (NumberFormatException error) {
                if(operations.contains(token) || token.equals("^")){
                    secondNum = stack.pop();
                    firstNum = stack.pop();

                    if (token.equals("*")) {
                        stack.push(firstNum * secondNum);
                    } else if (token.equals("/")) {
                        stack.push(firstNum / secondNum);
                    } else if (token.equals("+")) {
                        stack.push(firstNum + secondNum);
                    } else if (token.equals("−")) {
                        stack.push(firstNum - secondNum);
                    } else if(token.equals("^")){
                        stack.push(Math.pow(firstNum, secondNum));
                    }
                }else if(token.equals("sin")){
                    stack.push(Math.sin(stack.pop()));
                }else if(token.equals("cos")) {
                    stack.push(Math.cos(stack.pop()));
                }else if(token.equals("tan")) {
                    stack.push(Math.tan(stack.pop()));
                }else if(token.equals("log")) {
                    stack.push(Math.log10(stack.pop()));
                }else if(token.equals("ln")) {
                    stack.push(Math.log(stack.pop()));
                }else if(token.equals("√")) {
                    stack.push(Math.sqrt(stack.pop()));
                }else if(token.equals("³√")) {
                    stack.push(Math.cbrt(stack.pop()));
                }else if(token.equals("e^")){
                    stack.push(Math.exp(stack.pop()));
                }else if(token.equals("π")){
                    stack.push(Math.PI);
                }else if(token.equals("%")){
                    stack.push(stack.pop() / 100);
                }
            }
        }
        total = stack.pop();
    }
}