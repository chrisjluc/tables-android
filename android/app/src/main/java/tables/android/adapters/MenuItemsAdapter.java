package tables.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tables.android.R;
import tables.android.models.RestaurantMenuItem;
import tables.android.ui.Constants;
import tables.android.ui.restaurant.MenuItemActivity;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private final static String TAG = "MenuItemsAdapter";
    private RestaurantMenuItem[] mItems;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    public MenuItemsAdapter(RestaurantMenuItem[] items, Context context) {
        mItems = items;
        mContext = context;
    }

    @Override
    public MenuItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_menu_item, parent, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RestaurantMenuItem item = mItems[position];

        TextView categoryNameTextView = (TextView) holder.mCardView.findViewById(R.id.menuItemNameTextView);
        if (item.getMenuItemName() != null)
            categoryNameTextView.setText(item.getMenuItemName());

        TextView menuItemPriceTextView = (TextView) holder.mCardView.findViewById(R.id.menuItemPrice);
        menuItemPriceTextView.setText(item.getBasePrice().toString());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuItemActivity.class);
                intent.putExtra(Constants.MENU_ITEM_ID, mItems[position].getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }
}
