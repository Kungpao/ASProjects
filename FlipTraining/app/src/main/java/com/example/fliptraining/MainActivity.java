package com.example.fliptraining;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    //private String tag = "";
    private final boolean flg = true;
    private final boolean flgFlipMain = true;
    private ViewFlipper vf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vf = findViewById(R.id.viewFlipper);

        ImageView iv = findViewById(R.id.imageView1);
        ImageView pRock = findViewById(R.id.p_rock);
        ImageView pScissors = findViewById(R.id.p_scissors);
        ImageView pPaper = findViewById(R.id.p_paper);

        LinearLayout MainMenuLL = findViewById(R.id.LinearLayoutMainMenu);
        FrameLayout infoScreenFrameLayout = findViewById(R.id.infoScreenFrameLayout);

        LinearLayout Ll = findViewById(R.id.linearLayout1);
        //pRock.setOnClickListener(view -> doJanken(view));
        pScissors.setOnClickListener(view -> doJanken(view));
        pPaper.setOnClickListener(view -> doJanken(view));

        Button startButton = findViewById(R.id.button3);

        /*startButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(MainMenuLL, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(MainMenuLL, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //we do not need if in case there are different menu screen
                        if(flgFlipMain){
                            //iv.setImageResource(R.drawable.win);
                            setContentView(R.layout.activity_main1);
                        }else{
                            //iv.setImageResource(R.drawable.janken_boys);
                            setContentView(R.layout.activity_main);
                        }
                        flgFlipMain = !flgFlipMain;
                        oa2.start();
                    }
                });
                oa1.start();
            }
        });

         */

        Button infoScreenMainMenuButton = findViewById((R.id.infoScreenMainMenuButton));
        ViewFlipper vf;




       /* infoScreenMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa3 = ObjectAnimator.ofFloat(infoScreenFrameLayout, "scaleX", 1f, 0f);
                final ObjectAnimator oa4 = ObjectAnimator.ofFloat(infoScreenFrameLayout, "scaleX", 0f, 1f);
                oa3.setInterpolator(new DecelerateInterpolator());
                oa4.setInterpolator(new AccelerateDecelerateInterpolator());

                oa3.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //we do not need if in case there are different menu screen
                        //if(flgFlipMain){
                            //iv.setImageResource(R.drawable.win);
                        //setContentView(R.layout.activity_main1);
                        //}else{
                            //iv.setImageResource(R.drawable.janken_boys);
                        // setContentView(R.layout.activity_main);
                        // }
                        //flgFlipMain = !flgFlipMain;
                        // oa2.start();

                        //setContentView(R.layout.activity_main);
                        //oa4.start();
                    }
                });
                oa3.start();
            }
        });
*/

        //you can apply flip animations to a Layout the same way as with image view.
        /*pRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ll.setRotationX(Ll.getRotationX() + 180f); //horizontal flip
                Ll.setRotationY(Ll.getRotationY()+180f);  //vertical flip

            }
        });


         */


        //iv.setOnClickListener(view -> {
        //changeImage();
        //});

    }

    private void doJanken(View view){
        ImageView player = (ImageView) view;
        String str = "";
        switch(player.getId()){
            case R.id.p_rock:
                str = "Rock!";
                break;
            case R.id.p_scissors:
                str = "Scissors!";
                break;
            case R.id.p_paper:
                str = "Paper!";
                break;
        }
        Toast.makeText(MainActivity.this,str, Toast.LENGTH_LONG).show();
        player.setVisibility(View.INVISIBLE);
    }

    public void nextView(View view){
        vf.setInAnimation(this, R.anim.slide_in_right);
        vf.setOutAnimation(this, R.anim.slide_out_left);
        vf.showNext();
    }

    public void previousView(View view){
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
        vf.showPrevious();
    }
}