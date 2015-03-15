package tables.android.ui.restaurant;

import android.os.Bundle;
import android.view.MenuItem;

import tables.android.R;
import tables.android.base.BaseActivity;

public class RestaurantMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);
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
