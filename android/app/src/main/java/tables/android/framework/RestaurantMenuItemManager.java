package tables.android.framework;

import java.util.HashMap;

import tables.android.models.Restaurant;
import tables.android.models.RestaurantMenuItem;

public class RestaurantMenuItemManager {
    private HashMap<String, RestaurantMenuItem> mItemHashmap;
    private static RestaurantMenuItemManager mItemManager;

    public static RestaurantMenuItemManager getInstance() {
        if (mItemManager == null)
            mItemManager = new RestaurantMenuItemManager();
        return mItemManager;
    }

    public RestaurantMenuItemManager() {
        mItemHashmap = new HashMap<String, RestaurantMenuItem>();
    }

    public void addRestaurantMenuItem(RestaurantMenuItem r) {
        if (!mItemHashmap.containsKey(r.getId()))
            mItemHashmap.put(r.getId(), r);
    }

    public RestaurantMenuItem getRestaurantMenuItem(String id) {
        if (mItemHashmap.containsKey(id))
            return mItemHashmap.get(id);
        return null;
    }
}
