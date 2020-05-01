package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public void func(View view){
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv=(ImageView)findViewById(R.id.logo);
        //iv.animate().alpha(1).rotation(-360).setDuration(1000);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(iv, "scaleX", 0.3f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(iv, "scaleY", 0.3f);
        scaleDownX.setDuration(1500);
        scaleDownY.setDuration(2000);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });




    }
}
