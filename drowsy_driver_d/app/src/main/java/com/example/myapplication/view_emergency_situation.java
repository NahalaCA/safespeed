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

public class view_emergency_situation extends AppCompatActivity {
ListView l1;
SharedPreferences sh;
String[]  emid,lat,longt,date,photo,status,emer;
public static  String la,lo,em_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emergency_situation);
        l1=findViewById(R.id.listview);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip","");
        String url = "http://" + sh.getString("ip", "") + "/api/view_emergency";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>(){
                    @Override
                    public void onResponse(NetworkResponse response) {


                        try {
                            JSONObject jsonObj = new JSONObject(new String(response.data));
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(),"evida und", Toast.LENGTH_LONG).show();

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                emid = new String[js.length()];
                                emer = new String[js.length()];

                                date = new String[js.length()];
                                lat = new String[js.length()];
                                longt=new String[js.length()];
                                status = new String[js.length()];
                                photo=new String[js.length()];
//                                val=new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    emid[i] = u.getString("emergency_id");//dbcolumn name in double quotes

                                    emer[i] = u.getString("emergency");//dbcolumn name in double quotes
                                    date[i] = u.getString("date");
                                    lat[i] = u.getString("latitude");
                                    longt[i] = u.getString("longitude");
                                    status[i] = u.getString("status");
                                    photo[i] = u.getString("image");
//

//                                    val[i] = "category_id : "+cid[i]+"\n\ncategory    : "+cate[i] ;


                                }
//                                l1.setAdapter(new custom_image(getApplicationContext(),name,gender,place,photo,email,district,phone));//custom_view_service.xml and li is the listview object
                                l1.setAdapter(new custom_image(getApplicationContext(),emid,emer,date,lat,longt,status,photo));//custom_view_service.xml and li is the listview object

//                                ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
//                                l1.setAdapter(aa);

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
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("login_id", sh.getString("lid",""));//passing to python
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                //   params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//        ++++++++++++++++++++++add+++
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                em_id=emid[i];
                SharedPreferences.Editor ed = sh.edit();
//                ed.putString("ll_id",ll_id);
                ed.commit();

                la=lat[i];
                lo=longt[i];
                final CharSequence[] items = {"location","solved", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(view_emergency_situation.this);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("location"))
                        {
                            String url = "http://www.google.com/maps?saddr="+LocationService.lati+""+","+LocationService.logi+""+"&&daddr="+view_emergency_situation.la+","+view_emergency_situation.lo;
                            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(in);
                        }
                        else if (items[item].equals("solved")) {
                            sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            String url=sh.getString("url","")+"/update_status";
                            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                                    new Response.Listener<NetworkResponse>() {
                                        @Override
                                        public void onResponse(NetworkResponse response) {
                                            try {
//                                    pd.dismiss();


                                                JSONObject obj = new JSONObject(new String(response.data));

                                                if(obj.getString("status").equals("ok")){
                                                    Toast.makeText(getApplicationContext(), " success", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(getApplicationContext(), home.class);
                                                    startActivity(i);

                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(),"success" ,Toast.LENGTH_SHORT).show();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }) {


                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                    params.put("username1", username1.getText().toString());//passing to python
//                                    params.put("password1", password1.getText().toString());//passing to python
//                                    params.put("password2", Password2.getText().toString());//passing to python
                                    params.put("em_id",view_emergency_situation.em_id);//passing to python

                                    return params;
                                }


                                @Override
                                protected Map<String, DataPart> getByteData() {
                                    Map<String, DataPart> params = new HashMap<>();
                                    long imagename = System.currentTimeMillis();
                                    //  params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                                    return params;
                                }
                            };

                            Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);

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