package tables.android.models;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class RestaurantMenuItem {
    private String id;
    private String menuItemName;
    private String menuItemDescription;
    private double basePrice;
    private ParseFile image;

    public RestaurantMenuItem(ParseObject po){
        id = po.getObjectId();
        menuItemName = po.getString("itemName");
        menuItemDescription = po.getString("itemDescription");
        basePrice = po.getDouble("itemPrice");
        image = po.getParseFile("itemImage");
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
}
