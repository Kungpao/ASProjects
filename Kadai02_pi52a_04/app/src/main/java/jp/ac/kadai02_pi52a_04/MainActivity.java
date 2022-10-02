package jp.ac.kadai02_pi52a_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateBmi(View view){

        EditText etHeight = findViewById(R.id.height);
        String strHeight = etHeight.getText().toString();
        double dHeight = Double.parseDouble(strHeight);

        EditText etWeight = findViewById(R.id.weight);
        String strWeight = etWeight.getText().toString();
        double dWeight = Double.parseDouble(strWeight);

        double bmi = dWeight/((dHeight/100)*(dHeight*100));

        String sBmi = "".valueOf(bmi);

        TextView tvBmi = findViewById(R.id.textView7);
        tvBmi.setText(sBmi);


        double stndWeight = dHeight * dHeight * 22;

        TextView tvStandartWeight = findViewById(R.id.textView12);
        tvStandartWeight.setText(String.valueOf(stndWeight));


    }


}