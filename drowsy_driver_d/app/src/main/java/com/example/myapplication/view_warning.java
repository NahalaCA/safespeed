package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_warning extends AppCompatActivity {
    ListView l1;
    String[] description,statuss,date_time,val,user_id;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_warning);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lst);
        String url = "http://" + sh.getString("ip", "") + "/api/user_view_message";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject jsonObj = new JSONObject(new String(response.data));
                            if (jsonObj.getString("status").equalsIgnoreCase("ok"))
                            {
                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                description = new String[js.length()];
                                statuss = new String[js.length()];
                                date_time = new String[js.length()];
                                user_id = new String[js.length()];
                                val=new String[js.length()];
                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    description[i] = u.getString("description");//dbcolumn name in double quotes
                                    statuss[i] = u.getString("status");//dbcolumn name in double quotes
                                    date_time[i] = u.getString("date_time");//dbcolumn name in double quotes
                                    user_id[i] = u.getString("user_id");//dbcolumn name in double quotes
                                    val[i] = "DESCRIPTION :"+description[i]+"STATUS :"+statuss[i]+"\n\nDATE_TIME :"+date_time[i];
                                }
                                l1.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, val));
                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("lid", sh.getString("lid",""));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);




    }
}
