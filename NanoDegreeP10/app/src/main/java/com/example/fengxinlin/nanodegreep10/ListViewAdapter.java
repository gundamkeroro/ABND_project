package com.example.fengxinlin.nanodegreep10;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by fengxinlin on 10/8/16.
 */
public class ListViewAdapter extends BaseAdapter {
    private static final String TAG = ListViewAdapter.class.getSimpleName();
    ArrayList<Inventory> listArray;

    public ListViewAdapter(ArrayList<Inventory> listArray) {
        this.listArray = new ArrayList<Inventory>(listArray);
    }

    @Override
    public int getCount() {
        return listArray.size();
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        //take item from list and set view

        final Inventory dataModel = listArray.get(index);

        final TextView productName = (TextView) view.findViewById(R.id.productName);
        productName.setText(dataModel.getProductName());

        final TextView productAvailable = (TextView) view.findViewById(R.id.productAvailable);
        productAvailable.setText("" + dataModel.getQuantity());

        final TextView productPrice = (TextView) view.findViewById(R.id.productPrice);
        productPrice.setText("$" + dataModel.getPrice());

        Button button = (Button) view.findViewById(R.id.listItemButton);


        this.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(view.getContext());
                dataModel.sale();
                if (dataModel.getQuantity() == 0) {
                    Toast.makeText(parent.getContext(), "No more item(s) of " + dataModel.getProductName() + " left out.", Toast.LENGTH_SHORT).show();
                }
                Long i = db.update(dataModel.getId(), dataModel);
                productAvailable.setText("" + dataModel.getQuantity());
            }
        });
        //this one works on entire item
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details = new Intent(parent.getContext(), ItemFullDisplayActivity.class);
                details.putExtra("productName", dataModel.getProductName());
                details.putExtra("productQuantity", dataModel.getQuantity());
                details.putExtra("price", dataModel.getPrice());
                details.putExtra("id", dataModel.getId());
                parent.getContext().startActivity(details);
            }
        });
        return view;
    }
}
