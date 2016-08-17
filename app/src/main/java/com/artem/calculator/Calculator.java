package com.artem.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    private TextView display;
    private StringBuilder displayText;
    private Set<String> operations;
    private boolean containsDecimal;
    private int numberSetLength;
    private int startPositionOfNumberSet;
    private boolean isNegative;
    private int negativeSignPos;

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

        display = (TextView) findViewById(R.id.display);
        displayText = new StringBuilder(display.getText());

        operations = new HashSet<String>();
        operations.add("+");
        operations.add("−");
        operations.add("*");
        operations.add("/");
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.one:
                formatForDisplay("1");
                break;
            case R.id.two:
                formatForDisplay("2");
                break;
            case R.id.three:
                formatForDisplay("3");
                break;
            case R.id.four:
                formatForDisplay("4");
                break;
            case R.id.five:
                formatForDisplay("5");
                break;
            case R.id.six:
                formatForDisplay("6");
                break;
            case R.id.seven:
                formatForDisplay("7");
                break;
            case R.id.eight:
                formatForDisplay("8");
                break;
            case R.id.nine:
                formatForDisplay("9");
                break;
            case R.id.zero:
                formatForDisplay("0");
                break;
            case R.id.clear:
                displayText.setLength(0);
                display.setText(displayText);
                break;
            case R.id.delete:
                if(displayText.length() != 0)
                    displayText.deleteCharAt(displayText.length()-1);
                display.setText(displayText);
                break;
            case R.id.add:
                formatForDisplay("+");
                break;
            case R.id.subtract:
                formatForDisplay("−");
                break;
            case R.id.multiply:
                formatForDisplay("*");
                break;
            case R.id.divide:
                formatForDisplay("/");
                break;
            case R.id.negative:
                formatForDisplay("-");
                break;
            case R.id.equal:
                equals();
                break;
            case R.id.decimal:
                formatForDisplay(".");
                break;
        }
    }

    //Maybe need to split method up into various things?
    //seperate numbers and operations and "other"
    //formatNumbers / formatOperations / formatModifiers / format functions
    //possibly add a method that'll trim any white space when it displays it
    public void formatForDisplay(String textToAdd){

        if(operations.contains(textToAdd)){ //Could add in the brackets to this part to prevent repeating stuff or make a resetNumberSet method?
            numberSetLength = 0;
            containsDecimal = false;
            isNegative = false;
            negativeSignPos = 0;
            startPositionOfNumberSet = 0;

            if(displayText.length() == 0 ) //maybe a method to check if its an okay input and checks for decimals before it and such
                return;
            else if(operations.contains(displayText.charAt(displayText.length() - 1)+""))
                return;
            else
                displayText.append(" " + textToAdd + " ");
        }else if(textToAdd.equals(".")){
            if(containsDecimal)
                return;
            else if(numberSetLength == 0)
                displayText.append("0.");
            else
                displayText.append(".");
            containsDecimal = true;
        }else if(textToAdd.equals("-")){
            if(isNegative){
                isNegative = false;
                displayText.deleteCharAt(negativeSignPos);
            }else{
                displayText.insert(startPositionOfNumberSet, "-"); //Add a negative sign infront of the numbers
                isNegative = true;
                negativeSignPos = startPositionOfNumberSet;
            }
        }else{
            if(numberSetLength == 0){
                startPositionOfNumberSet = displayText.length();
            }
            displayText.append(textToAdd);
            numberSetLength++;
        }

        String trimmedDisplayText = displayText.toString().replaceAll(" ", "");
        display.setText(trimmedDisplayText);
    }

    public void equals(){
        InfixToPostfix convert = new InfixToPostfix(displayText.toString());
        String postfix = convert.convertToPostfix();
        double total = calculate((postfix));

        displayText.setLength(0);
        //display.setText(postfix); //for testing
        display.setText(total + "");
    }

    public double calculate(String postfix) {
        double total;
        Stack<Double> stack = new Stack<Double>();

        for (String token : postfix.split(" ")) {
            //if its a number push to the stack, if its an operator then pop two numbers off and then push to stack again
            Double tokenValue = null;
            double firstNum;
            double secondNum;

            try {
                tokenValue = Double.parseDouble(token);
            } catch (NumberFormatException error) {
            }

            if (tokenValue != null) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("*")) {
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
        total = stack.pop();
        return total;
    }

}
