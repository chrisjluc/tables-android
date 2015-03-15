package tables.android.ui;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;

import tables.android.R;
import tables.android.adapters.RestaurantsAdapter;
import tables.android.base.BaseActivity;
import tables.android.models.Restaurant;

public class FindRestaurantsActivity extends BaseActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

    private static final String TAG = "FindRestaurantsActivity";

    private RecyclerView mRestaurantRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private LocationClient mLocationClient;
    private Location mCurrentLocation;
    private FindRestaurantsActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mRestaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRestaurantRecyclerView.setLayoutManager(mLayoutManager);
        mRestaurantRecyclerView.setHasFixedSize(true);
        mActivity = this;
        mLocationClient = new LocationClient(this, this, this);


    }

    private void requestRestaurants() {
        mCurrentLocation = mLocationClient.getLastLocation();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("radius", Constants.RADIUS_KM);
        ParseGeoPoint point = new ParseGeoPoint(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        params.put("geoPoint", point);
        ParseCloud.callFunctionInBackground("findNearbyRestaurants", params, new FunctionCallback<ArrayList<ParseObject>>() {
            public void done(ArrayList<ParseObject> results, ParseException e) {
                if (e == null) {
                    Restaurant[] restaurants = new Restaurant[results.size()];
                    for (int i = 0; i < results.size(); i++) {
                        restaurants[i] = Restaurant.fromParseObject(results.get(i));
                    }
                    mAdapter = new RestaurantsAdapter(restaurants, mActivity);
                    mRestaurantRecyclerView.setAdapter(mAdapter);

                    mRestaurantRecyclerView.setVisibility(View.VISIBLE);
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_find, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.mapButton:
                intent = new Intent(this, FindRestaurantsMapActivity.class);
                break;
            case R.id.profileButton:
                intent = new Intent(this, ProfileActivity.class);
                break;

//            case R.id.action_log_out:
//                ParseUser.logOut();
//                Intent i = new Intent(getApplicationContext(), IntroActivity.class);
//                startActivity(i);
//                finish();
//                finishActivity(2);
//                return true;
        }
        if (intent != null) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        mLocationClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Hack so don't load the restaurants again
        if (mCurrentLocation == null) {
            requestRestaurants();
        }
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
