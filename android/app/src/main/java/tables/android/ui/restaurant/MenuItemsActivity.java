package tables.android.ui.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;

import tables.android.R;
import tables.android.adapters.MenuItemsAdapter;
import tables.android.base.BaseActivity;
import tables.android.models.RestaurantMenuItem;
import tables.android.ui.Constants;

public class MenuItemsActivity extends BaseActivity {

    private String mCategoryId;

    private RecyclerView mMenuItemsRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

        mCategoryId = getIntent().getStringExtra(Constants.MENU_CATEGORY_ID);
        //Todo: handle more gracefully
        if (mCategoryId == null)
            finish();

        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        mMenuItemsRecyclerView = (RecyclerView) findViewById(R.id.menu_items_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mMenuItemsRecyclerView.setLayoutManager(mLayoutManager);
        mMenuItemsRecyclerView.setHasFixedSize(true);
        final Context context = this;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("categoryID", mCategoryId);
        ParseCloud.callFunctionInBackground("getMenuItems", params, new FunctionCallback<ArrayList<ParseObject>>() {
            public void done(ArrayList<ParseObject> results, ParseException e) {
                if (e == null) {
                    RestaurantMenuItem[] items = new RestaurantMenuItem[results.size()];
                    for (int i = 0; i < results.size(); i++) {
                        items[i] = new RestaurantMenuItem(results.get(i));
                    }
                    MenuItemsAdapter adapter = new MenuItemsAdapter(items, context);
                    mMenuItemsRecyclerView.setAdapter(adapter);

                    mMenuItemsRecyclerView.setVisibility(View.VISIBLE);
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                }
            }
        });
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
