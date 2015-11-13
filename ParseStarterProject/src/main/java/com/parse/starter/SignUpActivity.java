package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import android.widget.Toast;
import com.parse.SignUpCallback;
import com.parse.ParseException;

import com.parse.ParseUser;

/**
 * Created by floyd on 11/5/15.
 */
public class SignUpActivity extends AppCompatActivity{

    EditText mUsernameEditText;
    EditText mPasswordEditText;
    EditText mPasswordAgainEditText;
    EditText mEmailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mPasswordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        Button mActionButton = (Button) findViewById(R.id.action_button);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                signup();
            }
        });
    }

    private void signup() {
        String mUserName = mUsernameEditText.getText().toString().trim();
        String mPassword = mPasswordEditText.getText().toString().trim();
        String passwordAgain = mPasswordAgainEditText.getText().toString().trim();
        String mEmail = mEmailEditText.getText().toString().trim();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (mUserName.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }
        if (mPassword.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        if (!mPassword.equals(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
        }
        validationErrorMessage.append(getString(R.string.error_end));
        if (validationError) {
            Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        ParseUser user = new ParseUser();
        user.setUsername(mUserName);
        user.setEmail(mEmail);
        user.setPassword(mPassword);
        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                // Handle the response
                if (e != null) {
                    // Show the error message
                    Toast.makeText(SignUpActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
