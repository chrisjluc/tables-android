package tables.android.models;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RestaurantMenuItem {
    private String id;
    private String menuItemName;
    private String menuItemDescription;
    private double basePrice;
    private ParseFile image;
    private HashMap<String, ArrayList<CustomizationOption>> mCustomizations;

    public RestaurantMenuItem(ParseObject po) {
        id = po.getObjectId();
        menuItemName = po.getString("itemName");
        menuItemDescription = po.getString("itemDescription");
        basePrice = po.getDouble("itemPrice");
        image = po.getParseFile("itemImage");
        mCustomizations = new HashMap<>();

        HashMap<String, HashMap<String, String>> rawCustomizations =
                (HashMap<String, HashMap<String, String>>) po.get("customizations");

        for (Map.Entry<String, HashMap<String, String>> customizationCategory : rawCustomizations.entrySet()) {
            if (mCustomizations.containsKey(customizationCategory.getKey()))
                continue;
            mCustomizations.put(customizationCategory.getKey(), new ArrayList<CustomizationOption>());

            for (Map.Entry<String, String> customizationOption : customizationCategory.getValue().entrySet()) {
                mCustomizations.get(customizationCategory.getKey()).add(
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

    public Set<String> getCustomizationCategoriesSet() {
        return mCustomizations.keySet();
    }

    public ArrayList<CustomizationOption> getCustomizationOptions(String key) {
        return mCustomizations.get(key);
    }

    /**
     * Includes price in the name in the following format
     * "name (price)"
     *
     * @param key
     * @return Array of option names and prices
     */
    public String[] getCustomizationOptionNames(String key) {
        ArrayList<String> optionNames = new ArrayList<>();
        for (CustomizationOption option : mCustomizations.get(key)) {
            optionNames.add(option.getName() + " (" + Double.toString(option.getPrice()) + ")");
        }
        return optionNames.toArray(new String[optionNames.size()]);
    }
}
