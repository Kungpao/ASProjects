package com.example.hyouka_pi52a_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Matrix;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ViewGroup rootContainer;

    //scenes. 1 - main menu, 2 - character menu, 3 - tutorial, 4 - game screen, 5 - game result screen
    Scene scene1;
    Scene scene2;
    Scene scene3;
    Scene scene4;
    Scene scene5;

    //utility variables
    Transition transitionMgr;
    int duration;
    Toast toast;
    Context context;
    String msg;
    Button btn;

    //imageViews declaration
    ImageView iv,resultCpuHand,resultPlayerHand,ivSlide;

    //Animation use
    AnimationSet rotation;
    Animation slideIn;

    int[] hand;
    int player;
    int cpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test code. separate declare and init
        context = getApplicationContext();
        msg = "Let's Roll !";
        duration = Toast.LENGTH_LONG;
        toast = Toast.makeText(context,msg,duration);
        //test code end

        hand = new int[5];
        hand[0] = R.drawable.rock;
        hand[1] = R.drawable.scissors;
        hand[2] = R.drawable.paper;
        hand[3] = R.drawable.spock;
        hand[4] = R.drawable.lizard;



        //Transition in between the scenes
        rootContainer = (ViewGroup) findViewById(R.id.rootContainer);

        transitionMgr = TransitionInflater.from(this).inflateTransition(R.transition.transition);

        scene1 = Scene.getSceneForLayout(rootContainer, R.layout.main_menu,this);
        scene2 = Scene.getSceneForLayout(rootContainer, R.layout.character_choose,this);
        scene3 = Scene.getSceneForLayout(rootContainer, R.layout.tutorial,this);
        scene4 = Scene.getSceneForLayout(rootContainer, R.layout.game_screen,this);
        scene5 = Scene.getSceneForLayout(rootContainer, R.layout.game_result_screen,this);

        scene1.enter();
        // Transition block end

    }

    public void characterChoose(View view){
        ImageView iv = (ImageView) view;
        //Drawable drw = iv.getBackground();

        String str = iv.getBackground().getConstantState().toString();

        if(str == getResources().getDrawable(R.drawable.ellipse5).getConstantState().toString()){
            iv.setBackgroundResource(R.drawable.ellipse2);
        }else if (str == getResources().getDrawable(R.drawable.ellipse2).getConstantState().toString()){
            iv.setBackgroundResource(R.drawable.ellipse5);
        }
        Toast.makeText(MainActivity.this,str, Toast.LENGTH_LONG).show();

    }

    // 0 = Draw;
    // -2,4 loose;
    // "-4,2" Win;
    public void calcOutcome (){
        TextView tv = findViewById(R.id.resultText);
        int result = player - cpu;
        if(result == 0){
            //iv.setImageResource(R.drawable.draw);
            tv.setText("Draw");
        }else if(result == -4 || result == 2){
            //iv.setImageResource(R.drawable.win);
            tv.setText("WIN!");
        }else if(result == -2 || result == 4){
            //iv.setImageResource(R.drawable.lose);
            tv.setText("Loose");
        }else{
            if((player == 0 && result == -3) ||
                    (player == 1 && result == 1) ||
                    (player == 3 && (result == 1 || result == -1)) ||
                    (player == 4 && result == 3)
            ){
                tv.setText("Loose");
            }else{
                tv.setText("WIN!");
            }
        }
        resultCpuHand = findViewById(R.id.resultCpuHand);
        resultCpuHand.setImageResource(hand[cpu]);

        resultPlayerHand = findViewById(R.id.rollImg);
        resultPlayerHand.setImageResource(hand[player]);
    }

    public void slide_in(){
        ivSlide = findViewById(R.id.imageView12);
        slideIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in);
        ivSlide.startAnimation(slideIn);

    }
    public void roll(View view){
        //toast.show();

        //if(rotation == null){
            cpu = new Random().nextInt(4);

            iv = findViewById(R.id.rollImg);

            rotation = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.rotation);

            for(Animation a: rotation.getAnimations()) {
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //rotation = null;
                        //a little pause before proceed to the next screen
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        goToScene5(view);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        player = new Random().nextInt(4);
                        iv.setImageResource(hand[player]);
                    }
                });
            }

            //iv.startAnimation(rotation);

        //}

        iv.startAnimation(rotation);
    }

    public void goToScene5(View view){
        TransitionManager.go(scene5, transitionMgr);
        calcOutcome ();
        slide_in();

    }
    public void goToScene4(View view){
        TransitionManager.go(scene4, transitionMgr);
        roll(view);
    }
    public void goToScene3(View view){
        TransitionManager.go(scene3, transitionMgr);
    }
    public void goToScene2(View view){
        TransitionManager.go(scene2, transitionMgr);
    }
    public void goToScene1(View view){
        TransitionManager.go(scene1, transitionMgr);
    }



}