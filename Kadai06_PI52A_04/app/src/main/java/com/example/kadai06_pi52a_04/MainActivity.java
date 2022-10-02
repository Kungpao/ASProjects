package com.example.kadai06_pi52a_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CustomImageView[][] civ = new CustomImageView[3][3];
    boolean flg = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        civ[0][0] = findViewById(R.id.c_1);
        civ[0][1] = findViewById(R.id.c_2);
        civ[0][2] = findViewById(R.id.c_3);

        civ[1][0] = findViewById(R.id.c_4);
        civ[1][1] = findViewById(R.id.c_5);
        civ[1][2] = findViewById(R.id.c_6);

        civ[2][0] = findViewById(R.id.c_7);
        civ[2][1] = findViewById(R.id.c_8);
        civ[2][2] = findViewById(R.id.c_9);

        //initialize screen with blank images
        init();

        //click listeners
        for(int i=0; i<civ.length; i++){
            for(int j=0; j<civ[i].length; j++){
                civ[i][j].setOnClickListener(view -> civClicked(view));
            }
        }

        btn.setOnClickListener(view -> clearBtn());

    }

    private void clearBtn(){
        init();
    }

    //click listener body
    private void civClicked(View view){
        CustomImageView civClicked = (CustomImageView) view;

        //saving blank img id
        int imgId = R.drawable.blank;

        //check if clicked == blank
        if(civClicked.getCustomResId() == imgId) {

            if(flg){
                imgId = R.drawable.maru;

            }else{
                imgId = R.drawable.batu;

            }

        }

        civClicked.setImageResource(imgId);
        flg = !flg;


    }

    //initialize screen with blank images body
    private void init() {
        for(int i=0; i<civ.length; i++){
            for(int j=0; j<civ[i].length; j++){
                civ[i][j].setImageResource(R.drawable.blank);
            }
        }
    }
}