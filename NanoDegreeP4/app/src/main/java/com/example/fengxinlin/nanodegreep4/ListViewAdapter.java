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
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] title;
    private final int[] Imageid;

    public ListViewAdapter(Context c, String[] title, int[] Imageid) {
        context = c;
        this.Imageid = Imageid;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            listView = new View(context);
            listView = inflater.inflate(R.layout.listview_layout, null);
            TextView textView = (TextView) listView.findViewById(R.id.list_text);
            ImageView imageView = (ImageView) listView.findViewById(R.id.list_image);
            textView.setText(title[i]);
            imageView.setImageResource(Imageid[i]);

        } else {
            listView = (View) view;
        }
        return listView;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
