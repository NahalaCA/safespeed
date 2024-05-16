package com.example.myapplication;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationService extends Service  implements  JsonResponse {

	SharedPreferences sh;
	String[] id, plname;
	private LocationManager locationManager;
	private Boolean locationChanged;

	private Handler handler = new Handler();
	public static Location curLocation;
	public static boolean isService = true;
	String ip = "", uid;

	String pc = "";

	public static String place = "", address = "", lati = "", logi = "";
	public  static  String [] latitude,longitude,plac;

	List<String> locname, loccount;
	LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
//			Toast.makeText(getApplicationContext(), "IN LOCATION SERVICE", Toast.LENGTH_LONG).show();
			if (curLocation == null) {
				curLocation = location;
				locationChanged = true;
			} else if (curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude()) {
				locationChanged = false;
				return;
			} else
				locationChanged = true;
			curLocation = location;

			if (locationChanged)
				locationManager.removeUpdates(locationListener);
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

			if (status == 0)// UnAvailable
			{
			} else if (status == 1)// Trying to Connect
			{
			} else if (status == 2) {// Available
			}
		}
	};

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();

		SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		uid = sh.getString("id", "");

		curLocation = getBestLocation();
//		Log.d("entelocation", "Current location is null",curLocation);
//		Toast.makeText(getApplicationContext(), "location service" + curLocation, Toast.LENGTH_LONG).show();
		if (curLocation == null) {
			System.out.println("starting problem.........3...");
			Toast.makeText(this, "GPS problem..........", Toast.LENGTH_SHORT).show();
		} else {
			// Log.d("ssssssssssss", String.valueOf("latitude2.........."+curLocation.getLatitude()));
		}
		isService = true;
	}

	final String TAG = "LocationService";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override

	public void onLowMemory() {
		super.onLowMemory();
	}


	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "Start services", Toast.LENGTH_SHORT).show();

		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

//		if (!provider.contains("gps")) {
		//if gps is disabled
		final Intent poke = new Intent();
		poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		poke.setData(Uri.parse("3"));
		sendBroadcast(poke);
