package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class sleep_user_place extends Activity implements JsonResponse {
    ListView lv1;
    Button b1,b2;
    EditText e1;
    SharedPreferences sh;
    String searchitem;

    String [] details_id,place,type,lati,longi,val;
    public static String details_ids,lts,lgs,types,plac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_user_place);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lv1=(ListView)findViewById(R.id.lvhot);
//        lv1.setOnItemClickListener(this);

        e1=(EditText)findViewById(R.id.searchtxt);
        b2=(Button) findViewById(R.id.searchbtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchitem=e1.getText().toString();

                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse)sleep_user_place.this;
                String q="/searchplace?searchitem="+searchitem+"&log_id="+sh.getString("log_id","");
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) sleep_user_place.this;
        String q = "/view_places";
        q=q.replace(" ","%20");
        JR.execute(q);
    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.User_add_interests, menu);
//		return true;
//	}

    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub
        try {

            String method = jo.getString("method");
            Log.d("pearl", method);


            if(method.equalsIgnoreCase("view_places")) {
                String status = jo.getString("status");
                Log.d("pearl", status);
                Toast.makeText(getApplicationContext(), "normal View", Toast.LENGTH_SHORT).show();
                if (status.equalsIgnoreCase("success")) {

                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");



                    details_id = new String[ja1.length()];
                    type = new String[ja1.length()];
                    lati = new String[ja1.length()];
                    longi = new String[ja1.length()];
                    place = new String[ja1.length()];
                    val = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {

                        details_id[i] = ja1.getJSONObject(i).getString("details_id");
                        type[i] = ja1.getJSONObject(i).getString("type");
                        place[i] = ja1.getJSONObject(i).getString("place");
                        lati[i] = ja1.getJSONObject(i).getString("latitude");
                        longi[i] = ja1.getJSONObject(i).getString("longitude");

//					Toast.makeText(getApplicationContext(), val[i], Toast.LENGTH_SHORT).show();
//					val[i] = "Place Name : " + type_name[i] + " - " + title[i] + "\nLatitude:  " + lati[i] + "\nLongitude: " + longi[i] + "\nDescription:  " + des[i];
                        val[i] = "\nPlace Name : " + place[i] + " - " + type[i];


                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                    lv1.setAdapter(ar);


                }
            }

//
//				if (method.equalsIgnoreCase("searchplace")) {
//
//					String status = jo.getString("status");
//
//
//					if (status.equalsIgnoreCase("success")) {
//						JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
//						Toast.makeText(getApplicationContext(), "search View", Toast.LENGTH_SHORT).show();
//
//						type_id = new String[ja1.length()];
//						type_name = new String[ja1.length()];
//						title = new String[ja1.length()];
//						lati = new String[ja1.length()];
//						longi = new String[ja1.length()];
//						des = new String[ja1.length()];
//						place_id = new String[ja1.length()];
//
//						values = new String[ja1.length()];
//
//
//						for (int i = 0; i < ja1.length(); i++) {
//							type_id[i] = ja1.getJSONObject(i).getString("type_id");
//							type_name[i] = ja1.getJSONObject(i).getString("type_name");
//							title[i] = ja1.getJSONObject(i).getString("title");
//							lati[i] = ja1.getJSONObject(i).getString("latitude");
//							longi[i] = ja1.getJSONObject(i).getString("longitude");
//							des[i] = ja1.getJSONObject(i).getString("description");
////							place_id[i] = ja1.getJSONObject(i).getString("place_id");
//
//							values[i] = "Place Name : " + type_name[i] + " - " + title[i] + "\nLatitude:  " + lati[i] + "\nLongitude: " + longi[i] + "\nDescription:  " + des[i];
//
//
//						}
//						ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
//               			lv1.setAdapter(ar);
////						Custimage a = new Custimage(this, productname, category, price, quantity, image, details);
////						l1.setAdapter(a);
//
//
//					} else {
//						Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
//
//					}
//				}
        }catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }



    }

//    @Override
//    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//        // TODO Auto-generated method stub
//        details_ids=details_id[arg2];
//        types=type[arg2];
//        lts=lati[arg2];
//        lgs=longi[arg2];
//
//
//
//        final CharSequence[] items = {"View Map"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(sleep_user_place.this);
//        // builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
////
////
////                if (items[item].equals("View Map"))
////                {
////                    String url = "http://www.google.com/maps?saddr="+LocationService.lati+""+","+LocationService.logi+""+"&&daddr="+User_add_interests.lts+","+User_add_interests.lgs;
//////					  Toast.makeText(getApplicationContext(), "url.........."+url, Toast.LENGTH_SHORT).show();
////                    Log.d("url........", url);
////
////                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                    startActivity(in);
////                }
//
////                if (items[item].equals("Add Interest"))
////                {
////                    JsonReq JR=new JsonReq();
////                    JR.json_response=(JsonResponse) sleep_user_place.this;
////                    String q = "/user_add_interest?loginid="+Login.logid+"&type_id="+sleep_user_place.type_ids;
////                    q=q.replace(" ","%20");
////                    JR.execute(q);
////                }
//                if (items[item].equals("Set Alarm")) {
//                    JsonReq JR=new JsonReq();
//                    JR.json_response=(JsonResponse) sleep_user_place.this;
//                    String q = "/usersetalaram?loginid="+login.logid+"&lati="+lts+"&longi="+lgs+"&pid="+details_ids+"&types="+types;
//                    q=q.replace(" ","%20");
//                    JR.execute(q);
//                }
//
//                else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//
//        });
//        builder.show();
////	Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        //startActivityForResult(i, GALLERY_CODE);
//    }


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog")
                .setMessage("This is an alert message!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when "OK" is clicked
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when "Cancel" is clicked
                        dialog.dismiss();
                    }
                })
                .show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),home.class);
        startActivity(b);
    }




}
