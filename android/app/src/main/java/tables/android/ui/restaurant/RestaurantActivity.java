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
import tables.android.ui.Constants;
import tables.android.ui.ProfileActivity;

public class RestaurantActivity extends BaseActivity implements View.OnClickListener {

    private boolean mIsCheckedIn;
    private BitmapManager mBitmapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mBitmapManager = BitmapManager.getInstance();

        mIsCheckedIn = getIntent().getBooleanExtra(Constants.CHECKED_IN, false);
        String restaurantId = getIntent().getStringExtra(Constants.RESTAURANT_ID);
        String restaurantName = getIntent().getStringExtra(Constants.RESTAURANT_NAME);
        if (restaurantId == null)
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

        if (getActionBar() != null && restaurantName != null) {
            getActionBar().setTitle(restaurantName);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ImageView restaurantCoverPhotoImageView = (ImageView) findViewById(R.id.restaurantCoverPhotoImageView);

        String link = "http://waterfrontsf.com/waterfrontsf.com/userimages/HomePage5_lrg_78140.jpg";
        if (link != null) {
            mBitmapManager.loadBitmap(link, restaurantCoverPhotoImageView);
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
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.menuItem:
                intent = new Intent(getApplicationContext(), RestaurantMenuActivity.class);

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
        if (intent != null)
            startActivity(intent);

    }
}
