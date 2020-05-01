package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class game1 extends AppCompatActivity {
    MediaPlayer coin;
    MediaPlayer claps;
    MediaPlayer tie;
    public void playAgain(View view)
    {
        Log.i("INFO", "Button pressed");
        gameActive=true;
        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText("Start your game!! ");

        Button bt=(Button)findViewById(R.id.buttonn);
        bt.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView child=(ImageView)gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        checker=0;
        for(int i=0; i<boxStates.length; i++)
        {
            boxStates[i]=2;
        }


    }
    int[] boxStates = {2,2,2,2,2,2,2,2,2};
    int[][] wins={{0,1,2},{3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    int checker=0;
    public void dropIn(View view) {
        //MainActivity ma=new MainActivity();

        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setVisibility(View.INVISIBLE);



        ImageView marker = (ImageView) view;
        int viewNum = Integer.parseInt(marker.getTag().toString());
        if (gameActive && boxStates[viewNum] == 2) {
            coin.start();

            view.setTranslationY(-1000);
            if (checker == 0) {
                checker = 1;
                boxStates[viewNum]=0;
                ((ImageView) view).setImageResource(R.drawable.x);
            } else {
                checker = 0;
                boxStates[viewNum]=1;
                ((ImageView) view).setImageResource(R.drawable.o);
            }
            String winner="";
            view.animate().translationYBy(1000).rotation(3600).setDuration(1000);
            for(int[] states:wins)
            {
                if(boxStates[states[0]] == boxStates[states[1]] && boxStates[states[2]] == boxStates[states[1]] && boxStates[states[1]]!=2)
                {
                    gameActive=false;
                    if (boxStates[states[0]] == 1)
                        winner = "O";
                    else
                        winner = "X";

                    //TextView tv=(TextView) findViewById(R.id.textView);
                    tv.setText(winner+" has won!! ");
                    tv.setVisibility(View.VISIBLE);
                    claps.start();

                    Button bt=(Button)findViewById(R.id.buttonn);
                    bt.setVisibility(View.VISIBLE);

                }
            }
            boolean all=false;
            int c=0;
            for(int boo : boxStates)
            {
                if(boo != 2)
                    c++;
            }
            if(c==9 && gameActive)
            {
                //TextView tv=(TextView) findViewById(R.id.textView);
                tv.setText("Its a tie!! ");
                tv.setVisibility(View.VISIBLE);
                tie.start();


                Button bt=(Button)findViewById(R.id.buttonn);
                bt.setVisibility(View.VISIBLE);
                gameActive=false;
            }

        }
    }
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        coin=MediaPlayer.create(this, R.raw.coin);
        claps=MediaPlayer.create(this, R.raw.claps);
        tie=MediaPlayer.create(this, R.raw.tie);
        back=findViewById(R.id.game1_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(game1.this, fun.class);
                startActivity(i1);
            }
        });


        androidx.gridlayout.widget.GridLayout gridLayout=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        gridLayout.animate().alpha(1).setDuration(2000).rotation(360);

        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText("Start your game!! ");
        tv.setVisibility(View.VISIBLE);
    }
}



    /*
   public void coinFall(){
        //MediaPlayer coinn=MediaPlayer.create(this, R.raw.coin);
        coin.start();
    }
    public void clapss(){
        //MediaPlayer claps=MediaPlayer.create(this, R.raw.claps);
        claps.start();
    }
    public  void tiee(){
        //MediaPlayer tiee=MediaPlayer.create(this, R.raw.tie);
        tie.start();
    }
*/


    //MediaPlayer oopss=MediaPlayer.create(R.raw.oops);
    //0 means x, 1 means o, 2 means empty



