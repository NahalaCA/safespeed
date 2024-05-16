package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
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

public class view_disaster extends AppCompatActivity {
    ListView li;
    String[] news_id, datetime, place, latitude, longitude, disaster_type, description, val;
    SharedPreferences sh;
    public static String ltd,lgd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_disaster);
        li = (ListView) findViewById(R.id.listview);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = "http://" + sh.getString("ip", "") + "/api/user_view_disaster";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject jsonObj = new JSONObject(new String(response.data));
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                news_id = new String[js.length()];
                                datetime = new String[js.length()];
                                place = new String[js.length()];
                                latitude = new String[js.length()];
                                longitude = new String[js.length()];
                                disaster_type = new String[js.length()];
                                description = new String[js.length()];
                                val = new String[js.length()];
                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    news_id[i] = u.getString("news_id");//dbcolumn name in double quotes
                                    datetime[i] = u.getString("datetime");//dbcolumn name in double quotes
                                    place[i] = u.getString("place");//dbcolumn name in double quotes
                                    latitude[i] = u.getString("latitude");//dbcolumn name in double quotes
                                    longitude[i] = u.getString("longitude");//dbcolumn name in double quotes
                                    disaster_type[i] = u.getString("disaster_type");//dbcolumn name in double quotes
                                    description[i] = u.getString("description");//dbcolumn name in double quotes
                                    val[i] = "DATETIME:" + datetime[i] + "\n\nPLACE :" + place[i] + "\n\nLATITUDE :" + latitude[i] + "\n\nLONGITUDE:" + longitude[i] + "\n\nDISASTER_TYPE :" + disaster_type[i] + "\n\nDESCRIPTION :" + description[i];
                                }
                                li.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, val));
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
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ll_id=l_id[position];
                SharedPreferences.Editor ed = sh.edit();
//                ed.putString("ll_id",ll_id);
                ed.commit();

                ltd=latitude[i];
                lgd=longitude[i];
                final CharSequence[] items = {"location",  "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(view_disaster.this);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("location"))
                        {
                            String url = "http://www.google.com/maps?saddr="+LocationService.lati+""+","+LocationService.logi+""+"&&daddr="+viewdiaster_for_emergency.lts+","+viewdiaster_for_emergency.lgs;
                            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(in);
                        }

                        else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }




                });
                builder.show();

            }
        });
    }
}