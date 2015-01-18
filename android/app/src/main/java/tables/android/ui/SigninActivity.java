package tables.android.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;

import tables.android.R;
import tables.android.base.BaseActivity;
import tables.android.utils.EmailUtils;

public class SigninActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SigninActivity";
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button signinButton = (Button) findViewById(R.id.signinButton);
        signinButton.setOnClickListener(this);
        Button signinFacebookButton = (Button) findViewById(R.id.signinFacebookButton);
        signinFacebookButton.setOnClickListener(this);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signinFacebookButton:
                final ProgressDialog dialog = ProgressDialog.show(
                        SigninActivity.this, "", "Connecting to Facebook...", true);
                ParseFacebookUtils.logIn(Arrays.asList("email", "public_profile"),
                        this, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException err) {
                                dialog.dismiss();
                                if (user == null) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, err.toString());
                                } else {
                                    Intent i = new Intent(getApplicationContext(), FindRestaurantsActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                break;
            case R.id.signinButton:
                final ProgressDialog signinDialog = ProgressDialog.show(
                        SigninActivity.this, "", "Signing in...", true);
                String email = mEmailEditText.getText().toString();
                if (!EmailUtils.isValidEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Invalid email, please try again.", Toast.LENGTH_SHORT).show();
                    signinDialog.hide();
                    break;
                }
                String password = mPasswordEditText.getText().toString();
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password has to be a minimum of 6 characters.", Toast.LENGTH_SHORT).show();
                    signinDialog.hide();
                    break;
                }
                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        signinDialog.hide();
                        if (e == null) {
                            Intent i = new Intent(getApplicationContext(), FindRestaurantsActivity.class);
                            startActivity(i);
                            Intent resultIntent = new Intent(getApplicationContext(), IntroActivity.class);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "Invalid email / password combination.", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
}
