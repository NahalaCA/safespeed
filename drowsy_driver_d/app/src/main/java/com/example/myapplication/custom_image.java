package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_image extends BaseAdapter {
    private Context context;

    String [] emid,lat,longt,date,photo,status,emer;

    public custom_image(Context con, String[]emid, String[]emer , String[] date,String[] lat,String[] longt,String[] status,String[] photo)

    {
        this.context=con;

        this.emid=emid;

        this.emer=emer;

        this.date=date;
        this.lat=lat;
        this.longt=longt;
        this.status=status;
        this.photo=photo;

    }

    @Override
    public int getCount() {
        return emid.length  ;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_image,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView labourname1=(TextView)gridView.findViewById(R.id.textview1);
        TextView labourname2=(TextView) gridView.findViewById(R.id.textView2);
        TextView labourname3=(TextView) gridView.findViewById(R.id.textView3);
        TextView labourname4=(TextView) gridView.findViewById(R.id.textView4);
        TextView labourname5=(TextView) gridView.findViewById(R.id.textView5);
        TextView labourname6=(TextView) gridView.findViewById(R.id.textView6);







        labourname1.setTextColor(Color.BLACK);
        labourname2.setTextColor(Color.BLACK);
        labourname3.setTextColor(Color.BLACK);
        labourname4.setTextColor(Color.BLACK);
        labourname5.setTextColor(Color.BLACK);
        labourname6.setTextColor(Color.BLACK);









        labourname1.setText("emid : "+emid[i]);
        labourname2.setText("\n\nemer :"+emer[i]);
        labourname3.setText("\n\n\ndate :"+date[i]);
        labourname4.setText("\n\n\n\nlat :"+lat[i]);
        labourname5.setText("\n\n\n\n\nlongt :"+longt[i]);
        labourname6.setText("\n\n\n\n\n\nstatus :"+status[i]);









        ImageView im=(ImageView) gridView.findViewById(R.id.imgss);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
        String url="http://"+ ip +"/"+photo[i];

        Log.d("+++",url);

        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}