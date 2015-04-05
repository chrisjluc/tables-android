package tables.android.ui.restaurant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    private String mRestaurantId;
    private Restaurant mRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ImageLoader imageLoader = ImageLoader.getInstance();

        mIsCheckedIn = getIntent().getBooleanExtra(Constants.CHECKED_IN, false);
        mRestaurantId = getIntent().getStringExtra(Constants.RESTAURANT_ID);
        if (mRestaurantId == null)
            finish();
        mRestaurant = RestaurantManager.getInstance().getRestaurant(mRestaurantId);
        if (mRestaurant == null) {
            //TODO: Handle this better
            finish();
        }

        if (!mIsCheckedIn) {
            findViewById(R.id.restaurantActivityCheckInButton).setVisibility(View.VISIBLE);
            findViewById(R.id.restaurantActivityCheckInButton).setOnClickListener(this);
        }

        findViewById(R.id.menuItem).setOnClickListener(this);
        findViewById(R.id.phoneNumberItem).setOnClickListener(this);
        findViewById(R.id.hoursItem).setOnClickListener(this);

        if (getActionBar() != null) {
            if (mRestaurant.getRestaurantName() != null)
                getActionBar().setTitle(mRestaurant.getRestaurantName());
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (mRestaurant.getMainImage() != null) {
            ParseImageView mainImage = (ParseImageView) findViewById(R.id.mainImageView);
            imageLoader.displayImage(mRestaurant.getMainImage().getUrl(), mainImage);
        }

        if (mRestaurant.getHours() != null) {
            TextView hoursText = (TextView) findViewById(R.id.hoursTextView);
            hoursText.setText(mRestaurant.getHours().toString());
        }

        if (!mRestaurant.isOpen())
            findViewById(R.id.openTextView).setVisibility(View.GONE);

        if (mRestaurant.getPhoneNumber() != null) {
            TextView phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
            phoneNumberTextView.setText(mRestaurant.getPhoneNumber());
        }

        if (mRestaurant.getAddress() != null) {
            TextView addressTextView = (TextView) findViewById(R.id.addressTextView);
            addressTextView.setText(mRestaurant.getAddress());
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.menuItem:
                intent = new Intent(getApplicationContext(), MenuCategoriesActivity.class);
                intent.putExtra(Constants.RESTAURANT_ID, mRestaurantId);
                break;
            case R.id.hoursItem:

                break;
            case R.id.phoneNumberItem:
                String url = "tel:" + mRestaurant.getPhoneNumber();
                intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                break;
            case R.id.addressItem:

                break;
            case R.id.restaurantActivityCheckInButton:

                break;
            default:

        }
        if (intent != null)
            startActivity(intent);

    }
}
