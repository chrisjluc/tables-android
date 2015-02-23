package tables.android.ui.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import tables.android.R;
import tables.android.base.BaseActivity;
import tables.android.framework.BitmapManager;
import tables.android.models.Restaurant;
import tables.android.ui.Constants;

public class RestaurantActivity extends BaseActivity implements View.OnClickListener {

    private boolean mIsCheckedIn;
    private Restaurant restaurant;
    private BitmapManager mBitmapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mBitmapManager = BitmapManager.getInstance();

        mIsCheckedIn = getIntent().getBooleanExtra(Constants.CHECKED_IN, false);
        long restaurantId = getIntent().getLongExtra(Constants.RESTAURANT_ID, -1L);
        if (restaurantId == -1)
            finish();

        if (!mIsCheckedIn) {
            findViewById(R.id.restaurantActivityCheckInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.restaurantActivityCheckInButton).setOnClickListener(this);
        }

        findViewById(R.id.menuItem).setOnClickListener(this);
        findViewById(R.id.specialsItem).setOnClickListener(this);
        findViewById(R.id.contactItem).setOnClickListener(this);
        findViewById(R.id.hoursItem).setOnClickListener(this);

        // TODO: Get restaurant here

        if (getActionBar() != null)
            getActionBar().setTitle("Restaurant Name");

        ImageView restaurantCoverPhotoImageView = (ImageView) findViewById(R.id.restaurantCoverPhotoImageView);

        String link1 = "http://waterfrontsf.com/waterfrontsf.com/userimages/HomePage5_lrg_78140.jpg";
        if (link1 != null) {
            mBitmapManager.loadBitmap(link1, restaurantCoverPhotoImageView);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profileButton) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuItem:
                Intent intent = new Intent(getApplicationContext(), RestaurantMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.specialsItem:

                break;
            case R.id.contactItem:

                break;
            case R.id.hoursItem:

                break;
            case R.id.restaurantActivityCheckInButton:

                break;
            default:

        }

    }
}
