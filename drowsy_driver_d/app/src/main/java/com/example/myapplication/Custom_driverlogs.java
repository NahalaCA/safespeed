package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_driverlogs extends BaseAdapter {

    String[] date, speed, longitudinal, latitudinal, angle, place;
    private Context context;

    public Custom_driverlogs(Context appcontext, String[] date1, String[] speed1, String[] longi1, String[] lati1, String[] angle1, String[] place) {
        this.context = appcontext;
        this.date = date1;
        this.speed = speed1;
        this.longitudinal = longi1;
        this.latitudinal = lati1;
        this.angle = angle1;
        this.place = place;
    }

    @Override
    public int getCount() {
        return date.length;
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
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = inflator.inflate(R.layout.custom_driverlogs, null);

        } else {
            gridView = (View) view;

        }
        TextView T1 = gridView.findViewById(R.id.textView60);
        TextView T2 = gridView.findViewById(R.id.textView61);
        TextView T3 = gridView.findViewById(R.id.textView62);
        TextView T4 = gridView.findViewById(R.id.textView63);
        TextView T5 = gridView.findViewById(R.id.textView64);
        TextView T6 = gridView.findViewById(R.id.textView65);

        T1.setText(date[i]);

        T2.setText(speed[i]);

        T3.setText(longitudinal[i]);

        T4.setText(latitudinal[i]);

        T5.setText(angle[i]);
        T6.setText(place[i]);
        return gridView;
    }
}