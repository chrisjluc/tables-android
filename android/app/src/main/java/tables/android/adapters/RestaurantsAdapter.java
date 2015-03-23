package tables.android.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
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
import tables.android.models.Restaurant;
import tables.android.ui.Constants;
import tables.android.ui.restaurant.RestaurantActivity;
import tables.android.utils.SdkUtils;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {
    private final static String TAG = "RestaurantsAdapter";
    private Restaurant[] mRestaurants;
    private Activity mActivity;
    private ImageLoader mImageLoader;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    public RestaurantsAdapter(Restaurant[] restaurants, Activity activity) {
        mRestaurants = restaurants;
        mActivity = activity;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_restaurant, parent, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Restaurant r = mRestaurants[position];

        TextView restaurantNameTextView = (TextView) holder.mCardView.findViewById(R.id.restaurantNameTextView);
        if (r.getRestaurantName() != null)
            restaurantNameTextView.setText(r.getRestaurantName());

        final ParseImageView restaurantCoverPhotoImageView = (ParseImageView) holder.mCardView.findViewById(R.id.mainImageView);
        if (r.getMainImage() != null) {
            mImageLoader.displayImage(r.getMainImage().getUrl(), restaurantCoverPhotoImageView);
        }

        TextView restaurantTypeTextView = (TextView) holder.mCardView.findViewById(R.id.restaurantTypeTextView);
        if (r.getRestaurantType() != null) {
            restaurantTypeTextView.setText(r.getRestaurantType());
        }

        TextView distanceTextView = (TextView) holder.mCardView.findViewById(R.id.restaurantRelativeDistanceTextView);
        if (r.getGeoPoint() != null) {
            distanceTextView.setText(r.getKilometersDistanceString());
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RestaurantActivity.class);
                intent.putExtra(Constants.CHECKED_IN, false);
                intent.putExtra(Constants.RESTAURANT_ID, mRestaurants[position].getId());
                if (SdkUtils.supportsLollipop()) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            mActivity,
                            restaurantCoverPhotoImageView,
                            mActivity.getResources().getString(R.string.transition_restaurant_image));
                    mActivity.startActivity(intent, options.toBundle());
                } else {
                    mActivity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurants.length;
    }
}
