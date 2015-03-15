package tables.android.models;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

public class Restaurant {
    private String id;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantType;
    private String restaurantCoverPhotoLink;
    private String address;
    private ParseGeoPoint geoPoint;

    public Restaurant(String id, String restaurantName, String restaurantDescription, String restaurantType, String restaurantCoverPhotoLink, String address, ParseGeoPoint geoPoint) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantType = restaurantType;
        this.restaurantCoverPhotoLink = restaurantCoverPhotoLink;
        this.address = address;
        this.geoPoint = geoPoint;
    }
    public static Restaurant fromParseObject(ParseObject parseObject){
        String id = parseObject.getObjectId();
        String name = parseObject.getString("name");
        String restaurantType = parseObject.getString("restaurantType");
        String address = parseObject.getString("streetAddress");
        ParseGeoPoint geoPoint = parseObject.getParseGeoPoint("coordinates");

        String link = "http://waterfrontsf.com/waterfrontsf.com/userimages/HomePage5_lrg_78140.jpg";

        return new Restaurant(id, name, null, restaurantType, link, address, geoPoint);

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

    public String getRestaurantCoverPhotoLink() {
        return restaurantCoverPhotoLink;
    }

    public void setRestaurantCoverPhotoLink(String restaurantCoverPhotoLink) {
        this.restaurantCoverPhotoLink = restaurantCoverPhotoLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
