package tables.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.parse.ParseUser;

import tables.android.R;
import tables.android.base.BaseActivity;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

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
        if (resultCode != Activity.RESULT_OK) {
            System.out.println("Result not okay");
            return;
        }
        switch (requestCode) {
            case 1:
                finish();
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
