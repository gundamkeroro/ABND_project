package com.example.fengxinlin.nanodegreep6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fengxinlin on 10/6/16.
 */
public class locationFullDisplayActivity extends AppCompatActivity{
    public String Name;
    public void setName(String s){
        Name = s;
    }
    public String getName(){
        return Name;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_full_display);
        // Get Name, Description, Image views
        TextView name = (TextView)findViewById(R.id.title);
        TextView desc = (TextView)findViewById(R.id.description);
        ImageView img = (ImageView)findViewById(R.id.image);

        // Get data passed in from Fragment
        Intent details = getIntent();
        setTitle(details.getStringExtra(getString(R.string.name)));
        setName(details.getStringExtra(getString(R.string.name)));

        name.setText(details.getStringExtra(getString(R.string.name)));
        desc.setText(details.getStringExtra(getString(R.string.desc)));

        final int id = details.getIntExtra(getString((R.string.icon)),-1);
        if(id != -1) {
            img.setImageResource(id);
        }
        else{
            img.setVisibility(View.GONE);
        }


    }

    public void openGoogleMap(View view){
        // Map point based on address
        String n = getName();
        Uri location = Uri.parse("geo:0,0?q="+n+"+Blacksburg+Virginia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(mapIntent);

    }

}



