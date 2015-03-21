package tables.android.ui.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseImageView;

import tables.android.R;
import tables.android.base.BaseActivity;
import tables.android.framework.RestaurantManager;
import tables.android.models.Restaurant;
import tables.android.ui.Constants;
import tables.android.ui.ProfileActivity;

public class RestaurantActivity extends BaseActivity implements View.OnClickListener {

    private boolean mIsCheckedIn;
    private Restaurant mRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ImageLoader imageLoader = ImageLoader.getInstance();

        mIsCheckedIn = getIntent().getBooleanExtra(Constants.CHECKED_IN, false);
        String restaurantId = getIntent().getStringExtra(Constants.RESTAURANT_ID);
        if (restaurantId == null)
            finish();
        mRestaurant = RestaurantManager.getInstance().getRestaurant(restaurantId);
        if (mRestaurant == null) {
            //TODO: Handle this better
            finish();
        }

        if (!mIsCheckedIn) {
            findViewById(R.id.restaurantActivityCheckInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.restaurantActivityCheckInButton).setOnClickListener(this);
        }

        findViewById(R.id.menuItem).setOnClickListener(this);
        findViewById(R.id.specialsItem).setOnClickListener(this);
        findViewById(R.id.contactItem).setOnClickListener(this);
        findViewById(R.id.hoursItem).setOnClickListener(this);

        if (getActionBar() != null && mRestaurant.getRestaurantName() != null) {
            getActionBar().setTitle(mRestaurant.getRestaurantName());
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ParseImageView mainImage = (ParseImageView) findViewById(R.id.mainImageView);
        if (mRestaurant.getMainImage() != null) {
            imageLoader.displayImage(mRestaurant.getMainImage().getUrl(), mainImage);
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
