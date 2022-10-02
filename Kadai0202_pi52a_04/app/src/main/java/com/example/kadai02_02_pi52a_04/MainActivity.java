package com.example.kadai02_02_pi52a_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBmi(View view) {

        EditText etHeight = findViewById(R.id.height);
        String strHeight = etHeight.getText().toString();

        if(!validateEmptyText(strHeight)){
            etHeight.setError("身長入力してください");
            return;
        }else if(!validateRangeHeight(strHeight)){
            etHeight.setError("0～300で入力してください!");
            return;
        }

        EditText etWeight = findViewById(R.id.weight);
        String strWeight = etWeight.getText().toString();

        if(!validateEmptyText(strWeight)){
            etWeight.setError("大量入力してください");
            return;
        }else if (!validateRangeWeight(strWeight)){
            etWeight.setError("0～200で入力してください!");
            return;
        }

        double dHeight = Double.parseDouble(strHeight);
        double dWeight = Double.parseDouble(strWeight);

        double bmi = dWeight / ((dHeight / 100) * (dHeight / 100));

        bmi = Math.round(bmi * 10.0) / 10.0;


        String sBmi = "".valueOf(bmi);

        TextView tvBmiStr = findViewById(R.id.textView13);
        tvBmiStr.setText(checkBmiRange(bmi));

        TextView tvBmi = findViewById(R.id.textView7);
        tvBmi.setText(sBmi);


        double stndWeight = ((dHeight/100) * (dHeight/100)) * 22;
        stndWeight = Math.round(stndWeight * 10.0) / 10.0;


        TextView tvStandartWeight = findViewById(R.id.textView12);
        tvStandartWeight.setText(String.valueOf(stndWeight));
    }

    private boolean validateEmptyText (String str){
        boolean valid = true;

        if(str.length() == 0){
            valid = false;
        }

        return valid;

    }

    private boolean validateRangeHeight (String str){
        boolean valid = true;
        double value = Double.parseDouble(str);
        if(value <= 0 || value > 300 ){
            valid = false;
        }

        return valid;
    }

    private boolean validateRangeWeight (String str){
        boolean valid = true;
        double value = Double.parseDouble(str);
        if(value <= 0 || value > 200 ){
            valid = false;
        }

        return valid;
    }

    private String checkBmiRange (Double value){
        String result = "";

        ImageView iv = findViewById(R.id.imageView3);


        if(value < 18.5){
            result = "低体重(痩せ型)";
            iv.setImageResource(R.drawable.thin);
        }else if(value < 25){
            result = "普通体重";
            iv.setImageResource(R.drawable.normal);
        }else if(value < 30){
            result = "肥満(1度)";
            iv.setImageResource(R.drawable.fat);
        }else if(value < 35){
            result = "肥満(2度)";
            iv.setImageResource(R.drawable.fat);
        }else if(value < 40){
            result = "肥満(3度)";
            iv.setImageResource(R.drawable.fat);
        }else {
            result = "肥満(4度)";
            iv.setImageResource(R.drawable.fat);
        }

        return result;
    }
}