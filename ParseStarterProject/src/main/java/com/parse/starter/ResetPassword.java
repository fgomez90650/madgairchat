package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by floyd on 11/5/15.
 */
public class ResetPassword extends AppCompatActivity {

    TextView mEmailTextView;
    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_activity);

        mEmailTextView = (TextView) findViewById(R.id.email_user);


    }

    public void resetPassword(View v) {
        mEmail = mEmailTextView.getText().toString();
        ParseUser.requestPasswordResetInBackground(mEmail, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), " email sent successful", Toast.LENGTH_LONG).show();

                    // An email was successfully sent with reset instructions.
                } else {
                    Toast.makeText(getApplicationContext(), " sign in not successful", Toast.LENGTH_LONG).show();

                    // Something went wrong. Look at the ParseException to see what's up.
                }
            }
        });
    }
}
