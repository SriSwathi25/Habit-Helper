package com.example.habithelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.habithelper.data.DBHelper;

import java.util.ArrayList;

public class view extends AppCompatActivity {
    private DBHelper db;
    ArrayList<String> habits;
    ArrayList<String> streak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ListView listView = findViewById(R.id.listView);
        Button btn=findViewById(R.id.view_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.this, Activity2.class);
                startActivity(intent);
            }
        });
        habits = new ArrayList<>();
        streak = new ArrayList<>();
        habits.add("Add new habit..?");
        streak.add("-1");
        db=new DBHelper(this);
        SQLiteDatabase dbb=db.getReadableDatabase();
        Cursor cursor=dbb.rawQuery("select * from TRACK", null);
        while(cursor.moveToNext()){
            String name=cursor.getString(1);
            String str=Integer.toString(cursor.getInt(3));
            habits.add(name);
            streak.add(str);

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, habits);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent=new Intent(view.this, addHabit.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(view.this, "Task name : "+habits.get(i)+"\nCurrent Streak : "+streak.get(i), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
