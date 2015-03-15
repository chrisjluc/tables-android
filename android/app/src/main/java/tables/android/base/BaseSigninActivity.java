package tables.android.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;

import tables.android.ui.FindRestaurantsActivity;
import tables.android.ui.intro.consts;

public class BaseSigninActivity extends BaseActivity {
    private final String TAG = "BaseSigninActivity";

    protected void connectToFacebook() {
        final ProgressDialog dialog = ProgressDialog.show(
                BaseSigninActivity.this, "", "Connecting to Facebook...", true);
        ParseFacebookUtils.logIn(Arrays.asList("email", "public_profile"),
                this, consts.PARSE_REQUEST_CODE, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        dialog.dismiss();
                        if (user == null) {
                            Toast.makeText(getApplicationContext(), "Something went wrong, please try again later.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, err.toString());

                        } else {
                            if (user.isNew())
                                getFacebookInfo();
                            Intent i = new Intent(getApplicationContext(), FindRestaurantsActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
    }

    protected void getFacebookInfo() {
        new Request(
                ParseFacebookUtils.getSession(),
                "/me",
                null,
                HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        GraphObject obj = response.getGraphObject();
                        ParseUser user = ParseUser.getCurrentUser();
                        String email = obj.getProperty("email").toString();
                        user.setEmail(obj.getProperty("email").toString());
                        user.put("FirstName", obj.getProperty("first_name").toString());
                        user.put("LastName", obj.getProperty("last_name").toString());
                        user.put("profile", response.getGraphObject().asMap());
                        user.saveEventually();
                    }
                }
        ).executeAsync();

//        Bundle params = new Bundle();
//        params.putBoolean("redirect", false);
//        params.putInt("height", 200);
//        params.putInt("width", 200);
//        params.putString("type", "normal");
//        new Request(
//                ParseFacebookUtils.getSession(),
//                "/me/picture",
//                params,
//                HttpMethod.GET,
//                new Request.Callback() {
//                    public void onCompleted(Response response) {
//                        GraphObject obj = response.getGraphObject();
//                        ParseUser user = ParseUser.getCurrentUser();
//                        String imageUrl = obj.getProperty("url").toString();
//                    }
//                }
//        ).executeAsync();
    }
}
