package com.example.fengxinlin.nanodegreep6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by fengxinlin on 10/6/16.
 */
public class RecreationFragment extends Fragment{
    ListView list;

    private static final double LON = 37.2304;
    private static final double LAT = -80.4994;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recreations, container, false);
        final LocationDetails location_cinemas_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.recreation1Name),
                                getContext().getString(R.string.recreation1Desc)
                                , R.drawable.ic_war_m, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.recreation2Name),
                                getContext().getString(R.string.recreation2Desc) ,
                                R.drawable.ic_imax, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.recreation3Name),
                                getContext().getString(R.string.recreation3Desc),
                                R.drawable.ic_newman, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.recreation2Name),
                                getContext().getString(R.string.recreation2Desc) ,
                                R.drawable.ic_imax, LON, LAT)
                };

        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(new LocationAdapter(view.getContext(), location_cinemas_data));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LocationDetails current = location_cinemas_data[position];
                Intent details = new Intent(getActivity(),locationFullDisplayActivity.class);
                details.putExtra(getString(R.string.name) , current.getLocationName());
                details.putExtra(getString(R.string.desc),current.getLocationDesc());
                details.putExtra(getString(R.string.lon), current.getLon());
                details.putExtra(getString(R.string.lat), current.getLat());
                details.putExtra(getString(R.string.icon), current.getLocationIcon());
                startActivity(details);
            }
        });
        return view;
    }
}