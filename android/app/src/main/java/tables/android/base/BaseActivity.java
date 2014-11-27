package tables.android.base;

import android.app.Activity;
import android.content.Context;

import tables.android.framework.VisibilityManager;

/**
 * A base class for activities. This is used to determine if our app is in the foreground.
 * If you make a new activity, make sure to extend from this base class, or else
 * implement the operations that are given here.
 *
 * Note: See [1] for reference.
 * [1]: http://stackoverflow.com/questions/18038399/how-to-check-if-activity-is-in-foreground-or-in-visible-background
 */
public class BaseActivity extends Activity {

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
}
