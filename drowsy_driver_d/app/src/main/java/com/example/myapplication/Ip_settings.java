package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ip_settings extends AppCompatActivity {
    EditText e1;
    Button b1;
    public  static String ip;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_settings);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=findViewById(R.id.etip);
        b1=findViewById(R.id.btip);
        e1.setText(sh.getString("ip","192.168.146.143:5006"));
      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ip=e1 .getText() .toString();

              if (ip.equals("")) {
                  e1.setError("Enter IP address");
                  e1.setFocusable(true);

              }else {
                  // TODO Auto-generated method stub
                  ip=e1.getText().toString();
                  SharedPreferences.Editor ed=sh.edit();
                  ed.putString("url", "http://"+ ip +"/api");
                  ed.putString("ip",ip);
                  ed.commit();
                  //startService(new Intent(getApplicationContext(), LocationService.class));
                  Intent i=new Intent(getApplicationContext(),login.class);
                  startActivity(i);
              }



          }
      });
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit  :")
                .setMessage("Are you sure you want to exit..?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Intent i = new Intent(Intent.ACTION_MAIN);
                        i.addCategory(Intent.CATEGORY_HOME);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }).setNegativeButton("No", null).show();

    }



}