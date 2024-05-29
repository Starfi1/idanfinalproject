package com.example.idansubs;
import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannel extends Application {

    public static final String CHANNEL_1_ID = "Lens_app_channel_1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        android.app.NotificationChannel channel1 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel1 = new android.app.NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
        }

        NotificationManager manager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel1);
        }
    }
}
