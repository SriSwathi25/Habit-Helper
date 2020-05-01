package com.example.habithelper;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class game2 extends AppCompatActivity {
    Button goButton;
    TextView question;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button back;
    Button playAgain;
    TextView anss;
    TextView score;
    int opt;
    TextView timer;
    ConstraintLayout mainn;
    int scoree;
    TextView finalScore;


    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        mainn.setVisibility(View.VISIBLE);
        restart(findViewById(R.id.timer));
    }
    public void choose(View view){
        anss=findViewById(R.id.anss);
        score=findViewById(R.id.score);
        if(Integer.toString(opt).equals(view.getTag().toString())){
            anss.setText("CORRECT!!!");
            scoree+=1;
            score.setText(Integer.toString(scoree));
        }
        else{
            anss.setText("WRONG!!!");
        }
        game();
    }
    public void game(){
        //restart(findViewById(R.id.timer));

        mainn.setVisibility(View.VISIBLE);
        ArrayList<Integer> al=new ArrayList<>();
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        int ans=a+b;
        question=findViewById(R.id.ques);
        question.setText(Integer.toString(a)+" + "+Integer.toString(b));
        int[] choice=new int[4];


        opt=rand.nextInt(4);
        for(int i=0; i<4; i++){
            if(i==opt){
                al.add(ans);
            }
            else{
                int wrong=rand.nextInt(41);
                while(wrong==a+b){
                    wrong=rand.nextInt(41);
                }
                al.add(wrong);
            }

        }
        b1.setText(Integer.toString(al.get(0)));
        b2.setText(Integer.toString(al.get(1)));
        b3.setText(Integer.toString(al.get(2)));
        b4.setText(Integer.toString(al.get(3)));


    }
    public void restart(View view){
        //score.setText("0");
        playAgain.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        timer.setText("30s");

        game();
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");
            }
            @Override
            public void onFinish() {
                //anss.setText("TIME UP!!");
                mainn.setVisibility(View.INVISIBLE);
                finalScore.setText("Score is : "+Integer.toString(scoree));
                finalScore.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        scoree=0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        back=findViewById(R.id.game2_back);
        goButton=findViewById(R.id.go);
        timer=findViewById(R.id.timer);
        mainn=findViewById(R.id.mainn);
        playAgain=findViewById(R.id.playAgain);
        mainn.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        finalScore=findViewById(R.id.ss);
        finalScore.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(game2.this, fun.class);
                startActivity(i1);
            }
        });



    }
}
