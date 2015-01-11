package tables.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

import tables.android.R;
import tables.android.base.BaseActivity;

public class FindRestaurantsActivity extends BaseActivity implements FindRestaurantsFragment.OnFragmentInteractionListener {

    private static final String TAG = "FindRestaurantsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new FindRestaurantsFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.find, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_log_out:
                ParseUser.logOut();
                Intent i = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(i);
                finish();
                finishActivity(2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openMap() {
        Intent intent = new Intent(this, FindRestaurantsMapActivity.class);
        startActivity(intent);
    }
}
