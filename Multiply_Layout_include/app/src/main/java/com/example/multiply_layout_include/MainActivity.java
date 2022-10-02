package com.example.multiply_layout_include;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ViewGroup rootContainer;
    Scene scene1;
    Scene scene2;
    Transition transitionMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootContainer = (ViewGroup) findViewById(R.id.rootContainer);

        transitionMgr = TransitionInflater.from(this).inflateTransition(R.transition.transition);

        scene1 = Scene.getSceneForLayout(rootContainer, R.layout.a_scene,this);
        scene2 = Scene.getSceneForLayout(rootContainer, R.layout.b_scene,this);

        scene1.enter();
    }

    public void goToScene2(View view){
        TransitionManager.go(scene2, transitionMgr);
    }

    public void goToScene1(View view){
        TransitionManager.go(scene1, transitionMgr);
    }
}