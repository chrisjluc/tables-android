package tables.android.ui.restaurant;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseImageView;

import tables.android.R;
import tables.android.adapters.CustomizationOptionsAdapter;
import tables.android.base.BaseActivity;
import tables.android.framework.RestaurantMenuItemManager;
import tables.android.models.RestaurantMenuItem;
import tables.android.models.RestaurantMenuItemOrder;
import tables.android.ui.Constants;

public class MenuItemActivity extends BaseActivity {

    private String mItemId;
    private RestaurantMenuItemOrder mOrder;

    private RecyclerView mCustomizationOptionsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        mItemId = getIntent().getStringExtra(Constants.MENU_ITEM_ID);
        //Todo: handle more gracefully
        if (mItemId == null)
            finish();

        RestaurantMenuItem menuItem = RestaurantMenuItemManager.getInstance().getRestaurantMenuItem(mItemId);

        //Todo: handle more gracefully
        if (menuItem == null)
            finish();

        mOrder = new RestaurantMenuItemOrder(menuItem);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            if (menuItem.getMenuItemName() != null)
                getActionBar().setTitle(menuItem.getMenuItemName());
        }

        if (menuItem.getImage() != null) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            ParseImageView mainImage = (ParseImageView) findViewById(R.id.menuItemImageView);
            imageLoader.displayImage(menuItem.getImage().getUrl(), mainImage);
        }

        if (menuItem.getMenuItemDescription() != null) {
            ((TextView) findViewById(R.id.menuItemDescriptionTextView)).setText(menuItem.getMenuItemDescription());
        }

        ((TextView) findViewById(R.id.orderPriceTextView)).setText(mOrder.getPrice().toString());
        mCustomizationOptionsRecyclerView = (RecyclerView) findViewById(R.id.customizationOptionsRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mCustomizationOptionsRecyclerView.setLayoutManager(layoutManager);
        mCustomizationOptionsRecyclerView.setHasFixedSize(true);
        CustomizationOptionsAdapter adapter = new CustomizationOptionsAdapter(mOrder, this);
        mCustomizationOptionsRecyclerView.setAdapter(adapter);
    }

    public void recalculatePrice() {
        ((TextView) findViewById(R.id.orderPriceTextView)).setText(mOrder.getPrice().toString());
    }
}
