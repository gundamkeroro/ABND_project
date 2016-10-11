package com.example.fengxinlin.nanodegreep5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fengxinlin on 10/4/16.
 */
public class ReportCardAdapter extends ArrayAdapter<ReportCard> {
    Context context;
    int id;
    ReportCard[] RClist = null;
    public ReportCardAdapter(Context context, int id, ReportCard[] RClist) {
        super(context, id, RClist);
        this.context = context;
        this.id = id;
        this.RClist = RClist;
    }

    static class ReportCardDetails {
        ImageView subImage;
        TextView className;
        TextView classGrede;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ReportCardDetails reportCardDetails;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, parent, false);

            reportCardDetails = new ReportCardDetails();
            reportCardDetails.subImage = (ImageView) view.findViewById(R.id.subimage);
            reportCardDetails.className = (TextView) view.findViewById(R.id.name);
            reportCardDetails.classGrede = (TextView) view.findViewById(R.id.grade);

            view.setTag(reportCardDetails);
        } else {
            reportCardDetails = (ReportCardDetails) view.getTag();
        }

        ReportCard reportCard = RClist[position];
        reportCardDetails.subImage.setImageResource(reportCard.getSubjectImage());
        reportCardDetails.className.setText(reportCard.getClassName());
        reportCardDetails.classGrede.setText(reportCard.getGrade());
        return view;
    }
}
