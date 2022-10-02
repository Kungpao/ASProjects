package jp.ac.dentaku03_04_pi52a_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private int value = 0;
    private double dValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btn instance
        Button[] numBtn = new Button[10];
        numBtn[0] = findViewById(R.id.zero);
        numBtn[1] = findViewById(R.id.one);
        numBtn[2] = findViewById(R.id.two);
        numBtn[3] = findViewById(R.id.three);
        numBtn[4] = findViewById(R.id.four);
        numBtn[5] = findViewById(R.id.five);
        numBtn[6] = findViewById(R.id.six);
        numBtn[7] = findViewById(R.id.seven);
        numBtn[8] = findViewById(R.id.eight);
        numBtn[9] = findViewById(R.id.nine);

        //btn click listeners
        for(int i = 0; i<numBtn.length; i++){
            numBtn[i].setOnClickListener(view -> {

                numBtnClicked(view);
            });
        }

        // operation buttons
        //arithmetic
        Button plusButton = findViewById(R.id.addition);
        Button minusButton = findViewById(R.id.subtraction);
        Button multiplicationButton = findViewById(R.id.multiply);
        Button divisionButton = findViewById(R.id.div);

        //logic
        Button equalButton = findViewById(R.id.equal);
        Button backButton = findViewById(R.id.backspace);
        Button clearButton = findViewById(R.id.clear);
        Button signButton = findViewById(R.id.sign);
        Button dotButton = findViewById(R.id.dot);

        //operation buttons ClickEventsListeners
        plusButton.setOnClickListener(view -> {
            opClicked(view);
        });
        minusButton.setOnClickListener(view -> {
            opClicked(view);
        });
        multiplicationButton.setOnClickListener(view -> {
            opClicked(view);
        });
        divisionButton.setOnClickListener(view -> {
            opClicked(view);
        });



        equalButton.setOnClickListener(view -> {
            opClicked(view);
        });
        backButton.setOnClickListener(view -> {
            opClicked(view);
        });
        clearButton.setOnClickListener(view -> {
            opClicked(view);
        });
        signButton.setOnClickListener(view -> {
            opClicked(view);
        });
        dotButton.setOnClickListener(view -> {
            opClicked(view);
        });


    }

    private void opClicked(View view) {

        Button opCode = (Button) view;

        String opCodeStr = opCode.getText().toString();

        if(     opCodeStr.equals("+") ||
                opCodeStr.equals("-") ||
                opCodeStr.equals("*") ||
                opCodeStr.equals("÷")       ){

            TextView tvDisplay = findViewById(R.id.displayArea);
            String valueStr = tvDisplay.getText().toString();

            value = Integer.parseInt(valueStr);

            tvDisplay.setText("0");
        }else if(opCodeStr.equals("=")){
            TextView tvDisplay = findViewById(R.id.displayArea);
            String valueStr = tvDisplay.getText().toString();

            //teacher's working example
            //value += Integer.parseInt(valueStr);

            //My try
            //we are passing string of operation code, and string of a prev. value
            equalOp(opCodeStr,valueStr);

            tvDisplay.setText(String.valueOf(value));
        }else if(opCodeStr.equals("←")){
            //take display id
            TextView tvDisplay = findViewById(R.id.displayArea);
            //delete last entered number by division by 10
            value = value / 10;
            //show resulted value
            tvDisplay.setText(String.valueOf(value));
        }else if(opCodeStr.equals("C")){
            value = 0;
            TextView tvDisplay = findViewById(R.id.displayArea);
            tvDisplay.setText(String.valueOf(value));
        }else if(opCodeStr.equals("±")){
            value = value * -1;
            TextView tvDisplay = findViewById(R.id.displayArea);
            tvDisplay.setText(String.valueOf(value));
        }else if(opCodeStr.equals(".")){
            //not yet finished
            //this will change displayed number to a double.
            //the problem still is you can't operate different types in the same equation
            //idea to think: maybe put here few if cases to handle different combinations ?
            //idea to think: maybe create global dValue to handle cases when double appears
            //idea to think: set a flag to track if double value going to be used

            //when . is pressed we are switching to double value holder and clean int value
            TextView tvDisplay = findViewById(R.id.displayArea);
            dValue = Double.parseDouble(tvDisplay.getText().toString());
            value = 0;
            tvDisplay.setText(String.valueOf(dValue));


        }


    }
    //this method takes string of operation Code and string of the previous value.
    //then check which operation has occured, and decides an aproriet operation to make
    //this method is purely cosmetic. For the beaty of the code !
    private void equalOp (String opCodeStr, String valueStr){
        //can i use switch here ?
        if(opCodeStr.equals("+")){
            value += Integer.parseInt(valueStr);
        }else if(opCodeStr.equals("-")){
            value -= Integer.parseInt(valueStr);
        }else if(opCodeStr.equals("*")){
            value *= Integer.parseInt(valueStr);
        }else if(opCodeStr.equals("÷")){
            value /= Integer.parseInt(valueStr);
        }else{
            //error block ?
            //maybe to write some str to the log or make it
        }
    }

    private void numBtnClicked(View view){

        Button btn = (Button)view;
        String str = btn.getText().toString();

        TextView tvDisplay = findViewById(R.id.displayArea);

        int value = Integer.parseInt(tvDisplay.getText().toString());
        //to put it simple, every time we enter a number we multiply previous value by 10 to "jump"
        //then we add new value to the back
        value = value * 10 + Integer.parseInt(str);

        tvDisplay.setText(String.valueOf(value));

    }
}