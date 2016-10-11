package com.example.fengxinlin.nanodegreep4.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fengxinlin.nanodegreep4.ListViewAdapter;
import com.example.fengxinlin.nanodegreep4.R;

/**
 * Created by fengxinlin on 10/3/16.
 */
public class songs extends Fragment {
    ListView list;
    String[] songTitle = {
            "Hello",
            "7 years",
            "Hello",
            "7 years",
            "Hello",
            "7 years"

    } ;
    String[] singerName = {
            "Adele",
            "Lucas Graham",
            "Adele",
            "Lucas Graham",
            "Adele",
            "Lucas Graham"
    };
    int[] imageId = {
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songs, container, false);

        list=(ListView) view.findViewById(R.id.list);
        list.setAdapter(new ListViewAdapter(view.getContext(),songTitle, imageId ));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(view.getContext(), "Now Playing Song: " +songTitle[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}
