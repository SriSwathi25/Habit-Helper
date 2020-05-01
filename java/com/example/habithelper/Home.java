package com.example.habithelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habithelper.data.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Home extends AppCompatActivity {
    private DBHelper db;




    LinearLayout firstlayout;
    String hname;
    int id;
    String new_val;
    static int least_streak=0;
    static int highest_streak=0;
    static String lname="";
    static String hiname="";
    static String hiText;
    static String loText;
    //SharedPreferences hiLo_streak=getApplicationContext().getSharedPreferences("com.example.habithelper", 0);


    //public static Home instance=Home.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db= new DBHelper(this);
        firstlayout = (LinearLayout) findViewById(R.id.my_linear_layout);
        Button back=findViewById(R.id.home_back);

        /*if(addHabit.sending==true) {
            Intent in = getIntent();

            id = Integer.parseInt(in.getStringExtra("HABIT_ID"));
            hname = in.getStringExtra("HABIT_NAME");

            addNewHabit(id, hname);
            addHabit.sending=false;
        }

         */
        displayHome();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mm=new Intent(Home.this, Activity2.class);
                Home.this.startActivity(mm);
            }
        });


    }
    public Cursor allData(){
        SQLiteDatabase dbb=db.getReadableDatabase();
        Cursor cursor=dbb.rawQuery("select * from HABITS", null);
        return cursor;
    }
    public Cursor allData2(){
        SQLiteDatabase dbb=db.getReadableDatabase();
        Cursor cursor=dbb.rawQuery("select * from TRACK", null);
        return cursor;
    }
    //home h=new home();

    //int id=-1;
    public void displayHome(){
        //int id=-1;
        //String query = "SELECT ID FROM HABITS WHERE NAME=="+habit_name;
        Cursor cursor = allData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Habits to show!!", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                id=cursor.getInt(0);
               hname=cursor.getString(1);
               addNewHabit(id,hname);
            }
        }
        //System.out.println("********"+id);

        //Home.getInstance().addNewHabit(id, habit_name);
    }

   public void addNewHabit(int id1, String habit_name){
        //onCreate(new Bundle());
        LinearLayout myNewRow=new LinearLayout(Home.this);
        myNewRow.setId(id);
       myNewRow.setOrientation(LinearLayout.HORIZONTAL);
       myNewRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
               LinearLayout.LayoutParams.WRAP_CONTENT));
        Button yes_b=new Button(this);
        yes_b.setText("+");
        yes_b.setId(id1);
        String yes=Integer.toString(id1)+" yes";
        Object yes_tag=yes;
        yes_b.setTag(yes_tag);

        yes_b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
       yes_b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               clickFun(view.getId(), view.getTag().toString());

           }});
        TextView name_tv=new TextView(Home.this);
        name_tv.setGravity(Gravity.CENTER);
        name_tv.setBackgroundColor(getResources().getColor(R.color.lightGreen));
        name_tv.setTextColor(getResources().getColor(R.color.lightOrange));
        name_tv.setTextSize(20.0f);
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

           name_tv.setBackground(getResources().getDrawable(R.drawable.textview_border));
       }
       name_tv.setText(habit_name);
       name_tv.setLayoutParams(new LinearLayout.LayoutParams(400,100));
       Button no_b=new Button(Home.this);
       no_b.setText("-");
       String no=Integer.toString(id1)+" no";
       Object no_tag=no;
       no_b.setTag(no_tag);
       no_b.setId(id1);
       no_b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               clickFun(view.getId(), view.getTag().toString());

       }});
       no_b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
               LinearLayout.LayoutParams.WRAP_CONTENT));
       myNewRow.addView(yes_b);
       myNewRow.addView(name_tv);
       myNewRow.addView(no_b);
       System.out.println("ID OF THIS ISS "+myNewRow.getId());

       firstlayout.addView(myNewRow);
    }
    String prev;
    String today;
    public void clickFun(int id, String tag){
        //Toast.makeText(Home.this, "You are on button "+Integer.toString(id)+" !! Tag is "+tag+" !!", Toast.LENGTH_LONG).show();
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = new Date();
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

        String newDateStr = postFormater.format(dateObj);
        Cursor cursor = allData2();
           Cursor cursor2=allData();
           String motivation="";
           while(cursor2.moveToNext()){

               if(id==cursor2.getInt(0)){
                   motivation=cursor2.getString(4);


               }
           }
           if(cursor.getCount()==0){
               Toast.makeText(Home.this, "Oops:( Something went wrong", Toast.LENGTH_LONG).show();
           }
           else{
               int idd;
               String hnamee="";

               while(cursor.moveToNext()){
                   if(cursor.getInt(3)<least_streak) {
                       least_streak = cursor.getInt(3);
                       lname = cursor.getString(1);
                   }
                   if(cursor.getInt(3)>least_streak) {
                       highest_streak = cursor.getInt(3);
                       hiname = cursor.getString(1);
                   }
                   if(!hiname.equals(lname)) {
                       hiText = Integer.toString(highest_streak) + " for " + hiname;
                       loText = Integer.toString(least_streak) + " for " + lname;
                   }
                   //hiLo_streak = getApplicationContext().getSharedPreferences("com.example.habithelper", 0);




                   int streak=0;
                   idd=cursor.getInt(0);
                   hnamee=cursor.getString(1);
                   if(idd==id){
                       if(tag.equals(Integer.toString(idd)+" no"))
                       streak=cursor.getInt(3)-1;
                       if(tag.equals(Integer.toString(idd)+" yes"))
                           streak=cursor.getInt(3)+1;

                       SQLiteDatabase dbb2=db.getWritableDatabase();
                       String msgAlert="Done For the day!! \nComeback Tomorrow!!\n";


                       // db.execSQL()
                       if(streak<0){
                           msgAlert="You are not doing "+hnamee+" since "+Integer.toString(streak+(-2)*streak) + "days!!";
                           if(streak==-3){
                               msgAlert+=". Your motivation message for this task is - \n"+motivation;
                           }
                       }

                           db.streakUpdate(dbb2, id, streak);
                       new AlertDialog.Builder(this).setIcon(R.drawable.owl)
                               .setTitle("Streak Alert")
                               .setMessage(msgAlert)
                               .setNeutralButton("OK !", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       //String val1=hiname;
                                       Intent intent3=new Intent(Home.this, Activity2.class);
                                       //intent3.putExtra("Least", loText);
                                       //intent3.putExtra("Highest", hiText);
                                       startActivity(intent3);
                                   }
                               })
                               .show();


                      // Toast.makeText(this, "Current Streak for "+hnamee+" is "+streak, Toast.LENGTH_SHORT).show();
                       /*if(streak<=0){
                           Toast.makeText(Home.this, "Streak is dead :((", Toast.LENGTH_SHORT).show();
                       }

                        */

                       break;
                   }

               }
           }

       }
       }





