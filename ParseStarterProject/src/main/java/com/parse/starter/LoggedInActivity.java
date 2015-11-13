package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.parse.ParseUser;

/**
 * Created by floyd on 11/5/15.
 */
public class LoggedInActivity extends AppCompatActivity {
    TextView mWelcomeUser;
    String mUserName;
    private static final int NUM_PAGES = 3;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_activity);
        ParseUser currentUser = ParseUser.getCurrentUser();
        mUserName = currentUser.getUsername();
        mWelcomeUser = (TextView) findViewById(R.id.welcome_user);
        mWelcomeUser.setText(mUserName);

        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


    }


    public void signout(View v) {
        ParseUser.logOut();
        Intent intent = new Intent(LoggedInActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return MapFragment.newInstance();
                case 1: return UserFragment.newInstance();
                default: return DestinationFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
