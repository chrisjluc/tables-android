package tables.android.ui.intro;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import tables.android.R;
import tables.android.base.BaseSigninActivity;
import tables.android.ui.FindRestaurantsActivity;

public class IntroActivity extends BaseSigninActivity implements View.OnClickListener {

    private static final String TAG = "IntroActivity";
    private static final int REQUEST_CODE = 1;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), FindRestaurantsActivity.class);
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
                connectToFacebook();
                break;
            case R.id.signupEmailButton:
                Intent signup = new Intent(getApplicationContext(), SignupEmailActivity.class);
                startActivityForResult(signup, REQUEST_CODE);
                break;
            case R.id.signinTextView:
                Intent signin = new Intent(getApplicationContext(), SigninActivity.class);
                startActivityForResult(signin, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                //RESULT_OK means user signed in, RESULT_CANCELLED user didn't sign in
                if (resultCode == Activity.RESULT_OK)
                    finish();
                break;
            case consts.PARSE_REQUEST_CODE:
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
