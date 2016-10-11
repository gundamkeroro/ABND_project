package com.example.fengxinlin.nanodegreep4.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fengxinlin.nanodegreep4.GridViewAdapter;
import com.example.fengxinlin.nanodegreep4.R;


/**
 * Created by fengxinlin on 10/3/16.
 */


//for getting the real information on internet, we need to use the API on music website and for loading real album photos, we need some 3rd party library like:fresco.
public class artists extends Fragment {
    GridView gridView;
    String[] artists = {
            "Adele",
            "Jay Chou",
            "Justin Bieber",
            "Maroon 5",
            "Unknown Artist",
    };

    int[] imageId = {
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artists, container, false);
        gridView=(GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new GridViewAdapter(view.getContext(),artists, imageId ));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(view.getContext(), "You will be redirected to the Songs of the Artist : " +artists[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }
}
