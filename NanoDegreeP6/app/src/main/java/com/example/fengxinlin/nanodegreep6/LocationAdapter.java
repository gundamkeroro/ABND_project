package com.example.fengxinlin.nanodegreep6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fengxinlin on 10/6/16.
 */
public class LocationAdapter extends BaseAdapter {
    private Context context;
    private LocationDetails[] locationDetails;

    public LocationAdapter(Context c, LocationDetails[] locationDetails) {
        context = c;
        this.locationDetails = locationDetails;

    }

    @Override
    public int getCount() {
        return locationDetails.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View list;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            list = new View(context);
            list = inflater.inflate(R.layout.listview_layout, null);
            TextView name = (TextView) list.findViewById(R.id.location_name);
            TextView desc = (TextView) list.findViewById(R.id.location_desc);
            ImageView img = (ImageView) list.findViewById(R.id.location_image);

            name.setText(locationDetails[i].getLocationName());
            desc.setText(locationDetails[i].getLocationDesc());
            img.setImageResource(locationDetails[i].getLocationIcon());
        } else {
            list = (View) view;
        }

        return list;
    }
}