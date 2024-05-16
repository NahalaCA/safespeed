package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class home extends AppCompatActivity implements  JsonResponse {
    ImageView b2,b3,camara,drivmode,moredetails,speedlimit,imglogout2,setalam,feedback;
    TextView b4;

    private Handler handler = new Handler();
    public static String[] start_date, end_date, time_mrg, time_aftn, time_nyt,mname;
    public static String[] place, type, latitude, longitude;
    private static final long LOCATION_SERVICE_INTERVAL = 60000; // 60 seconds
    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor e=sh.edit();
        e.putString("val", "user");
        e.commit();
        b2=findViewById(R.id.b1);
        b3=findViewById(R.id.button2);
        b4=findViewById(R.id.button3);
        camara=findViewById(R.id.camara);
        drivmode=findViewById(R.id.drivmode);
        moredetails=findViewById(R.id.moredetails);
        speedlimit=findViewById(R.id.speedlimit);
        imglogout2=findViewById(R.id.imglogout2);
        setalam=findViewById(R.id.setalam);
        feedback=findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sendcomplaints.class);
                startActivity(i);
            }
        });
        setalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), sleep_user_place.class);
                startActivity(i);
            }
        });
        imglogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });
        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewDetailshome.class);
                startActivity(i);
            }
        });
        speedlimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Speed_settings.class);
                startActivity(i);
            }
        });
        drivmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EnableDrivermode.class);
                startActivity(i);
            }
        });
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FaceActivity.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), view_disaster.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), view_warning.class);
                startActivity(i);
            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), change_password.class);
                startActivity(i);
            }
        });
    }

    private Runnable locationServiceRunnable = new Runnable() {
        @Override
        public void run() {
//            startService(new Intent(getApplicationContext(), LocationService.class));

            JsonReq JR = new JsonReq();
            JR.json_response = (JsonResponse) home.this;
            String q = "/notificationnnnnnnnn?log_id=" +login.logid;
            q = q.replace(" ", "%20");
            JR.execute(q);

            // Schedule the next execution after the specified interval
            handler.postDelayed(this, LOCATION_SERVICE_INTERVAL);
        }
    };

    @Override
    protected void onDestroy() {
        // Remove any callbacks to prevent memory leaks
        handler.removeCallbacks(locationServiceRunnable);
        super.onDestroy();
    }
//
//    private String getCurrentTime() {
//        // Get current date and time
//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//
//        // Format the time as needed
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//        String formattedTime = timeFormat.format(today);
//
//        return formattedTime;
//    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("method", status);

            if (status.equalsIgnoreCase("success")) {

//                String currenttime = getCurrentTime();
//				Toast.makeText(getApplicationContext(), "Current Time : " + currenttime, Toast.LENGTH_SHORT).show();
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");


                start_date = new String[ja1.length()];
                end_date = new String[ja1.length()];
                time_mrg = new String[ja1.length()];
                time_aftn = new String[ja1.length()];
                time_nyt = new String[ja1.length()];
                mname = new String[ja1.length()];

//				Toast.makeText(getApplicationContext(),"fff fffffffffff: ",Toast.LENGTH_SHORT).show();


                for (int i = 0; i < ja1.length(); i++) {

                    start_date[i] = ja1.getJSONObject(i).getString("place");
                    end_date[i] = ja1.getJSONObject(i).getString("place");
                    time_mrg[i] = ja1.getJSONObject(i).getString("latitude");
                    time_aftn[i] = ja1.getJSONObject(i).getString("latitude");
                    time_nyt[i] = ja1.getJSONObject(i).getString("type");
                    mname[i] = ja1.getJSONObject(i).getString("type");

//                    if (time_mrg[i].equalsIgnoreCase(currenttime) || time_aftn[i].equalsIgnoreCase(currenttime) || time_nyt[i].equalsIgnoreCase(currenttime)) {

////
//                        SharedPreferences.Editor e = sh.edit();
//                        e.putString("mname", mname[i]);
//                        e.commit();

//						Toast.makeText(getApplicationContext(), "time::::;", Toast.LENGTH_SHORT).show();
//                        startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
//                        showFullScreenNotification();

//                    }


                }

            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
