package tables.android.ui.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;

import tables.android.R;
import tables.android.adapters.MenuCategoriesAdapter;
import tables.android.base.BaseActivity;
import tables.android.models.MenuCategory;
import tables.android.ui.Constants;

public class MenuCategoriesActivity extends BaseActivity {

    private String mRestaurantId;

    private RecyclerView mMenuCategoriesRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categories);
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        mRestaurantId = getIntent().getStringExtra(Constants.RESTAURANT_ID);
        //Todo: handle more gracefully
        if (mRestaurantId == null)
            finish();

        mMenuCategoriesRecyclerView = (RecyclerView) findViewById(R.id.menu_categories_recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mMenuCategoriesRecyclerView.setLayoutManager(mLayoutManager);
        mMenuCategoriesRecyclerView.setHasFixedSize(true);
        final Context context = this;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("restaurantID", mRestaurantId);
        ParseCloud.callFunctionInBackground("getMenuCategories", params, new FunctionCallback<ArrayList<ParseObject>>() {
            public void done(ArrayList<ParseObject> results, ParseException e) {
                if (e == null) {
                    MenuCategory[] categories = new MenuCategory[results.size()];
                    for (int i = 0; i < results.size(); i++) {
                        categories[i] = new MenuCategory(results.get(i));
                    }
                    MenuCategoriesAdapter adapter = new MenuCategoriesAdapter(categories, context);
                    mMenuCategoriesRecyclerView.setAdapter(adapter);

                    mMenuCategoriesRecyclerView.setVisibility(View.VISIBLE);
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                }
            }
        });
    }
}