//		}
		handler.post(GpsFinder);
	}

	@Override
	public void onDestroy() {
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

//		if (provider.contains("gps")) {
		//if gps is enabled
		final Intent poke = new Intent();
		poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
		poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		poke.setData(Uri.parse("3"));
		sendBroadcast(poke);
//		}

		handler.removeCallbacks(GpsFinder);
		handler = null;
		Toast.makeText(this, "Service Stopped..!!", Toast.LENGTH_SHORT).show();
		isService = false;
	}


	public Runnable GpsFinder = new Runnable() {


		public void run() {

			String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

//			if (!provider.contains("gps")) {
			//if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
//			}

//			TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
//			phoneid = telephonyManager.getDeviceId().toString();

			Location tempLoc = getBestLocation();

			if (tempLoc == curLocation){
//				Toast.makeText(getApplicationContext(),"placeeeeeeeee",Toast.LENGTH_LONG).show();
				Log.d("MyService", String.valueOf("latitude"+curLocation.getLatitude()));
			}

			if (tempLoc != null) {
				curLocation = tempLoc;
				 Log.d("MyService", String.valueOf("latitude"+curLocation.getLatitude()));

				lati = String.valueOf(curLocation.getLatitude());
				logi = String.valueOf(curLocation.getLongitude());
//					Toast.makeText(getApplicationContext(), "lattitude" + lati + "longitude" + logi, Toast.LENGTH_LONG).show();
				SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				String ip = sh.getString("ip", "");
				//startService(new Intent(getApplicationContext(),notification.class));
					updateloc();
				String loc="";

				Geocoder geoCoder = new Geocoder( getBaseContext(), Locale.getDefault());
				try
				{
					List<Address> addresses = geoCoder.getFromLocation(curLocation.getLatitude(), curLocation.getLongitude(), 1);
					if (addresses.size() > 0)
					{
						for (int index = 0;index < addresses.get(0).getMaxAddressLineIndex(); index++)
							address += addresses.get(0).getAddressLine(index) + " ";
						//Log.d("get loc...", address);

						place=addresses.get(0).getFeatureName().toString();
//						Toast.makeText(getApplicationContext(),place,Toast.LENGTH_LONG).show();
					}
					else
					{



					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}



			}
			handler.postDelayed(GpsFinder, 5000);// register again to start after 20 seconds...
		}
	};

	private Location getBestLocation() {
		Location gpslocation = null;
		Location networkLocation = null;
		if (locationManager == null) {
			locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		}
		try {
			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

//				Toast.makeText(getApplicationContext(),"starting pbm",Toast.LENGTH_LONG).show();
				//if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				//return TODO;
				//}
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);// here you can set the 2nd argument time interval also that after how much time it will get the gps location
				gpslocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				//  System.out.println("starting problem.......7.11....");

			}
			if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000, 0, locationListener);
				networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
		} catch (IllegalArgumentException e) {
			Log.e("error", e.toString());
		}
		if(gpslocation==null && networkLocation==null)
			return null;

		if(gpslocation!=null && networkLocation!=null){
			if(gpslocation.getTime() < networkLocation.getTime()){
				gpslocation = null;
				return networkLocation;
			}else{
				networkLocation = null;
				return gpslocation;
			}
		}
		if (gpslocation == null) {
			return networkLocation;
		}
		if (networkLocation == null) {
			return gpslocation;
		}
		return null;
	}
	private Location getCurrentLocation() {
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		if (locationManager != null) {
			if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
					&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

				Criteria criteria = new Criteria();
				criteria.setAccuracy(Criteria.ACCURACY_FINE);

				String provider = locationManager.getBestProvider(criteria, true);
				if (provider != null) {
					locationManager.requestLocationUpdates(provider, 1000, 1, new LocationListener() {
						@Override
						public void onLocationChanged(Location location) {
							// Handle location changes if needed
						}

						@Override
						public void onProviderDisabled(String provider) {
						}

						@Override
						public void onProviderEnabled(String provider) {
						}

						@Override
						public void onStatusChanged(String provider, int status, Bundle extras) {
						}
					});

					return locationManager.getLastKnownLocation(provider);
				}
			}
		}

		return null;
	}
	public void updateloc()
	{
//		JsonReq jr= new JsonReq();
//		jr.json_response=(JsonResponse)LocationService.this;
//		String q="/updatepasslocation?latti=" +lati+"&longi="+logi+"&logid="+Login.logid;
//		jr.execute(q);

		JsonReq JR = new JsonReq();
		JR.json_response = (JsonResponse) LocationService.this;
		String q="/notification?logid="+login.logid+"&lati="+LocationService.lati+"&longi="+LocationService.logi;
		q = q.replace(" ", "%20");
		JR.execute(q);


	}
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void response(JSONObject jo)
	{
		// TODO Auto-generated method stub
		try {
			String status = jo.getString("status");
			Log.d("method",status);

			if (status.equalsIgnoreCase("success")) {
//				String currentlocation= String.valueOf(getBestLocation());
//				String currenttime = getCurrentTime();
				String currentlocation = String.valueOf(getCurrentLocation());
//				Toast.makeText(getApplicationContext(), "Current Time : " + currentlocation, Toast.LENGTH_SHORT).show();
				Log.d("entelocation", currentlocation);

				Location currentLocation = getCurrentLocation();

				if (currentLocation != null) {
					double latitude = currentLocation.getLatitude();
					double longitude = currentLocation.getLongitude();
//					Toast.makeText(getApplicationContext(), "mylati : " + latitude, Toast.LENGTH_SHORT).show();
//					Toast.makeText(getApplicationContext(), "mylongi : " + longitude, Toast.LENGTH_SHORT).show();


					Log.d("entelocation", "Latitude: " + latitude + ", Longitude: " + longitude);
				} else {
					Log.d("entelocation", "Current location is null");
				}
				sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				String out = jo.getString("ss");
				SharedPreferences.Editor ed=sh.edit();
				ed.putString("out",out);
				ed.commit();


				startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));

			}






//					if (latitude[i].equalsIgnoreCase(currentlocation) || longitude[i].equalsIgnoreCase(currentlocation)){
//
//						startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
//
//					}
////					if (time_aftn[i].equalsIgnoreCase(currenttime)) {
////
////						startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
////
////					}  if (time_nyt[i].equalsIgnoreCase(currenttime)) {
////
////						startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
////
////					}
//
//				}
//
//			}


		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
		}
	}

}
