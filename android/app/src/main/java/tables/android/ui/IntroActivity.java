package tables.android.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;

import tables.android.R;
import tables.android.base.BaseActivity;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "IntroActivity";
    private static final int PARSE_REQUEST_CODE = 2;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_intro);
        if (getActionBar() != null)
            getActionBar().hide();
        mVideoView = (VideoView) findViewById(R.id.introVideoView);
        mVideoView.setMediaController(null);
        mVideoView.setVideoURI(Uri.parse("android.resource://tables.android/" + R.raw.intro));
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        TextView appNameTextView = (TextView) findViewById(R.id.appNameTextView);
        appNameTextView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bariol_Bold.otf"));
        Button connectFacebookButton = (Button) findViewById(R.id.connectFacebookButton);
        connectFacebookButton.setOnClickListener(this);
        Button signupEmailButton = (Button) findViewById(R.id.signupEmailButton);
        signupEmailButton.setOnClickListener(this);
        TextView signinTextView = (TextView) findViewById(R.id.signinTextView);
        signinTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connectFacebookButton:
                final ProgressDialog dialog = ProgressDialog.show(
                        IntroActivity.this, "", "Connecting to Facebook...", true);
                ParseFacebookUtils.logIn(Arrays.asList("email", "public_profile"),
                        this, PARSE_REQUEST_CODE, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException err) {
                                dialog.dismiss();
                                if (user == null) {
                                    Toast.makeText(getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, err.toString());

                                } else {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                break;
            case R.id.signupEmailButton:
                Intent signup = new Intent(getApplicationContext(), SignupEmailActivity.class);
                startActivityForResult(signup, 1);
                break;
            case R.id.signinTextView:
                Intent signin = new Intent(getApplicationContext(), SigninActivity.class);
                startActivityForResult(signin, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                finish();
                break;
            case PARSE_REQUEST_CODE:
                ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }
}
