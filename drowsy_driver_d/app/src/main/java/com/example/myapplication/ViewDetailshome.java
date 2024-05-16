package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetailshome extends AppCompatActivity {
    ImageView b2,b3,camara,drivmode,logout;
    TextView b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetailshome);
        b3=findViewById(R.id.button2);
        logout=findViewById(R.id.imglogout);
        camara=findViewById(R.id.camara);
        drivmode=findViewById(R.id.drivmode);
        drivmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://www.google.com/maps/search/ATM/@" + LocationService.lati + "," + LocationService.logi;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://www.google.com/maps/search/police station/@" + LocationService.lati + "," + LocationService.logi;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://www.google.com/maps/search/Traffic/@" + LocationService.lati + "," + LocationService.logi;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://www.google.com/maps/search/Ai camera/@" + LocationService.lati + "," + LocationService.logi;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
            }

        });
    }

    }
