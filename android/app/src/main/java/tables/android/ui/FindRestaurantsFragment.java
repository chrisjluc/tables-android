package tables.android.ui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamanland.fab.FloatingActionButton;

import tables.android.R;
import tables.android.models.Restaurant;
import tables.android.ui.adapters.RestaurantsAdapter;

public class FindRestaurantsFragment extends Fragment {

    private static final String TAG = "FindRestaurantsFragment";

    private Activity mActivity;
    private RecyclerView mRestaurantRecyclerView;
    private FloatingActionButton mMapButton;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find_recycler_view, container, false);
        mActivity = getActivity();

        mMapButton = (FloatingActionButton) rootView.findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openMap();
            }
        });

        mRestaurantRecyclerView = (RecyclerView) rootView.findViewById(R.id.restaurant_recycler_view);
        mLayoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        mRestaurantRecyclerView.setLayoutManager(mLayoutManager);
        mRestaurantRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        Restaurant[] rest = new Restaurant[7];
        rest[0] = new Restaurant("test1", null, null, null, null);
        rest[1] = new Restaurant("test2", null, null, null, null);
        rest[2] = new Restaurant("test3", null, null, null, null);
        rest[3] = new Restaurant("test4", null, null, null, null);
        rest[4] = new Restaurant("test5", null, null, null, null);
        rest[5] = new Restaurant("test6", null, null, null, null);
        rest[6] = new Restaurant("test7", null, null, null, null);


        mAdapter = new RestaurantsAdapter(rest, mActivity);
        mRestaurantRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void openMap();
    }
}
