package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class SocialDistanceAlert extends Service {

	private Handler handler;
	private int NOTIFICATION_ID = 234;
	private NotificationManager notificationManager;
	private SharedPreferences sh;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		super.onCreate();

		try {
			if (Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		handler = new Handler();
		showNotification();  // Trigger notification once when service starts
		stopSelf();  // Stop the service after triggering notification
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// No need to remove callbacks as the service is stopping
	}

	private void showNotification() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			createNotificationChannel();
		}

		Uri soundUri = getCustomSoundUri();

		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "my_channel_01")
				.setSmallIcon(R.drawable.mainlogo)
				.setContentTitle("Reminder Alert")
				.setContentText(sh.getString("out",""))
				.setSound(soundUri);

		notificationManager.notify(NOTIFICATION_ID, builder.build());
	}




	private void createNotificationChannel() {
		String CHANNEL_ID = "my_channel_01";
		CharSequence name = "my_channel";
		String description = "This is my channel";
		int importance = NotificationManager.IMPORTANCE_HIGH;
		NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
		mChannel.setDescription(description);
		mChannel.enableLights(true);
		mChannel.setLightColor(Color.RED);
		mChannel.enableVibration(true);
		mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
		mChannel.setShowBadge(false);

		notificationManager.createNotificationChannel(mChannel);
	}

	private Uri getCustomSoundUri() {
		int soundResourceId = R.raw.beep;
		String soundResourcePath = "android.resource://" + getPackageName() + "/" + soundResourceId;
		Uri soundUri = Uri.parse(soundResourcePath);
		Log.d("CustomSoundUri", "URI: " + soundUri.toString());
		return soundUri;

	}
}
