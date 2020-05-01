package com.example.habithelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    private SharedPreferences Prefs;
    static boolean can_start=false;
    ProgressBar healthBar;
    int healthVal=100;
    //private boolean isFirstRun = true;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                new AlertDialog.Builder(this).setIcon(R.drawable.owl)
                        .setTitle("HELP")
                        .setMessage("Welcome to Help! If you are facing any trouble try restarting the app. To report any issues write to us at habithelper@gmail.com")
                        .setNeutralButton("OK !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //String val1=hiname;

                            }
                        })
                        .show();
                //Toast.makeText(this, "Habit Helper is a gamified application \n which helps user to keep up his habits. \n Streak :\nKeeps track of how many days a task is being done in a row. \nHealth :\nTask efficiency and streak reflect the health of the user.\nPomo Timer : \nTimer to help you perform any task with focus.\nFun Zone :\nFun zone :\nTrivia games and quizzes!", Toast.LENGTH_LONG).show();
                return true;
                //perform any action;

                //Toast.makeText(this, "Welcome to Help! If you are facing any trouble\ntry restarting the app.\n To report any issues\nwrite to us at habithelper@gmail.com", Toast.LENGTH_LONG).show();

            case R.id.about:
                //perform any action;
                new AlertDialog.Builder(this).setIcon(R.drawable.owl)
                        .setTitle("ABOUT")
                        .setMessage("Habit Helper is a gamified application which helps user to keep up his habits. Streak : Keeps track of how many days a task is being done in a row. Health : Task efficiency and streak reflect the health of the user. Pomo Timer : Timer to help you perform any task with focus. Fun Zone : Trivia games and quizzes!")
                        .setNeutralButton("OK !", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //String val1=hiname;

                            }
                        })
                        .show();
                //Toast.makeText(this, "Habit Helper is a gamified application \n which helps user to keep up his habits. \n Streak :\nKeeps track of how many days a task is being done in a row. \nHealth :\nTask efficiency and streak reflect the health of the user.\nPomo Timer : \nTimer to help you perform any task with focus.\nFun Zone :\nFun zone :\nTrivia games and quizzes!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.refresh:
                //perform any action;
                Toast.makeText(this, "Your app is Refreshed!", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    boolean isFirstRun;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_2);
            Button pomo_timer = (Button) findViewById(R.id.pomo);
            Button add_habit = findViewById(R.id.addHabit);
            Button update = findViewById(R.id.update);
            Button view= findViewById(R.id.viewButton);
            Button fun = findViewById(R.id.fun_zone);
            TextView healthHeading = findViewById(R.id.textView3);
            healthBar=findViewById(R.id.healthBar);
            healthVal=100-(Home.highest_streak-Home.least_streak)/2;
            healthBar.setProgress(healthVal);
            healthHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(healthVal==100){
                        new AlertDialog.Builder(Activity2.this).setIcon(R.drawable.owl)
                                .setTitle("Health Alert")
                                .setMessage("Hurray!! Your health bar is full")
                                .show();
                    }
                    else if (healthVal<=50){
                        new AlertDialog.Builder(Activity2.this).setIcon(R.drawable.owl)
                                .setTitle("Health Alert")
                                .setMessage("Peepeep!! Your health bar is dropping")
                                .show();
                    }
                    else if (healthVal==0){
                        new AlertDialog.Builder(Activity2.this).setIcon(R.drawable.owl)
                                .setTitle("Health Alert")
                                .setMessage("Very Bad!! Your health bar is 0")
                                .show();
                    }

                }
            });
            NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            //Notification notify=
            Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle("Habit Helper").setContentText("Update your tasks").
                    setContentTitle("Don't forget to reach your goals").setSmallIcon(R.drawable.habithelper).build();

            notify.flags |= Notification.FLAG_AUTO_CANCEL;



            final ImageView iv2=(ImageView)findViewById(R.id.reaction);
            iv2.setVisibility(View.INVISIBLE);
            TextView h_str=findViewById(R.id.highest_streak);
            TextView l_str=findViewById(R.id.lowest_Streak);
            h_str.setText(Home.hiText);
            l_str.setText(Home.loText);

            Bundle extras = getIntent().getExtras();
            if(extras != null){
                h_str.setText(getIntent().getStringExtra("Highest"));
                l_str.setText(getIntent().getStringExtra("Least"));
            }
            //iv.animate().alpha(1).rotation(-360).setDuration(1000);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();

           isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true);

            if(isFirstRun) {
                /*

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setIcon(R.drawable.owl)
                        .setTitle("Health")
                        .setMessage("Did you keep up all your habits all day?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                iv2.setImageResource(R.drawable.claps);
                                iv2.setVisibility(View.VISIBLE);
                                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(iv2, "scaleX", 1.3f);
                                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(iv2, "scaleY", 1.3f);
                                scaleDownX.setDuration(1500);
                                scaleDownY.setDuration(2000);

                                AnimatorSet scaleDown = new AnimatorSet();
                                scaleDown.play(scaleDownX).with(scaleDownY);

                                scaleDown.start();
                                iv2.animate().alpha(0).setDuration(2000);
                                //Toast.makeText(Activity2.this, "Wohoo!! Keep it up Champ!", Toast.LENGTH_LONG).show();
                                /*
                                Handler mHandler = new Handler();

                                mHandler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        //start your activity here
                                        //if(can_start)
                                        new AlertDialog.Builder(Activity2.this).setIcon(R.drawable.habithelper)
                                                .setMessage("Based on your habit streaks and efficiency, your health bar is affected. Once, your health reaches danger zone, all your streak will be lost.So, do well. All the Best!")
                                                .show();


                                    }

                                }, 4000L);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                iv2.setImageResource(R.drawable.owl);
                                iv2.setVisibility(View.VISIBLE);
                                ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(iv2, "scaleX", 1.3f);
                                ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(iv2, "scaleY", 1.3f);
                                scaleDownX.setDuration(1500);
                                scaleDownY.setDuration(2000);

                                AnimatorSet scaleDown = new AnimatorSet();
                                scaleDown.play(scaleDownX).with(scaleDownY);

                                scaleDown.start();
                                iv2.animate().alpha(0).setDuration(2000);
                                //Toast.makeText(Activity2.this, "Oh no!! :((", Toast.LENGTH_LONG).show();
                                Handler mHandler = new Handler();

                                mHandler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        //start your activity here
                                        //if(can_start)
                                        new AlertDialog.Builder(Activity2.this).setIcon(R.drawable.habithelper)
                                                .setMessage("Based on your habit streaks and efficiency, your health bar is affected. Once, your health reaches danger zone, all your streak will be lost.So, do well. All the Best!")
                                                .show();


                                    }

                                }, 4000L);

                            }
                        })
                        .show();
                editor.putBoolean("IS_FIRST_RUN", false);
                editor.commit();
            }}


            isFirstRun = false;

                 */

            }


            pomo_timer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(Activity2.this, Pomo.class);
                    Activity2.this.startActivity(myIntent);
                }
            });
            add_habit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent2 = new Intent(Activity2.this, addHabit.class);
                    Activity2.this.startActivity(myIntent2);

                }
            });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent3 = new Intent(Activity2.this, Home.class);
                    Activity2.this.startActivity(myIntent3);

                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent4 = new Intent(Activity2.this, view.class);
                    Activity2.this.startActivity(myIntent4);

                }
            });
            fun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent5 = new Intent(Activity2.this, fun.class);
                    Activity2.this.startActivity(myIntent5);
                }
            });
        }


         catch (Exception e) {
            Log.i("INFO", "PAGE BLOCK BUFFFF");
            System.out.println("PRINTING ERRORRR");
            e.printStackTrace();
            Toast.makeText(this, "GONEEEEEEEEE", Toast.LENGTH_LONG).show();
        }}
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Update
        editor.putBoolean("IS_FIRST_RUN", false);
        editor.commit();
    }
        private boolean promptTutorial() {
            // Check fo saved value in Shared preference for key: keyTutorial return "NullTutorial" if nothing found
            String keyTutorial = Prefs.getString("keyTutorial", "NullTutorial");
            // Log what we found in shared preference
            //Log.d(TAG, "Shared Pref read: [keyTutorial: " + keyTutorial + "]");

            if (keyTutorial.contains("NullTutorial")){
                // if nothing found save a new value "PROMPTED" for the key: keyTutorial
                // to save it in shared prefs just call our saveKey function
                saveKey("keyTutorial", "PROMPTED");
                return true;
            }
            // if some value was found for this key we already propted this window some time in the past
            // no need to prompt it again
            return false;
        }
        private void saveKey(String key, String value) {
            SharedPreferences.Editor editor = Prefs.edit();
            // Log what are we saving in the shared Prefs
            //Log.d(TAG, "Shared Prefs Write ["+ key + ":" +value + "]");
            editor.putString(key, value);
            editor.commit();
        }
    }




