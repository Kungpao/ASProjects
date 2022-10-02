package com.example.kadai05_pi52a_04;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //private String tag = "";
    private boolean flg = true;
    private boolean flgFlipMain = true;

    //int[] btns_id = new int[6];

    int[] p_hand = new int [3];
    int[] cpu_hand = new int[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView iv = findViewById(R.id.imageView1);

        p_hand[0] = R.id.p_rock;
        p_hand[1] = R.id.p_scissors;
        p_hand[2] = R.id.p_paper;

        cpu_hand[0] = R.id.p_rock_cpu;
        cpu_hand[1] = R.id.p_scissors_cpu;
        cpu_hand[2] = R.id.p_paper_cpu;

        //btns_id[0] = R.id.p_rock;
        //btns_id[1] = R.id.p_scissors;
        //btns_id[2] = R.id.p_paper;


        //btns_id[3] = R.id.p_rock_cpu;
        //btns_id[4] = R.id.p_scissors_cpu;
        //btns_id[5] = R.id.p_paper_cpu;



        ImageView pRock = findViewById(R.id.p_rock);
        ImageView pScissors = findViewById(R.id.p_scissors);
        ImageView pPaper = findViewById(R.id.p_paper);

        ImageView cpuRock = findViewById(R.id.p_rock_cpu);
        ImageView cpuScissors = findViewById(R.id.p_scissors_cpu);
        ImageView cpuPaper = findViewById(R.id.p_paper_cpu);

        Button playAgain = findViewById(R.id.playAgain);

        playAgain.setOnClickListener(view -> doJankenAgain());

        LinearLayout Ll = findViewById(R.id.linearLayout1);

        pRock.setOnClickListener(view -> doJanken(view));
        pScissors.setOnClickListener(view -> doJanken(view));
        pPaper.setOnClickListener(view -> doJanken(view));


        //you can apply flip animations to a Layout the same way as with image view.
        /*pRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ll.setRotationX(Ll.getRotationX() + 180f); //horizontal flip
                Ll.setRotationY(Ll.getRotationY()+180f);  //vertical flip

            }
        });*/

        /*iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if(flgFlipMain){
                            iv.setImageResource(R.drawable.win);
                        }else{
                            iv.setImageResource(R.drawable.janken_boys);
                        }
                        flgFlipMain = !flgFlipMain;
                        oa2.start();
                    }
                });
                oa1.start();
            }
        });*/


        //iv.setOnClickListener(view -> {
        //changeImage();
        //});
    }


    //need to pass player and cpu choices to make decision. Just in case it worked with empty params
    private void showResult(int player, int cpu){
        ImageView iv = findViewById(R.id.imageView1);

        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());

        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                //it can be not a good idea to do it here but it is not heavy so .. anyway

                int result = player - cpu;
                if(result == 0){
                    iv.setImageResource(R.drawable.draw);
                }else if(result == -1 || result == 2){
                    iv.setImageResource(R.drawable.win);
                }else{
                    iv.setImageResource(R.drawable.lose);
                }
                oa2.start();

            }
        });
        oa1.start();

    }


    private void doJanken(View view){
        //The view here is actually player choice. Self-explanatory, no further elaborations needed
        //cosmetics, to make it more readable
        if(!flgFlipMain){
            return;
        }

        ImageView player = (ImageView) view;
        String str = "";
        int player_int = 0;
        switch(player.getId()){
            case R.id.p_rock:
                str = "Rock!";
                break;
            case R.id.p_scissors:
                str = "Scissors!";
                player_int = 1;
                //str = view.getResources().getResourceEntryName(view.getId());
                //str = String.valueOf(player.getId());
                break;
            case R.id.p_paper:
                str = "Paper!";
                player_int = 2;
                break;
        }

        Toast.makeText(MainActivity.this,str, Toast.LENGTH_LONG).show();

        //player.setVisibility(View.INVISIBLE);

        //maybe it is better not to pass all view in the function but create if needed?
        //Cpu makes his choice

        int cpu_int = new Random().nextInt(cpu_hand.length);
        ImageView cpu = findViewById(cpu_hand[cpu_int]);

        //cleaning not used elements from the board
        for(int i=0; i<3; i++){
            if(player.getId() != p_hand[i]){
                //invicible ?
                findViewById(p_hand[i]).setVisibility(View.INVISIBLE);
            }
            if(cpu.getId() != cpu_hand[i]){
                findViewById(cpu_hand[i]).setVisibility(View.INVISIBLE);
            }
        }

        showResult(player_int,cpu_int);

        flgFlipMain = !flgFlipMain;
        //findViewById(R.id.playAgain).setVisibility(View.VISIBLE);
        animateBtnFadeIn();
    }

    private void doJankenAgain(){

        for(int i=0; i<3; i++){
            findViewById(p_hand[i]).setVisibility(View.VISIBLE);
            findViewById(cpu_hand[i]).setVisibility(View.VISIBLE);
        }

        //findViewById(R.id.playAgain).setVisibility(View.INVISIBLE);

        changePicture();
        animateBtnFadeOut();
        flgFlipMain = !flgFlipMain;
    }

    private void changePicture () {
        ImageView iv = findViewById(R.id.imageView1);
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());

        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                iv.setImageResource(R.drawable.janken_boys);
                oa2.start();
            }
        });
        //oa1.setDuration(5000);
        oa1.start();
    }

    private void animateBtnFadeIn () {
        Button btn = (Button) findViewById(R.id.playAgain);
        //btn.setGravity(Gravity.CENTER);   set gravity
        //final Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade);
        //animation.setInterpolator(new DecelerateInterpolator());
        //btn.startAnimation(animation);
        btn.setAlpha(0);
        btn.setVisibility(View.VISIBLE);
        final ObjectAnimator oa1= ObjectAnimator.ofFloat(btn,"alpha", 0,1);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa1.setDuration(2000);
        oa1.start();

    }

    private void animateBtnFadeOut() {
        Button btn = (Button) findViewById(R.id.playAgain);
        //final Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        //btn.startAnimation(animation);
        btn.setAlpha(0);
        btn.setVisibility(View.INVISIBLE);
        final ObjectAnimator oa1= ObjectAnimator.ofFloat(btn,"alpha", 1,0);
        oa1.setInterpolator(new AccelerateInterpolator());
        //if(btn != R.id.playAgain) {oa.setDuration 500}

        oa1.setDuration(2000);
        oa1.start();

    }


    private void animateBtnMove(){

    }



}