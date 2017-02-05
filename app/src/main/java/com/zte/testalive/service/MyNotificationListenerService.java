package com.zte.testalive.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by Torick on 16/11/14.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressLint("OverrideAbstract")
public class MyNotificationListenerService extends NotificationListenerService
{
    private String tag = getClass().getSimpleName();

    @Override
    public void onCreate()
    {
        Log.i(tag, "onCreate this=" + this);
        super.onCreate();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn)
    {
        // super.onNotificationPosted(sbn);
        Log.i(tag, "onNotificationPosted sbn=" + sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn)
    {
        // super.onNotificationRemoved(sbn);
        Log.i(tag, "onNotificationRemoved sbn=" + sbn);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(tag, "onStartCommand intent=" + intent + ", flags=" + flags + ", startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        Log.i(tag, "onDestroy this=" + this);
        super.onDestroy();
    }
}
