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

    private TextView inputBox;
    private TextView outputBox;
    private boolean outputDisplayed;
    private StringBuilder displayText;
    private Set<String> operations;
    private Set<String> functions;
    private boolean containsDecimal;
    private int numberSetLength;
    private int startPositionOfNumberSet;
    private boolean isNegative;
    private int negativeSignPos;
    private int numberOfBrackets;

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
        Button squareroot = (Button) findViewById(R.id.squareroot);
        Button cuberoot = (Button) findViewById(R.id.cuberoot);
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

        inputBox = (TextView) findViewById(R.id.input);
        outputBox = (TextView) findViewById(R.id.output);
        displayText = new StringBuilder(inputBox.getText());

        operations = new HashSet<String>();
        operations.add("+");
        operations.add("−");
        operations.add("*");
        operations.add("/");

        //might not need
        functions = new HashSet<String>();
        functions.add("sin");
        functions.add("cos");
        functions.add("tan");
        functions.add("log");
        functions.add("ln");
        functions.add("sqroot");
        functions.add("cuberoot");
        functions.add("^");
        functions.add("^2");
    }

    @Override
    public void onClick(View view) {

        if(outputDisplayed) {
            displayModifier("clear");
            outputDisplayed = false;
        }

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
                displayModifier("clear");
                break;
            case R.id.delete:
                displayModifier("del");
                break;
            case R.id.negative:
                displayModifier("-");
                break;
            case R.id.equal:
                displayModifier("=");
                break;
            case R.id.decimal:
                displayModifier(".");
                break;
            case R.id.left_bracket:
                displayModifier("(");
                break;
            case R.id.right_bracket:
                displayModifier(")");
                break;
            case R.id.absolute_value:
                displayModifier("abs");
                break;
            case R.id.eulers_number:
                displaySymbol("e");
                break;
            case R.id.pi:
                displaySymbol("pi");
                break;
            case R.id.sin:
                displayFunction("sin");
                break;
            case R.id.cos:
                displayFunction("cos");
                break;
            case R.id.tan:
                displayFunction("tan");
                break;
            case R.id.log:
                displayFunction("log");
                break;
            case R.id.natural_log:
                displayFunction("ln");
                break;
            case R.id.squareroot:
                displayFunction("√");
                break;
            case R.id.cuberoot:
                displayFunction("³√");
                break;
            case R.id.exponent:
                displayFunction("^");
                break;
            case R.id.power_two:
                displayFunction("^ 2");
                break;
            case R.id.percent:
                //need to add
                break;
            case R.id.one_over:
                //need to add
                break;
        }
    }

    public void displayNumber(String numberToAdd){
        if(numberSetLength == 0){
            startPositionOfNumberSet = displayText.length();
        }
        displayText.append(numberToAdd);
        numberSetLength++;
        displayFixedInputText();
    }

    public void displayFixedInputText(){
        String trimmedDisplayText = displayText.toString().replaceAll(" ", "");
        inputBox.setText(trimmedDisplayText);
    }

    public void displayOperation(String operationToAdd){
        resetTrackers();
        String trimmedDisplayText = displayText.toString().replaceAll(" ", ""); //need to fix/better solution

        if(trimmedDisplayText.length() == 0)
            return;
        else if(operations.contains(trimmedDisplayText.charAt(trimmedDisplayText.length() - 1)+""))
            return;
        else
            displayText.append(" " + operationToAdd + " ");

        displayFixedInputText();
    }

    public void resetTrackers(){
        numberSetLength = 0;
        containsDecimal = false;
        isNegative = false;
        negativeSignPos = 0;
        startPositionOfNumberSet = 0;
    }

    //maybe split up somehow
    public void displayModifier(String modifierToAdd){
        if(modifierToAdd.equals(".")){
            if(containsDecimal)
                return;
            else if(numberSetLength == 0)
                displayText.append("0.");
            else
                displayText.append(".");
            containsDecimal = true;
        }else if(modifierToAdd.equals("-")){
            if(isNegative){
                isNegative = false;
                displayText.deleteCharAt(negativeSignPos);
            }else{ //Add a negative sign infront of the numbers
                displayText.insert(startPositionOfNumberSet, "-");
                isNegative = true;
                negativeSignPos = startPositionOfNumberSet;
            }
        }else if(modifierToAdd.equals("del")){
            if(displayText.length() != 0)
                displayText.deleteCharAt(displayText.length()-1);
        }else if(modifierToAdd.equals("clear")){
            displayText.setLength(0);
            outputBox.setText("");
            resetTrackers();
        }else if(modifierToAdd.equals("=")){
            equals();
        }else if(modifierToAdd.equals("(")){
            formatSymbolsBrackets(modifierToAdd);
            numberOfBrackets++;
            resetTrackers();
        }else if(modifierToAdd.equals(")") && numberOfBrackets != 0){
            displayText.append(" " + modifierToAdd + " ");
            numberOfBrackets--;
            resetTrackers();
        }else if(modifierToAdd.equals("abs")){
            formatFunctionsAndAbsolute(modifierToAdd);
        }

        displayFixedInputText();
    }

    public void displaySymbol(String symbolToAdd){
        if(symbolToAdd.equals("pi"))
            formatSymbolsBrackets("π");
        else //for eulers number. probably should be under functions
            formatFunctionsAndAbsolute("e ^");

        displayFixedInputText();
    }

    //need better name
    public void formatSymbolsBrackets(String token){
        if(numberSetLength == 0)
            displayText.append(" " + token + " ");
        else
            displayText.append(" * " + token + " ");
    }

    //need better name
    public void formatFunctionsAndAbsolute(String function){
        if(function.equals("^ 2"))
            displayText.append(" " + function + " ");
        else if(numberSetLength == 0 || function.equals("^"))
            displayText.append(" " + function + " ( ");
        else
            displayText.append(" * " + function + " ( ");

        numberOfBrackets++;
        resetTrackers();
    }

    public void displayFunction(String functionToAdd){
        formatFunctionsAndAbsolute(functionToAdd);
        displayFixedInputText();
    }

    public void equals(){
        InfixToPostfix convert = new InfixToPostfix(displayText.toString());
        String postfix = convert.convertToPostfix();

        try {
            outputDisplayed = true;
            resetTrackers();
            double total = calculate((postfix));
            outputBox.setText(total + "");
        }catch(EmptyStackException exception){
            outputBox.setText("Invalid Format");
        }

        //outputBox.setText(postfix); //for testing
    }

    public double calculate(String postfix) throws EmptyStackException{
        double total;
        Stack<Double> stack = new Stack<Double>();

        for (String token : postfix.split(" ")) {
            //if its a number push to the stack, if its an operator then pop two numbers off
            //apply the operation and then push to stack again
            double firstNum;
            double secondNum;

            try {
                stack.push(Double.parseDouble(token));
            } catch (NumberFormatException error) {
                if (token.equals("*")) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum * secondNum);
                } else if (token.equals("/")){
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum / secondNum);
                }else if (token.equals("+")){
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum + secondNum);
                }else if(token.equals("−")) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum - secondNum);
                }
            }
        }
        total = stack.pop();
        return total;
    }

}
