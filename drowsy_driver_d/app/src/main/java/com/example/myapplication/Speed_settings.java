package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Speed_settings extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    String speed = "";
    String mode = "";
    int brightness = 0;
    String callblock = "Off", touch = "Off";
    String messagetype = "ALL";
    String bluet = "On";
    String automessage = "NO";

    String message = "";
    //    };
    Button B22, B23, B24, B25, B26;
    RadioButton R9, R10, R11, R12, R13;
    ToggleButton SW3, SW4;
    SwitchCompat SW5;
    ToggleButton TBL;
    EditText E1, BR;
    SeekBar SB2;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_settings);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SB2 = findViewById(R.id.sb2);
        B22 = (Button) findViewById(R.id.btn_psplimit1);
        B23 = (Button) findViewById(R.id.btn_psplimit2);
        B24 = (Button) findViewById(R.id.btn_psdlimit3);
        B25 = (Button) findViewById(R.id.btn_spsetsave);
        B26 = (Button) findViewById(R.id.btn_spsetcancel);
        R9 = (RadioButton) findViewById(R.id.rd_psilent);
        R10 = (RadioButton) findViewById(R.id.rd_pvibrate);
        R11 = (RadioButton) findViewById(R.id.rd_pgeneral);
        R12 = (RadioButton) findViewById(R.id.radioButton2);
        R13 = (RadioButton) findViewById(R.id.radioButton3);
        E1 = (EditText) findViewById(R.id.editText2);
        BR = (EditText) findViewById(R.id.editText4);
        SW3 = (ToggleButton) findViewById(R.id.swt_ptouch);
        SW4 = (ToggleButton) findViewById(R.id.swt_pcallblock);
        SW5 = (SwitchCompat) findViewById(R.id.switch1);

        TBL = (ToggleButton) findViewById(R.id.toggleBl);

        B22.setOnClickListener(this);
        B23.setOnClickListener(this);
        B24.setOnClickListener(this);
        B25.setOnClickListener(this);
        B26.setOnClickListener(this);
//        TBL.setOnClickListener(this);


        TBL.setOnCheckedChangeListener(this);
        SW3.setOnCheckedChangeListener(this);
        SW4.setOnCheckedChangeListener(this);
        SW5.setOnCheckedChangeListener(this);

