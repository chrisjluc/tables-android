package tables.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import tables.android.R;
import tables.android.base.BaseActivity;
import tables.android.utils.EmailUtils;

public class SigninActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button connectFacebookButton = (Button) findViewById(R.id.signinButton);
        connectFacebookButton.setOnClickListener(this);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signinButton:
                String email = mEmailEditText.getText().toString();
                if (!EmailUtils.isValidEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Invalid email, please try again.", Toast.LENGTH_SHORT).show();
                    break;
                }
                String password = mPasswordEditText.getText().toString();
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password has to be a minimum of 6 characters.", Toast.LENGTH_SHORT).show();
                    break;
                }
                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
}
