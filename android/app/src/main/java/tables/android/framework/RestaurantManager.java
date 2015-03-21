package tables.android.framework;

import java.util.HashMap;

import tables.android.models.Restaurant;

public class RestaurantManager {
    private HashMap<String, Restaurant> mRestaurantHashmap;
    private static RestaurantManager mRestaurantManager;

    public static RestaurantManager getInstance() {
        if (mRestaurantManager == null)
            mRestaurantManager = new RestaurantManager();
        return mRestaurantManager;
    }

    public RestaurantManager() {
        mRestaurantHashmap = new HashMap<String, Restaurant>();
    }

    public void addRestaurant(Restaurant r) {
        if (!mRestaurantHashmap.containsKey(r.getId()))
            mRestaurantHashmap.put(r.getId(), r);
    }

    public Restaurant getRestaurant(String id) {
        if (mRestaurantHashmap.containsKey(id))
            return mRestaurantHashmap.get(id);
        return null;
    }
}
