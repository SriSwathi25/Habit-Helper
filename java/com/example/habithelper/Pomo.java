package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Pomo extends AppCompatActivity {
    TextView textView;
    SeekBar s;
    CountDownTimer countDownTimer;

    int count=0;
    public void goBack(View view){

    }

    public void func(View view)
    {
        Button btn=findViewById(R.id.button);
        if(count==0) {
            countDownTimer = new CountDownTimer(s.getProgress()*1000+100, 1000) {
                @Override
                public void onTick(long l) {


                    timer((int)(l/1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer over= MediaPlayer.create(getApplicationContext(), R.raw.buzzer);;
                    over.start();

                }
            }.start();
            btn.setText("Stop");
            s.setEnabled(false);
            count++;
        }
        else if(count==1)
        {
            s.setEnabled(true);
            btn.setText("Start");
            textView.setText("00"+":"+"00");
            countDownTimer.cancel();

            count=0;
        }
    }
    public void timer(int i)
    {
        int minutes=i/60;
        int seconds=i-(minutes*60);
        String minutesString=Integer.toString(minutes);
        if(minutesString.equals("0"))
        {
            minutesString="00";
        }
        else if(minutesString.length()==1)
        {
            minutesString="0"+minutesString;
        }
        String secondsString=Integer.toString(seconds);
        if(secondsString.equals("0"))
        {
            secondsString="00";
        }
        else if(secondsString.length()==1)
        {
            secondsString="0"+secondsString;
        }
        textView.setText(minutesString+":"+secondsString);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomo);
        Button btn = (Button) findViewById(R.id.addHabit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Pomo.this, Activity2.class);
                Pomo.this.startActivity(myIntent);
            }
        });


        s=findViewById(R.id.seekBar);
        int max=25*60;
        final int min=1*60;
        s.setMax(max);
        s.setMin(min);
        s.setProgress(max);

        textView=findViewById(R.id.habit_name_textView);
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<min)
                {

                    s.setProgress(min);
                }
                timer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
