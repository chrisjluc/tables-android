package tables.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import tables.android.R;
import tables.android.adapters.RestaurantsAdapter;
import tables.android.base.BaseActivity;
import tables.android.models.Restaurant;

public class FindRestaurantsActivity extends BaseActivity {

    private static final String TAG = "FindRestaurantsActivity";

    private RecyclerView mRestaurantRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mRestaurantRecyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRestaurantRecyclerView.setLayoutManager(mLayoutManager);
        mRestaurantRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        Restaurant[] rest = new Restaurant[11];
        String link1 = "http://waterfrontsf.com/waterfrontsf.com/userimages/HomePage5_lrg_78140.jpg";
        String link = "https://d13yacurqjgara.cloudfront.net/users/434375/screenshots/1887010/city_logo.jpg";
        rest[0] = new Restaurant("test1", null, null, link, null, null, null);
        rest[1] = new Restaurant("test2", null, null, link1, null, null, null);
        rest[2] = new Restaurant("test3", null, null, link, null, null, null);
        rest[3] = new Restaurant("test4", null, null, link1, null, null, null);
        rest[4] = new Restaurant("test5", null, null, link, null, null, null);
        rest[5] = new Restaurant("test6", null, null, link1, null, null, null);
        rest[6] = new Restaurant("test7", null, null, link, null, null, null);
        rest[7] = new Restaurant("test8", null, null, link1, null, null, null);
        rest[8] = new Restaurant("test9", null, null, link, null, null, null);
        rest[9] = new Restaurant("test10", null, null, link1, null, null, null);
        rest[10] = new Restaurant("test11", null, null, link, null, null, null);


        mAdapter = new RestaurantsAdapter(rest, this);
        mRestaurantRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_find, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mapButton:
                Intent intent = new Intent(this, FindRestaurantsMapActivity.class);
                startActivity(intent);

//            case R.id.action_log_out:
//                ParseUser.logOut();
//                Intent i = new Intent(getApplicationContext(), IntroActivity.class);
//                startActivity(i);
//                finish();
//                finishActivity(2);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
