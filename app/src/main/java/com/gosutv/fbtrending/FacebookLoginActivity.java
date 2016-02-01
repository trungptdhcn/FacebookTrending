package com.gosutv.fbtrending;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.*;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gosutv.fbtrending.common.BaseActivity;

import java.util.Arrays;
import java.util.Collection;


public class FacebookLoginActivity extends Activity
{
    @Bind(R.id.facebook_login_btLogin)
    Button btLogin;

    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        updateUI();
                    }

                    @Override
                    public void onCancel()
                    {
                        updateUI();
                    }

                    @Override
                    public void onError(FacebookException exception)
                    {
                        updateUI();
                    }

                    private void showAlert()
                    {
                        new AlertDialog.Builder(FacebookLoginActivity.this)
                                .setTitle("Cancel")
                                .setMessage("Permisstion not granted")
                                .setPositiveButton("Ok", null)
                                .show();
                    }
                });
        setContentView(R.layout.facebook_login);
        ButterKnife.bind(this);
        profileTracker = new ProfileTracker()
        {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile)
            {
                updateUI();
            }
        };
    }

    @OnClick(R.id.facebook_login_btLogin)
    public void login()
    {
        Collection<String> permissions = Arrays.asList("public_profile"
                , "user_friends"
                , "email"
                , "user_likes"
                , "user_photos"
                , "user_posts"
                , "user_videos"
                , "pages_show_list"
                , "user_birthday");
        LoginManager.getInstance().logInWithReadPermissions(this, permissions);
    }

    public void updateUI()
    {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null)
        {
            btLogin.setText("Logout");
        }
        else
        {
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        AppEventsLogger.activateApp(this);
        updateUI();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        profileTracker.stopTracking();
    }
}
