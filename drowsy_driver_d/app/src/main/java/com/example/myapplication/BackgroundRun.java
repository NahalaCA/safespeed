package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundRun extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Perform your foreground tasks here

        // Return START_STICKY to indicate that the service should be restarted if it's killed by the system.
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                Toast.makeText(BackgroundRun.this, "Hello", Toast.LENGTH_SHORT).show();
//            }
//        }, 5000);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
