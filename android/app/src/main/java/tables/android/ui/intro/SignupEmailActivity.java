package tables.android.ui.intro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import tables.android.R;
import tables.android.base.BaseActivity;
import tables.android.ui.FindRestaurantsActivity;
import tables.android.utils.EmailUtils;

public class SignupEmailActivity extends BaseActivity implements View.OnClickListener {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_email);
        Button signupButton = (Button) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    @Override
    public void onClick(View v) {
        final ProgressDialog dialog = ProgressDialog.show(
                SignupEmailActivity.this, "", "Signing up...", true);
        switch (v.getId()) {
            case R.id.signupButton:
                String name = mNameEditText.getText().toString();
                if (name.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Name can't be empty", Toast.LENGTH_SHORT).show();
                    break;
                }
                String[] fullName = name.split("\\s+");
                if (name.length() < 3 || fullName.length != 2) {
                    Toast.makeText(getApplicationContext(), "First and last name are separated by a space", Toast.LENGTH_SHORT).show();
                    break;
                }
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
                ParseUser user = new ParseUser();
                user.setUsername(email);
                user.setPassword(password);
                user.setEmail(email);
                user.put("FirstName", fullName[0]);
                user.put("LastName", fullName[1]);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(getApplicationContext(), FindRestaurantsActivity.class);
                            startActivity(i);
                            Intent resultIntent = new Intent(getApplicationContext(), IntroActivity.class);
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        } else if (e.getCode() == ParseException.EMAIL_TAKEN) {
                            Toast.makeText(getApplicationContext(), "Email is taken, please try again", Toast.LENGTH_SHORT).show();
                        } else if (e.getCode() == ParseException.USERNAME_TAKEN) {
                            Toast.makeText(getApplicationContext(), "Username is taken, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
        dialog.dismiss();
    }
}
