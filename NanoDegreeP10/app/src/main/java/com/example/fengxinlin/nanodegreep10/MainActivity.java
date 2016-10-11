package com.example.fengxinlin.nanodegreep10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    final DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        TextView empty = (TextView) findViewById(R.id.empty);

        ArrayList<Inventory> listArray = new ArrayList<>();
        listArray.clear();
        listArray = db.read();
        if (listArray.size() == 0) {
            empty.setText("No Items(╯°□°）╯︵ ┻━┻");
        } else {
            empty.setText("");
        }
        ListViewAdapter customAdapter = new ListViewAdapter(listArray);
        customAdapter.notifyDataSetChanged();
        listView.setAdapter(customAdapter);

        Button add_button = (Button) findViewById(R.id.button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItem(view);
            }
        });
    }

    public void addNewItem(View view) {
        Intent intent = new Intent(this, AddNewItem.class);
        intent.putExtra("HEADER", "Add a New Item");
        startActivity(intent);
    }
}

