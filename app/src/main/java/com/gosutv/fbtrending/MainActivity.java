package com.gosutv.fbtrending;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.gosutv.fbtrending.common.BaseActivity;
import com.gosutv.fbtrending.ui.*;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class MainActivity extends BaseActivity
{
    @Bind(R.id.activity_main_viewpager)
    ViewPager viewPager;
    @Bind(R.id.activity_main_tab)
    ViewGroup flTabContainer;
    @Bind(R.id.navdrawer_items_list)
    ViewGroup drawerItemListContainer;
    @Bind(R.id.profile_image)
    ImageView profilePictureView;
    @Bind(R.id.profile_name_text)
    TextView tvName;
    FragmentPagerItems pages;
    FragmentPagerItemAdapter adapter;
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    public void setDataToView(Bundle savedInstanceState)
    {
        setupNavDrawer();
        if (drawerToggle != null)
        {
            drawerToggle.syncState();
        }
        flTabContainer.addView(LayoutInflater.from(this).inflate(R.layout.tab_container, flTabContainer, false));
        SmartTabLayout tabLayout = (SmartTabLayout) findViewById(R.id.tab_indicator_menu_viewpagertab);
        final LayoutInflater inflater = LayoutInflater.from(this);
        final Resources res = tabLayout.getContext().getResources();
        viewPager.setOffscreenPageLimit(3);
        pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Popular", TopTrendingFragment.class));
        pages.add(FragmentPagerItem.of("Search", SearchFragment.class));
        pages.add(FragmentPagerItem.of("Follow", FollowFragment.class));
        pages.add(FragmentPagerItem.of("Favorites", FavoriteFragment.class));
        pages.add(FragmentPagerItem.of("About", AboutFragment.class));
        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);

        if (drawerItemListContainer == null)
        {
            return;
        }
        Profile profile = Profile.getCurrentProfile();
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
        if (enableButtons && profile != null)
        {
            Glide.with(this)
                    .load(profile.getProfilePictureUri(164, 164))
                    .centerCrop()
                    .placeholder(R.drawable.person_image_empty)
                    .crossFade()
                    .into(profilePictureView);
            tvName.setText(getString(R.string.hello_user, profile.getName()));
        }
        else
        {
            profilePictureView.setImageResource(R.drawable.person_image_empty);
            tvName.setText(null);
        }

    }


    @Override
    public int getLayout()
    {
        return R.layout.activity_main;
    }

    private void setupNavDrawer()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null || !isVisibleNav())
        {
            return;
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, getActionBarToolbar(), R.string.drawer_open, R.string.drawer_close)
        {

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return false;
        }
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
