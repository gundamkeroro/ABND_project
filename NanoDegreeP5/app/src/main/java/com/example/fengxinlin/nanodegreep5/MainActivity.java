package com.example.fengxinlin.nanodegreep5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ReportCard data[] = new ReportCard[]
                {
                        new ReportCard(R.mipmap.ic_launcher, "Mathematics", "A"),
                        new ReportCard(R.mipmap.ic_launcher, "Music", "A+"),
                        new ReportCard(R.mipmap.ic_launcher, "English", "A"),
                        new ReportCard(R.mipmap.ic_launcher, "Electronics", "B"),
                        new ReportCard(R.mipmap.ic_launcher, "Physics", "B"),
                        new ReportCard(R.mipmap.ic_launcher, "Chemistry", "A"),
                        new ReportCard(R.mipmap.ic_launcher, "History", "A-")
                };

        ReportCardAdapter reportCardAdapter = new ReportCardAdapter(this, R.layout.report_card_listview, data);

        ListView listView = (ListView) findViewById(R.id.reportCardView);
        listView.setAdapter(reportCardAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
