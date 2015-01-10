package tables.android.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import tables.android.R;
import tables.android.base.BaseActivity;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
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
        switch (v.getId()){
            case R.id.connectFacebookButton:
                break;
            case R.id.signupEmailButton:
                Intent signup = new Intent(getApplicationContext(), SignupEmailActivity.class);
                startActivity(signup);
                break;
            case R.id.signinTextView:
                Intent signin = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(signin);
                break;
        }
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
