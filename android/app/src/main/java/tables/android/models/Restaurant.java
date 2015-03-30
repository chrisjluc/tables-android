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
    private String phoneNumber;
    private ParseGeoPoint geoPoint;
    private RestaurantHours hours;
    private double kilometersDistance;

    public Restaurant(ParseObject parseObject, ParseGeoPoint currentGeoPoint) {
        id = parseObject.getObjectId();
        restaurantName = parseObject.getString("name");
        restaurantType = parseObject.getString("restaurantType");
        address = parseObject.getString("streetAddress");
        phoneNumber = parseObject.getString("phoneNumber");
        geoPoint = parseObject.getParseGeoPoint("coordinates");
        mainImage = parseObject.getParseFile("mainImage");
        hours = new RestaurantHours(parseObject.get("hours"));
        kilometersDistance = currentGeoPoint.distanceInKilometersTo(geoPoint);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RestaurantHours getHours() {
        return hours;
    }

    public String getKilometersDistanceString() {
        if (kilometersDistance < 0.1)
            return "< 0.1 KM";
        return String.format("%.1f KM", kilometersDistance);
    }
}
