package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class driver_logs extends AppCompatActivity {
    String[] date, speed, longitudinal, latitudinal, angle, place;
    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_logs);
        LV = (ListView) findViewById(R.id.list_dv);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
//        String url = "http://" + hu + ":8000/myapp/partner_view_driverlogs/";

        String url = sh.getString("url","")+"/partner_view_driverlogs";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status = jsonObj.getString("status");
                            if (status.equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("data");
                                //  Toast.makeText(getApplicationContext(),js+"",Toast.LENGTH_SHORT).show();
                                date = new String[js.length()];
                                speed = new String[js.length()];
                                longitudinal = new String[js.length()];
                                latitudinal = new String[js.length()];
                                angle = new String[js.length()];
                                place = new String[js.length()];
//

                                for (int i = 0; i < js.length(); i++) {
                                    //Toast.makeText(getApplicationContext(),js+"",Toast.LENGTH_SHORT).show();

                                    JSONObject u = js.getJSONObject(i);
                                    date[i] = u.getString("datetime");
                                    speed[i] = u.getString("speed");
                                    latitudinal[i] = u.getString("latitude");
                                    longitudinal[i] = u.getString("longitude");
                                    angle[i] = u.getString("angle");
                                    place[i] = u.getString("place");

                                }
                                //  L6.setAdapter(new Custom_add_partner(getApplicationContext(),driverid,drivername,driverphoto));

                                LV.setAdapter(new Custom_driverlogs(getApplicationContext(), date, speed, longitudinal, latitudinal, angle, place));


                            } else {
                                Toast.makeText(getApplicationContext(), "No values", Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };


        requestQueue.add(postRequest);


    }
}