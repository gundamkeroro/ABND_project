package com.example.fengxinlin.nanodegreep9;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(MainActivity.this);
        Log.v("DB", "OK");
        Log.d("Insert: ", "Inserting ..");
        db.addHabit(new HabitObject("Game", 3));
        db.addHabit(new HabitObject("Sleep", 5));

        Cursor c = db.getDetail(2);
        HabitObject H = new HabitObject(c.getString(1), Integer.parseInt(c.getString(2)));

        Log.d("Reading: ", "Reading ...");
        Log.v("Update: ", H.getHabit() + " " + H.getFrequency());
    }
}
