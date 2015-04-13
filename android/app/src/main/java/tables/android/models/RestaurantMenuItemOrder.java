package tables.android.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class RestaurantMenuItemOrder {

    private RestaurantMenuItem mMenuItem;
    private LinkedHashMap<CustomizationCategory, ArrayList<CustomizationOption>> mSelectedCustomizations;

    public RestaurantMenuItemOrder(RestaurantMenuItem menuItem) {
        mMenuItem = menuItem;
        mSelectedCustomizations = new LinkedHashMap<>();
        for (CustomizationCategory category : menuItem.getCustomizationCategoriesSet()) {
            // Initialize with first item
            // mSelectedCustomizations.put(category, new ArrayList<>(Arrays.asList(menuItem.getCustomizationOptions(category).get(0))));
             mSelectedCustomizations.put(category, new ArrayList<CustomizationOption>());
        }
    }

    public RestaurantMenuItem getMenuItem() {
        return mMenuItem;
    }

    public ArrayList<CustomizationCategory> getCustomizationCategories() {
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

    public void setSelectedCustomizationOptions(CustomizationCategory category, ArrayList<CustomizationOption> options){
        mSelectedCustomizations.put(category, options);
    }
}
