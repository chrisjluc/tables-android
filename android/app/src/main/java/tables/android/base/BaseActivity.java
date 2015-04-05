package tables.android.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import tables.android.framework.VisibilityManager;
import tables.android.utils.SdkUtils;

/**
 * A base class for activities. This is used to determine if our app is in the foreground.
 * If you make a new activity, make sure to extend from this base class, or else
 * implement the operations that are given here.
 * <p/>
 * Note: See [1] for reference.
 * [1]: http://stackoverflow.com/questions/18038399/how-to-check-if-activity-is-in-foreground-or-in-visible-background
 */
public class BaseActivity extends Activity {

    private boolean mIsFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        VisibilityManager.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        VisibilityManager.activityPaused();
    }

    @Override
    public void startActivity(Intent intent) {
        if (SdkUtils.supportsLollipop())
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        else
            super.startActivity(intent);
    }

    @Override
    public void finish() {
        if (SdkUtils.supportsLollipop() && !mIsFinished) {
            mIsFinished = true;
            finishAfterTransition();
        } else
            super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
