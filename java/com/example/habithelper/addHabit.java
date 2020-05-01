package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.habithelper.data.DBHelper;

public class addHabit extends AppCompatActivity {


    boolean sending=false;

    private DBHelper db;
    static int curr_id=0;
    static EditText hname;
    static EditText hsession;
    static EditText hfreq;
    static EditText hmotiv;
    static String hname1;
    static String hsession1;
    static String hfreq1;
    static String hmotiv1;
    Button add_save;
    static boolean fieldsFull=false;
    public static final String DATABASE_NAME = "HABITHELPER.db";
    public static final String TABLE_NAME1 = "HABITS";
    public static final String COL_1_1= "ID";
    public static final String COL_1_2= "NAME";
    public static final String COL_1_3= "TIME";
    public static final String COL_1_4= "DAYS";
    public static final String COL_1_5= "MESSAGE";


    public static final String TABLE_NAME2 = "TRACK";
    public static final String COL_2_1= "ID";
    public static final String COL_2_2= "NAME";
    public static final String COL_2_3= "CURRENTCOUNT";
    public static final String COL_2_4= "STREAK";
    //private Object Home;
    //private Object Home;


    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        db= new DBHelper(this);
        Button back = findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addHabit.this, Activity2.class);
                startActivity(intent);
            }
        });
            add_save = findViewById(R.id.add_savee);
            hname=findViewById(R.id.hname_value);
            hsession=findViewById(R.id.sessionValue);
            hfreq=findViewById(R.id.freqValue);
            hmotiv=findViewById(R.id.motivmsg);

            add_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    checkFields();
                    boolean val=insertData();
                    //System.out.println("Booo"+hname1+" End");

                    if(val) {

                        EmptyAfterInsert();
                          }
                    /*
                    Intent myIntent4 = new Intent(addHabit.this, Home.class);
                    myIntent4.putExtra("HABIT_NAME", hname1);
                    myIntent4.putExtra("HABIT_ID", Integer.toString(id));
                    //myIntent4.putExtra("newValues", "true");
                    addHabit.this.startActivity(myIntent4);
                     */

                }
            });


        }



    public  static void checkFields(){
        hname1=hname.getText().toString();
        hsession1=hsession.getText().toString();
        hfreq1=hfreq.getText().toString();
        hmotiv1=hmotiv.getText().toString();
        if(hname1.equals("") || hsession1.equals("")|| hfreq1.equals("") || hmotiv1.equals("")){
            fieldsFull=false;
        }
        else{
            fieldsFull=true;
        }

    }
    public boolean insertData(){
        //String insert_query="INSERT INTO HABITS (NAME, TIME, DAYS, MESSAGE) VALUES ('EAT', 'DAY', '10', 'BUBUBU');";
        if(fieldsFull){
            SQLiteDatabase dbb = db.getWritableDatabase();
            //curr_id+=1;

            ContentValues contentValues=new ContentValues();
            //contentValues.put("ID", curr_id);
            contentValues.put(COL_1_2, hname1);
            contentValues.put(COL_1_3, hsession1);
            contentValues.put(COL_1_4, hfreq1);
            contentValues.put(COL_1_5, hmotiv1);
            long result=dbb.insert(TABLE_NAME1, null, contentValues);
            /*String insert_query="INSERT INTO HABITS (NAME, TIME, DAYS, MESSAGE) VALUES ('"+hname1+"', '"+hsession1+"', '"+hfreq1+"', '"+hmotiv1+"');";
            db.execSQL(insert_query);
            System.out.println(hname1);
            */
            ContentValues contentValues2=new ContentValues();
            //contentValues.put("ID", curr_id);
            contentValues2.put(COL_2_2, hname1);
            contentValues2.put(COL_2_3, 0);
            contentValues2.put(COL_2_4, 0);

            long result2=dbb.insert(TABLE_NAME2, null, contentValues2);
            if(result!=-1 && result2!=-1){
                Toast.makeText(this,"Habit Saved Successfully", Toast.LENGTH_LONG).show();
                return true;
            }
            Toast.makeText(this,"Oops :( Something went wrong", Toast.LENGTH_LONG).show();
            return false;
        }
        else{

            Toast.makeText(this, "Fill all the fields to proceed further!!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void EmptyAfterInsert(){

        hname.getText().clear();
        hsession.getText().clear();
        hfreq.getText().clear();
        hmotiv.getText().clear();

    }

}

