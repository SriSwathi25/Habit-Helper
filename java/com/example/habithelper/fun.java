package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fun extends AppCompatActivity {
    Button back;
    Button game1;
    Button game2;
    Button game3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        back=findViewById(R.id.fun_back);
        game1=findViewById(R.id.game1);
        game2=findViewById(R.id.game2);
        //game3=findViewById(R.id.game3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(fun.this, Activity2.class);
                startActivity(i1);
            }
        });
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(fun.this, game1.class);
                startActivity(i2);
            }
        });
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(fun.this, game2.class);
                startActivity(i3);
            }
        });


    }
}
