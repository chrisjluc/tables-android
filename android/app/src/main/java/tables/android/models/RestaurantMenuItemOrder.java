package tables.android.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class RestaurantMenuItemOrder {

    private RestaurantMenuItem mMenuItem;
    private LinkedHashMap<String, ArrayList<CustomizationOption>> mSelectedCustomizations;

    public RestaurantMenuItemOrder(RestaurantMenuItem menuItem) {
        mMenuItem = menuItem;
        mSelectedCustomizations = new LinkedHashMap<>();
        for (String category : menuItem.getCustomizationCategoriesSet()) {
            mSelectedCustomizations.put(category, new ArrayList<>(Arrays.asList(menuItem.getCustomizationOptions(category).get(0))));
        }
    }

    public RestaurantMenuItem getMenuItem() {
        return mMenuItem;
    }

    public ArrayList<String> getCustomizationCategories() {
        return new ArrayList<>(mSelectedCustomizations.keySet());
    }

    public ArrayList<CustomizationOption> getCustomizationSelectedOptionsByIndex(int position) {
        return new ArrayList<>(mSelectedCustomizations.values()).get(position);
    }

    public double getPrice() {
        double price = mMenuItem.getBasePrice();
        for (ArrayList<CustomizationOption> options : mSelectedCustomizations.values()) {
            for (CustomizationOption option : options) {
                price += option.getPrice();
            }
        }
        return price;
    }

    public void setSelectedCustomizationOptions(String category, ArrayList<CustomizationOption> options){
        mSelectedCustomizations.put(category, options);
    }
}
