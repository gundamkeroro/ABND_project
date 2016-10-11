package com.example.fengxinlin.nanodegreep4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fengxinlin on 10/3/16.
 */

//for playing music in real, MediaPlayer class need to be implemented.
public class nowPlaying extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playing, container, false);

        return view;
    }

}
