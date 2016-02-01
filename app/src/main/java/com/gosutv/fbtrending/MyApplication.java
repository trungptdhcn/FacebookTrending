package com.gosutv.fbtrending;

import android.app.Application;
import com.facebook.FacebookSdk;

/**
 * Created by Trung on 11/24/2015.
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        FacebookSdk.sdkInitialize(this.getApplicationContext());
    }
}
