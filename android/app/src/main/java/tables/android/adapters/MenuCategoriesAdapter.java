package tables.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseImageView;

import tables.android.R;
import tables.android.models.MenuCategory;
import tables.android.ui.Constants;
import tables.android.ui.restaurant.MenuItemsActivity;

public class MenuCategoriesAdapter extends RecyclerView.Adapter<MenuCategoriesAdapter.ViewHolder> {
    private final static String TAG = "MenuCategoriesAdapter";
    private MenuCategory[] mCategories;
    private Context mContext;
    private ImageLoader mImageLoader;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    public MenuCategoriesAdapter(MenuCategory[] categories, Context activity) {
        mCategories = categories;
        mContext = activity;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public MenuCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_menu_category, parent, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MenuCategory category = mCategories[position];

        TextView categoryNameTextView = (TextView) holder.mCardView.findViewById(R.id.menuCategoryNameTextView);
        if (category.getCategoryName() != null)
            categoryNameTextView.setText(category.getCategoryName());

        final ParseImageView menuCategoryImageView = (ParseImageView) holder.mCardView.findViewById(R.id.menuCategoryImageView);
        if (category.getCategoryImage() != null) {
            mImageLoader.displayImage(category.getCategoryImage().getUrl(), menuCategoryImageView);
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuItemsActivity.class);
                intent.putExtra(Constants.MENU_CATEGORY_ID, mCategories[position].getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.length;
    }
}
