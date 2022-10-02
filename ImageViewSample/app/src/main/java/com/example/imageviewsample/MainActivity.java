package com.example.imageviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private String tag = "";
    private boolean flg = true;
    private boolean flgFlipMain = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView iv = findViewById(R.id.imageView1);

        ImageView pRock = findViewById(R.id.p_rock);
        ImageView pScissors = findViewById(R.id.p_scissors);
        ImageView pPaper = findViewById(R.id.p_paper);
        LinearLayout Ll = findViewById(R.id.linearLayout1);
        //pRock.setOnClickListener(view -> doJanken(view));
        pScissors.setOnClickListener(view -> doJanken(view));
        pPaper.setOnClickListener(view -> doJanken(view));

        //you can apply flip animations to a Layout the same way as with image view.
        pRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ll.setRotationX(Ll.getRotationX() + 180f); //horizontal flip
                 Ll.setRotationY(Ll.getRotationY()+180f);  //vertical flip

            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
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
        });


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


    //private void changeImage(){
       // ImageView iv = findViewById(R.id.imageView1);
        //if(tag.equals("janken_boys")){
            //iv.setImageResource(R.drawable.win);
            //tag = "win";
        //}else{
            //iv.setImageResource(R.drawable.janken_boys);
            //tag = "janken_boys";
        //}

      // if(flg){
            //iv.setImageResource(R.drawable.win);
        //}else{
            //iv.setImageResource(R.drawable.janken_boys);
        //}
        //flg = !flg;
    //}

}