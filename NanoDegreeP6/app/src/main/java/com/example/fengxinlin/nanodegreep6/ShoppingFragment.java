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
public class ShoppingFragment extends Fragment {
    ListView list;

    private static final double LON = 37.2304;
    private static final double LAT = -80.4994;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shopping, container, false);
        final LocationDetails location_shopping_data[] = new LocationDetails[]
                {

                        new LocationDetails(getContext().getString(R.string.shopping1Name),
                                getContext().getString(R.string.shopping1Desc)
                                ,  R.drawable.ic_umall, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.shopping2Name),
                                getContext().getString(R.string.shopping2Desc) ,
                                R.drawable.ic_oasis, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.shopping3Name),
                                getContext().getString(R.string.shopping3Desc),
                                R.drawable.ic_kroger, LON, LAT),
                        new LocationDetails(getContext().getString(R.string.shopping3Name),
                                getContext().getString(R.string.shopping3Desc),
                                R.drawable.ic_kroger, LON, LAT)
                };


        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(new LocationAdapter(view.getContext(), location_shopping_data));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LocationDetails current = location_shopping_data[position];
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