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
    private StringBuilder displayText;
    private StringBuilder outputText;
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
    }

    @Override
    public void onClick(View view) {

        //possibly not reset if a operation is pressed, instead do that operation to the findTotal
        //if so probably move this to display numbers, then add conditions to the methods
        if(outputDisplayed) {
            clearText();
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
                clearText();;
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

    public void displayNumber(String numberToAdd){
        if(outputDisplayed) {
            clearText();
            outputDisplayed = false;
        }

        if(numberSetLength == 0){
            startPositionOfNumberSet = displayText.length();
        }

        displayText.append(numberToAdd);
        numberSetLength++;
        displayFixedInputText();
    }

    public void displayFixedInputText(){
        String trimmedDisplayText = displayText.toString().replaceAll(" ", "");
        inputTextView.setText(trimmedDisplayText);
    }

    //need to add findTotal support, if operation clicked then apply to findTotal
    public void displayOperation(String operationToAdd){
        resetTrackers();
        String trimmedDisplayText = inputTextView.toString();
        String oneSpotBefore = trimmedDisplayText.charAt(trimmedDisplayText.length()-1)+"";

        if(trimmedDisplayText.length() == 0)
            return;
        else if(operations.contains(oneSpotBefore))
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

    public void clearText(){
        displayText.setLength(0);
        outputTextView.setText("");
        inputTextView.setText(displayText);
        resetTrackers();
        numberOfBrackets = 0;
    }

    public void deletePreviousValue(){
        if(displayText.length() != 0)
            displayText.deleteCharAt(displayText.length()-1);
        displayFixedInputText();
    }

    //need to add findTotal support, if bracket clicked then apply to findTotal
    public void formatBrackets(String bracket){
        if(bracket.equals("(")){
            if(numberSetLength == 0)
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

    //need to add findTotal support, if modifier/symbol clicked then apply to findTotal
    public void formatModifiersAndSymbols(String text){
        if(text.equals(".")){
            if(containsDecimal) {
                return;
            }else if(numberSetLength == 0) {
                displayText.append("0.");
            }else {
                displayText.append(".");
            }
            containsDecimal = true;
        }else if(text.equals("-")){
            if(isNegative){
                isNegative = false;
                displayText.deleteCharAt(negativeSignPos);
            }else{ //Add a negative sign infront of the numbers
                displayText.insert(startPositionOfNumberSet, "-");
                isNegative = true;
                negativeSignPos = startPositionOfNumberSet;
            }
        }else if(text.equals("%")){
            displayText.append(" " + text + " ");
        }else if(text.equals("π")){
            if(displayText.length() > 0 && multiplicationNeeded())
                displayText.append(" * " + text + " ");
            else if(numberSetLength == 0)
                displayText.append(" " + text + " ");
        }
        displayFixedInputText();
    }

    public boolean multiplicationNeeded(){
        String trimmedText = displayText.toString().replaceAll(" ", "");

        if (displayText.length() > 0){
            String oneSpotBefore = trimmedText.charAt(trimmedText.length()-1) + "";
            return oneSpotBefore.equals("π");
        }
        return false;
    }

    //need to add findTotal support, if function clicked then apply to findTotal
    public void formatFunctions(String token){
        if(token.equals("^ 2")) {
            displayText.append(" " + token + " ");
        }else if(token.equals("1 / x ")) {
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

    //need changing, allow total to be moved to display
    public void findTotal(){
        while(numberOfBrackets > 0) {
            displayText.append(" ) ");
            numberOfBrackets--;
        }

        InfixToPostfix convert = new InfixToPostfix(displayText.toString());
        String postfix = convert.convertToPostfix();
        try {
            outputDisplayed = true;
            resetTrackers();//might not need
            double total = calculate((postfix));
            outputTextView.setText(total + "");
        }catch(EmptyStackException exception){
            outputTextView.setText("Invalid Format");
        }
        //outputTextView.setText(postfix); //for testing
    }

    //Needs refactoring
    public double calculate(String postfix) throws EmptyStackException{
        double total;
        Stack<Double> stack = new Stack<>();

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
                } else if (token.equals("/")) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum / secondNum);
                } else if (token.equals("+")) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum + secondNum);
                } else if (token.equals("−")) {
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(firstNum - secondNum);
                } else if(token.equals("^")){
                    secondNum = stack.pop();
                    firstNum = stack.pop();
                    stack.push(Math.pow(firstNum, secondNum));
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
        return total;
    }

    //might be better / cleaner route
    public double calculateFunction(String function, double number){

        if(function.equals("sin"))
            return Math.sin(number);
        else if(function.equals("cos"))
            return Math.cos(number);
        else if(function.equals("tan"))
            return Math.tan(number);
        else if(function.equals("log"))
            return Math.log10(number);
        else if(function.equals("ln"))
            return Math.log(number);
        else if(function.equals("√"))
            return Math.sqrt(number);
        else
            return Math.cbrt(number);
    }
}