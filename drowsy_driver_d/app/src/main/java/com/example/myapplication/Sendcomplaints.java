package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sendcomplaints extends AppCompatActivity implements JsonResponse,AdapterView.OnItemClickListener{
    EditText e1,e2;
    Button b1;
    ListView l1;
    String placepro,complaint;
    String [] value,complaints,reply,date,sts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendcomplaints);

        e1=(EditText) findViewById(R.id.nofoods);
//        e2=(EditText) findViewById(R.id.splace);
        b1=(Button) findViewById(R.id.addbtn);
        l1=(ListView) findViewById(R.id.lv12);
        l1.setOnItemClickListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Sendcomplaints.this;
        String q = "/viewreply?id="+login.logid;
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                complaint=e1.getText().toString();
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Sendcomplaints.this;
                String q = "/sendcomplaint?id="+login.logid+"&complaint="+complaint;
                q = q.replace(" ", "%20");
                JR.execute(q);

                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Sendcomplaints.class));

            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                complaints = new String[ja1.length()];
                date = new String[ja1.length()];
                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    complaints[i] = ja1.getJSONObject(i).getString("feedback");
                    date[i] = ja1.getJSONObject(i).getString("feed_date");
                    value[i] = "Feedback: " + complaints[i] +  "\n Date: " + date[i] +"\n"+"\n";

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);


            }
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),home.class);
        startActivity(b);
    }
}