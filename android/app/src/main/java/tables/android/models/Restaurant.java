package tables.android.models;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

public class Restaurant {
    private String id;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantType;
    private ParseFile mainImage;
    private String address;
    private ParseGeoPoint geoPoint;

    public Restaurant(String id, String restaurantName, String restaurantDescription, String restaurantType, ParseFile mainImage, String address, ParseGeoPoint geoPoint) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantType = restaurantType;
        this.mainImage = mainImage;
        this.address = address;
        this.geoPoint = geoPoint;
    }
    public static Restaurant fromParseObject(ParseObject parseObject){
        String id = parseObject.getObjectId();
        String name = parseObject.getString("name");
        String restaurantType = parseObject.getString("restaurantType");
        String address = parseObject.getString("streetAddress");
        ParseGeoPoint geoPoint = parseObject.getParseGeoPoint("coordinates");
        ParseFile photo = parseObject.getParseFile("mainImage");
        return new Restaurant(id, name, null, restaurantType, photo, address, geoPoint);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public ParseGeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(ParseGeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public ParseFile getMainImage() {
        return mainImage;
    }

    public void setMainImage(ParseFile mainImage) {
        this.mainImage = mainImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
