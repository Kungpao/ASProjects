package com.example.kadai06_pi52a_04;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {

    private int customResId;

    public CustomImageView (@NonNull Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        this.customResId = resId;

    }
    public int getCustomResId(){
        return this.customResId;
    }
}