//        SB2.setOnSeekBarChangeListener(this);
        SB2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brightness = progress;
                BR.setText(String.valueOf(brightness));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onClick(View view) {


        if (view == B22) {
            speed = "0-40";
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("url", "");

//            String url = ip + "/speedsetting_view/";

            String url = sh.getString("url","")+"/speedsetting_view";
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String sucs = jsonObj.getString("status");
                                if (sucs.equalsIgnoreCase("ok")) {
                                    String mode = jsonObj.getString("mode");
                                    if (mode.equals("SILENT")) {
                                        R9.setChecked(true);


                                    }
                                    if (mode.equals("VIBRATE")) {
                                        R10.setChecked(true);
                                    }
                                    if (mode.equals("GENERAL")) {
                                        R11.setChecked(true);
                                    }

                                    String brightness = jsonObj.getString("bright");
                                    SB2.setProgress(Integer.parseInt(brightness));
                                    BR.setText(brightness);

                                    String touch1 = jsonObj.getString("touch");
                                    if (touch1.equals("On"))
                                        SW3.setChecked(true);
                                    else
                                        SW3.setChecked(false);


                                    String callblock = jsonObj.getString("callblock");
                                    if (callblock.equals("On"))
                                        SW4.setChecked(true);
                                    else
                                        SW4.setChecked(false);
                                    String automessage = jsonObj.getString("automessage");
                                    if (automessage.equals("On"))
                                        SW5.setChecked(true);

                                    else
                                        SW5.setChecked(false);
                                    String message = jsonObj.getString("message");
                                    E1.setText(message);
                                    String type = jsonObj.getString("messagetype");
                                    if (type.equals("ALL")) {
                                        R12.setChecked(true);


                                    }
                                    if (type.equals("MY CONTACTS")) {
                                        R13.setChecked(true);
                                    }


                                    String blt1 = jsonObj.getString("tooth");
                                    if (blt1.equals("On"))
                                        TBL.setChecked(true);
                                    else
                                        TBL.setChecked(false);

//                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    params.put("speed", speed);

                    return params;
                }
            };


            requestQueue.add(postRequest);


        }
        if (view == B23) {
            speed = "40-60";
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("url", "");

//            String url = ip + "/speedsetting_view/";
            String url = sh.getString("url","")+"/speedsetting_view";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String sucs = jsonObj.getString("status");
                                if (sucs.equalsIgnoreCase("ok")) {
                                    String mode = jsonObj.getString("mode");
                                    if (mode.equalsIgnoreCase("SILENT")) {
                                        R9.setChecked(true);
                                    }
                                    if (mode.equalsIgnoreCase("VIBRATE")) {
                                        R10.setChecked(true);
                                    }
                                    if (mode.equalsIgnoreCase("GENERAL")) {
                                        R11.setChecked(true);
                                    }

                                    String brightness = jsonObj.getString("bright");
                                    SB2.setProgress(Integer.parseInt(brightness));
                                    BR.setText(brightness);

                                    String touch1 = jsonObj.getString("touch");
                                    if (touch1.equalsIgnoreCase("On"))
                                        SW3.setChecked(true);
                                    else
                                        SW3.setChecked(false);


                                    String callblock = jsonObj.getString("callblock");
                                    if (callblock.equalsIgnoreCase("On"))
                                        SW4.setChecked(true);
                                    else
                                        SW4.setChecked(false);

                                    String automessage = jsonObj.getString("automessage");
                                    if (automessage.equalsIgnoreCase("On"))
                                        SW5.setChecked(true);

                                    else
                                        SW5.setChecked(false);
                                    String message = jsonObj.getString("message");
                                    E1.setText(message);
                                    String type = jsonObj.getString("messagetype");
                                    if (type.equals("ALL")) {
                                        R12.setChecked(true);


                                    }
                                    if (type.equals("MY CONTACTS")) {
                                        R13.setChecked(true);
                                    }

                                    String blt1 = jsonObj.getString("tooth");
                                    if (blt1.equals("On"))
                                        TBL.setChecked(true);
                                    else
                                        TBL.setChecked(false);


//                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    params.put("speed", speed);

                    return params;
                }
            };
            requestQueue.add(postRequest);
        }

        if (view == B24) {
            speed = ">60";
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip = sh.getString("url", "");

//            String url = ip + "/speedsetting_view/";
            String url = sh.getString("url","")+"/speedsetting_view";


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String sucs = jsonObj.getString("status");
                                if (sucs.equalsIgnoreCase("ok")) {
                                    String mode = jsonObj.getString("mode");
                                    if (mode.equals("SILENT")) {
                                        R9.setChecked(true);
                                    }
                                    if (mode.equals("VIBRATE")) {
                                        R10.setChecked(true);
                                    }
                                    if (mode.equals("GENERAL")) {
                                        R11.setChecked(true);
                                    }

                                    String brightness = jsonObj.getString("bright");
                                    SB2.setProgress(Integer.parseInt(brightness));
                                    BR.setText(brightness);

                                    String touch1 = jsonObj.getString("touch");
                                    if (touch1.equals("On"))
                                        SW3.setChecked(true);
                                    else
                                        SW3.setChecked(false);


                                    String callblock = jsonObj.getString("callblock");
                                    if (callblock.equals("On"))
                                        SW4.setChecked(true);
                                    else
                                        SW4.setChecked(false);

                                    String automessage = jsonObj.getString("automessage");
                                    if (automessage.equals("On"))
                                        SW5.setChecked(true);

                                    else
                                        SW5.setChecked(false);
                                    String message = jsonObj.getString("message");
                                    E1.setText(message);
                                    String type = jsonObj.getString("messagetype");
                                    if (type.equals("ALL")) {
                                        R12.setChecked(true);

                                    }
                                    if (type.equals("MY CONTACTS")) {
                                        R13.setChecked(true);
                                    }


                                    String blt1 = jsonObj.getString("tooth");
                                    if (blt1.equals("On"))
                                        TBL.setChecked(true);
                                    else
                                        TBL.setChecked(false);

//                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    params.put("speed", speed);

                    return params;
                }
            };


            requestQueue.add(postRequest);


        }
        if (view == B25) {
            message = E1.getText().toString();

            if (R9.isChecked() == true) {
                mode = "SILENT";

            } else if (R10.isChecked() == true) {
                mode = "VIBRATE";


            } else {
                mode = "GENERAL";


            }
            if (R12.isChecked() == true) {
                messagetype = "ALL";

            } else if (R13.isChecked() == true) {
                messagetype = "MY CONTACTS";


            }

//            if (TBL.isEnabled() || TBL.isChecked()) bluet = "On";
//            else bluet = "Off";
//
//            if (SW3.isEnabled() || SW3.isChecked()) touch = "On";
//            else touch = "Off";
//
//            if (SW4.isEnabled() || SW4.isChecked()) callblock = "On";
//            else callblock = "Off";


            if (SW5.isChecked() == true) {
                automessage = "YES";

            } else {
                automessage = "NO";
            }

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String IP = sp.getString("ip", "");
//            String url = "http://" + IP + ":8000/myapp/driverspeed_settings/";
            String url = sh.getString("url","")+"/driverspeed_settings";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


//                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                String sucs = jsonObj.getString("status");
                                if (sucs.equalsIgnoreCase("ok")) {


//                                    Intent i = new Intent(getApplicationContext(), Partner_profile.class);
//                                    startActivity(i);
//

//                                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                    params.put("speed", speed);
                    params.put("mode", mode);
                    params.put("brightness", brightness + "");
                    params.put("touch", touch);
                    params.put("callblock", callblock);
                    params.put("automessage", automessage);
                    params.put("message", message);
                    params.put("messagetype", messagetype);
                    params.put("bluetooth", bluet);
//                    params.put("message",message);


//                params.put("asid",sh.getString("assid",""));
                    return params;
                }
            };


            requestQueue.add(postRequest);

        }


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        brightness = i;
        BR.setText(brightness);
        Log.d("Brightnessssss", i + "");
//        Toast.makeText(getApplicationContext(),brightness+"aa" ,Toast.LENGTH_SHORT ).show();


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == SW3) {
            if (b == true) {
                touch = "On";
//                Toast.makeText(getApplicationContext(), touch, Toast.LENGTH_SHORT).show();

            } else
                touch = "Off";
//                Toast.makeText(getApplicationContext(), touch, Toast.LENGTH_SHORT).show();

        }
        if (compoundButton == SW4) {
            if (b == true) {
                callblock = "On";
//                Toast.makeText( getApplicationContext(),callblock , Toast.LENGTH_SHORT).show();


            } else
                callblock = "Off";
//                Toast.makeText(getApplicationContext(), callblock, Toast.LENGTH_SHORT).show();


        }
        if (compoundButton == TBL) {
            if (b == true) {
                bluet = "On";
//                Toast.makeText( getApplicationContext(),callblock , Toast.LENGTH_SHORT).show();


            } else
                bluet = "Off";
//                Toast.makeText(getApplicationContext(), callblock, Toast.LENGTH_SHORT).show();


        }


    }
}
