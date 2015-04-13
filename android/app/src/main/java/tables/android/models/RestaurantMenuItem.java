package tables.android.models;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import tables.android.models.Consts;

public class RestaurantMenuItem {
    private String id;
    private String menuItemName;
    private String menuItemDescription;
    private double basePrice;
    private ParseFile image;
    private HashMap<CustomizationCategory, ArrayList<CustomizationOption>> mCustomizations;

    public RestaurantMenuItem(ParseObject po) {
        id = po.getObjectId();
        menuItemName = po.getString("itemName");
        menuItemDescription = po.getString("itemDescription");
        basePrice = po.getDouble("itemPrice");
        image = po.getParseFile("itemImage");
        mCustomizations = new HashMap<>();

        HashMap<String, HashMap<String, Object>> rawCustomizations =
                (HashMap<String, HashMap<String, Object>>) po.get("customizations");

        for (Map.Entry<String, HashMap<String, Object>> customizationCategory : rawCustomizations.entrySet()) {
            if (mCustomizations.containsKey(customizationCategory.getKey()))
                continue;
            CustomizationCategory c = new CustomizationCategory(customizationCategory.getKey(), (boolean) customizationCategory.getValue().get(Consts.IS_MULTI_SELECT_KEY));
            mCustomizations.put(c, new ArrayList<CustomizationOption>());

            for (Map.Entry<String, String> customizationOption : ((HashMap<String, String>)customizationCategory.getValue().get(Consts.OPTIONS_KEY)).entrySet()) {
                mCustomizations.get(c).add(
                        new CustomizationOption(customizationOption.getKey(), Double.parseDouble(customizationOption.getValue())));
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public ParseFile getImage() {
        return image;
    }

    public Set<CustomizationCategory> getCustomizationCategoriesSet() {
        return mCustomizations.keySet();
    }

    public ArrayList<CustomizationOption> getCustomizationOptions(CustomizationCategory key) {
        return mCustomizations.get(key);
    }

    /**
     * Includes price in the name in the following format
     * "name (price)"
     *
     * @param key
     * @return Array of option names and prices
     */
    public String[] getCustomizationOptionNames(CustomizationCategory key) {
        ArrayList<String> optionNames = new ArrayList<>();
        for (CustomizationOption option : mCustomizations.get(key)) {
            optionNames.add(option.getName() + " (" + Double.toString(option.getPrice()) + ")");
        }
        return optionNames.toArray(new String[optionNames.size()]);
    }
}
