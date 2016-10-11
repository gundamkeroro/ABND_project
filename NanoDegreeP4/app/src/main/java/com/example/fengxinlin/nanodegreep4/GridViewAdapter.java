package com.example.fengxinlin.nanodegreep4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fengxinlin on 10/3/16.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] web;
    private final int[] Imageid;
    public GridViewAdapter(Context c, String[] web, int[] Imageid) {
        context = c;
        this.Imageid = Imageid;
        this.web = web;
    }
    @Override
    public int getCount() {
        return web.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);
            imageView.setImageResource(Imageid[position]);

        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
