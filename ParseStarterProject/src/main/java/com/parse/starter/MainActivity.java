/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

import com.parse.ParseException;


public class MainActivity extends ActionBarActivity {
    String mUserName;
    String mPassword;
    EditText mUsernameEditText;
    EditText mPasswordEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


      mUsernameEditText = (EditText) findViewById(R.id.editText);
      mPasswordEditText = (EditText) findViewById(R.id.editText2);



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
    ParseObject testObject = new ParseObject("TestObject");
    testObject.put("foo", "bar");
    testObject.saveInBackground();



  }
    public void signup (View v) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void resetEmail (View v) {
        Intent intent = new Intent(MainActivity.this, ResetPassword.class);
        startActivity(intent);
    }

    public void login(View v) {
        mUserName = mUsernameEditText.getText().toString().trim();
        mPassword = mPasswordEditText.getText().toString().trim();
        ParseUser.logInInBackground(mUserName, mPassword, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(getApplicationContext(), mUserName + " sign in successful", Toast.LENGTH_LONG).show();
                    // Hooray! The user is logged in.
                    Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), mUserName + " sign in unsuccessful", Toast.LENGTH_LONG).show();
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });

    }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
