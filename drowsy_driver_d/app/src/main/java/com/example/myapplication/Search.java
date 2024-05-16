package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.virtualtourist.PlaceHome;


public class Search extends AppCompatActivity {
    ListView lv1;
    Button b1,b2;
    EditText e1;
    SharedPreferences sh;
    String searchitem;
    String [] place_id,type,place,details,image,place_name,description,image_path,lati,longi,package_name,val, amount, no_of_days,no_of_nights,no_of_adults,no_of_children,category_name;

    public static String place_ids,lts,lgs,package_ids,amounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        lv1=(ListView)findViewById(R.id.lvhot);
//        b1=(Button)findViewById(R.id.searchbtn);
//        lv1.setOnItemClickListener(this);

        e1=(EditText)findViewById(R.id.searchtxt);
        b2=(Button) findViewById(R.id.searchbtn);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchitem=e1.getText().toString();

//                JsonReq JR=new JsonReq();
//                JR.json_response=(JsonResponse)Search.this;
////                String q="/searchplace?searchitem="+searchitem+"&log_id="+sh.getString("log_id","");
//                String q="/searchplace?searchitem="+searchitem;
//                q = q.replace(" ", "%20");
//                JR.execute(q);

//            }
//        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = e1.getText().toString();


                if (val.equalsIgnoreCase("ATM")) {
                    String uri = "http://www.google.com/maps/search/ATM/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("hospitals")) {
                    String uri = "http://www.google.com/maps/search/hospital/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("police")) {
                    String uri = "http://www.google.com/maps/search/police station/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("bus stop")) {
                    String uri = "http://www.google.com/maps/search/bus stop/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("pump")) {
                    String uri = "http://www.google.com/maps/search/pump/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("zoo")) {
                    String uri = "http://www.google.com/maps/search/zoo/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("mall")) {
                    String uri = "http://www.google.com/maps/search/mall/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("super market")) {
                    String uri = "http://www.google.com/maps/search/super market/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("theatre")) {
                    String uri = "http://www.google.com/maps/search/theatre/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                if (val.equalsIgnoreCase("beach")) {
                    String uri = "http://www.google.com/maps/search/beach/@" + LocationService.lati + "," + LocationService.logi;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
//                if (val.equalsIgnoreCase("today's news")) {
//                    String url = "https://www.google.com/search?q=todays+news&oq=todays+news";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    intent.setPackage("com.android.chrome");
//                    startActivity(intent);
//                }
//                if (val.equalsIgnoreCase("youtube")) {
//                    String youtubeUrl = "https://www.youtube.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }

//                if (val.equalsIgnoreCase("gmail")) {
//                    String youtubeUrl = "https://www.gmail.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }
//                if (val.equalsIgnoreCase("yahoo")) {
//                    String youtubeUrl = "https://www.yahoo.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }

//                if (val.equalsIgnoreCase("twitter")) {
//                    String youtubeUrl = "https://www.twitter.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }
//                if (val.equalsIgnoreCase("wikipedia")) {
//                    String youtubeUrl = "https://www.wikipedia .com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }


//                if (val.equalsIgnoreCase("stack overflow")) {
//                    String youtubeUrl = "https://www.stackoverflow.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//
//                }
//                if (val.equalsIgnoreCase("google")) {
//                    String youtubeUrl = "https://www.google.com";
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                    startActivity(intent);
//                }
                }
        });

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Search_word.class));
//
//            }
//        });

//        JsonReq JR=new JsonReq();
//        JR.json_response=(JsonResponse) Search_word.this;
//        String q = "/view_places";
//        q=q.replace(" ","%20");
//        JR.execute(q);
    }

//    @Override
//    public void response(JSONObject jo) {
//        // TODO Auto-generated method stub
//        try {
//
//            String method = jo.getString("method");
//            Log.d("pearl", method);
//
//
//            if (method.equalsIgnoreCase("viewplaces")) {
//                String status = jo.getString("status");
//                Log.d("pearl", status);
//                Toast.makeText(getApplicationContext(), "normal View", Toast.LENGTH_SHORT).show();
//                if (status.equalsIgnoreCase("success")) {
//
//                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
//
//
//                    place_id = new String[ja1.length()];
//                    type = new String[ja1.length()];
//
//                    place_name = new String[ja1.length()];
//                    description = new String[ja1.length()];
//
//                    lati = new String[ja1.length()];
//                    longi = new String[ja1.length()];
//                    image_path=new String[ja1.length()];
//                    val = new String[ja1.length()];
//
//
//                    for (int i = 0; i < ja1.length(); i++) {
//
//
////
//                        image_path[i]=ja1.getJSONObject(i).getString("image");
//                        place_id[i] = ja1.getJSONObject(i).getString("place_id");
//                        type[i] = ja1.getJSONObject(i).getString("type");
//                        place_name[i] = ja1.getJSONObject(i).getString("place");
//                        description[i] = ja1.getJSONObject(i).getString("details");
//                        lati[i] = ja1.getJSONObject(i).getString("latitude");
//                        longi[i] = ja1.getJSONObject(i).getString("longitude");
//
//
//
////                        Toast.makeText(getApplicationContext(), val[i], Toast.LENGTH_SHORT).show();
//                        val[i] = "Place Name : " + place_name[i] + " \nType" + type[i]+ "\nDescription:  " + description[i]+ "\nlatitude:  " + lati[i]+ "\nlongitude:  " + longi[i];
//
//
//                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
//                    lv1.setAdapter(ar);
////                    lv1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.customtext,val));
//////                    customemployee clist=new customemployee(this,image_path,place_name,package_name,amount,no_of_days,no_of_nights,no_of_adults,no_of_children);
////                    customemployee clist=new customemployee(this,image_path,place_name,package_name,amount,no_of_days,no_of_nights);
////                    lv1.setAdapter(clist);
//
//
//                }
//            }
//          if (method.equalsIgnoreCase("Customer_add_to_favorite")) {
//                String status = jo.getString("status");
//                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
//                if (status.equalsIgnoreCase("success")) {
//                    Toast.makeText(getApplicationContext(), "Favorite Place Added Successfully!", Toast.LENGTH_LONG).show();
//
////                    startActivity(new Intent(getApplicationContext(), Customer_add_to_favorite.class));
//                } else {
////					Toast.makeText(getApplicationContext(),"Interested Place Added Failed", Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Already Added....",
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//
//        }
//    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), home.class);
        startActivity(b);

    }

    }