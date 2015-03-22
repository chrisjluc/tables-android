package tables.android.models;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.HashMap;

public class Restaurant {
    private String id;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantType;
    private ParseFile mainImage;
    private String address;
    private ParseGeoPoint geoPoint;
    private RestaurantHours hours;

    public Restaurant(ParseObject parseObject) {
        id = parseObject.getObjectId();
        restaurantName = parseObject.getString("name");
        restaurantType = parseObject.getString("restaurantType");
        address = parseObject.getString("streetAddress");
        geoPoint = parseObject.getParseGeoPoint("coordinates");
        mainImage = parseObject.getParseFile("mainImage");
        hours = new RestaurantHours(parseObject.get("hours"));
    }

    public String getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public ParseGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public ParseFile getMainImage() {
        return mainImage;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantHours getHours() {
        return hours;
    }
}
