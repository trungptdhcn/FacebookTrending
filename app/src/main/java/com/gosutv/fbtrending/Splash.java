package com.gosutv.fbtrending;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.AccessToken;
import com.facebook.Profile;


public class Splash extends Activity
{
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
                Profile profile = Profile.getCurrentProfile();
                if (enableButtons && profile != null)
                {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(Splash.this, FacebookLoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
