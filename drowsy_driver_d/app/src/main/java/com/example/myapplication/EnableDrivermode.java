package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;


import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EnableDrivermode extends Activity implements JsonResponse {

	ToggleButton toggleButton1;
	String val;
	SharedPreferences sh;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	String[] no;
	private TextToSpeech textToSpeech;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enable_drivermode);
		 //////////////////////////////////////////////////////////////////
	     // ShakeDetector initialization
	     		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	     		mAccelerometer = mSensorManager
	     				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	     		mShakeDetector = new ShakeDetector();
	     		mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
					
					public void onShake(int count) {
						// TODO Auto-generated method stub
						
						if(count>2)
						{
							if(sh.getString("drivemode", "").equalsIgnoreCase("ON"))
							{
//								SmsManager sms = SmsManager.getDefault();
//								sms.sendTextMessage("9287941746", null, "An accident Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
//								sms.sendTextMessage("7907410562", null, "An accident Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
//								sms.sendTextMessage("8078750709", null, "An accident Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
//					            sms.sendTextMessage("8590552204", null, "An accident Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
//					            Toast.makeText(getApplicationContext(), "Accident Detected and  Message is sent", Toast.LENGTH_LONG).show();
//
//								try {
//				      				SQLiteDatabase sqd=openOrCreateDatabase("drowsy", SQLiteDatabase.CREATE_IF_NECESSARY,null);
//				      				sqd.setVersion(1);
//				      				sqd.setLocale(Locale.getDefault());
//				      				String sql="create table if not exists accident(aid integer PRIMARY KEY AUTOINCREMENT,user_id text, lati text,longi text,date text,status text)";
//				      				sqd.execSQL(sql);
//				      				ContentValues cv=new ContentValues();
//				      				cv.put("user_id",sh.getString("logid", ""));
//				      				cv.put("lati",LocationService.lati);
//				      				cv.put("longi",LocationService.logi);
//				      				cv.put("status","pending");
//
//				      				Calendar c = Calendar.getInstance();
//				      				SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//				      				String formattedDate = df.format(c.getTime());
//
//				      				cv.put("date", formattedDate);
//				      				sqd.insert("accident", null,cv);
//
//				      				cv.clear();
//
////				      				Toast.makeText(getApplicationContext(),"Uploaded", Toast.LENGTH_LONG).show();
//				      				sqd.close();
//
//				      				Intent a= new Intent(getApplicationContext(),home.class);
//				      				startActivity(a);
//
//
//				      				}catch (Exception e) {
//				      					Toast.makeText(getApplicationContext(),"Exception11111 : " + e, Toast.LENGTH_LONG).show();
//
//				      				}
								Toast.makeText(getApplicationContext(), "Accident Detected"+count, Toast.LENGTH_LONG).show();
								JsonReq jr= new JsonReq();
								jr.json_response=(JsonResponse) EnableDrivermode.this;
								String q="/getnumber?lid="+login.logid+"&lati=" + LocationService.lati + "&longi=" + LocationService.logi;


								q.replace("", "%20");
								jr.execute(q);
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Driving mode OFF", Toast.LENGTH_LONG).show();
								
							}
						}
					}
				});
		
		  toggleButton1=(ToggleButton)findViewById(R.id.toggleButton1);  
		  
		  sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		  if(sh.getString("drivemode", "").equalsIgnoreCase("ON"))
		  {
			  toggleButton1.setChecked(true);
		  }
		  else
		  {
			  toggleButton1.setChecked(false);
		  }
		  toggleButton1.setOnClickListener(new View.OnClickListener()
		  {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				val=toggleButton1.getText().toString();
				Toast.makeText(getApplicationContext(),"Driving mode "+ val,Toast.LENGTH_LONG).show();  
				  
				SharedPreferences.Editor ed = sh.edit();
                ed.putString("drivemode", val);
                ed.commit();
			}
		});
	}

	
	@Override
	public void onResume() {
		super.onResume();
		// Add the following line to register the Session Manager Listener onResume
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
	}
 
	@Override
	public void onPause() {
		// Add the following line to unregister the Sensor Manager onPause
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}


	@Override
	public void response(JSONObject jo) {
		try {
			String status = jo.getString("status");
			Log.d("result", status);
			//   Toast.makeText(getApplicationContext(), status,Toast.LENGTH_LONG).show();
			if (status.equalsIgnoreCase("success")) {
				String number = jo.getString("contact_no");

				JSONArray ja1=(JSONArray)jo.getJSONArray("data");
				no=new String[ja1.length()];


				for(int i = 0;i<ja1.length();i++)
				{

					no[i]=ja1.getJSONObject(i).getString("contact_no");

					SmsManager sms = SmsManager.getDefault();
					sms.sendTextMessage(no[i], null, "An Emergency Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
//                        sms.sendTextMessage("8594081643", null, "An accident Occured at the location, http://www.google.com/maps?q=" + LocationService.lati + "," + LocationService.logi, null, null);
					Toast.makeText(getApplicationContext(), "Emergency Detected and  Message is sent", Toast.LENGTH_LONG).show();



//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="firstname : "+firstname[i]+" _ "+lastname[i]+"\nphone : "+phone[i]+"\nplace :" +place[i]+"\ndetails :"+details[i]+ "\namount :"+amount[i] + "\ndate :" +date[i]+"\nstatus :" +statuss[i];


				}

//				SharedPreferences.Editor ed=sh.edit();
//				ed.putString("number", number);
//
//				ed.commit();



				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + number));
				startActivity(callIntent);
//                    startService(new Intent(getApplicationContext(),CameraService.class));

				//startService(new Intent(getApplicationContext(), LocationService.class));


			} else {
				Toast.makeText(getApplicationContext(), "No number", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
