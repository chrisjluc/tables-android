package tables.android.base;

import android.support.v4.app.FragmentActivity;

import tables.android.framework.VisibilityManager;

public class BaseFragmentActivity extends FragmentActivity {

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
