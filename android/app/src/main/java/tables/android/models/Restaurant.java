package tables.android.models;

import android.location.Location;

public class Restaurant {
    private long id;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantType;
    private String restaurantCoverPhotoLink;
    private String address;
    private Location location;
    private RestaurantMenu restaurantMenu;

    public Restaurant(String restaurantName, String restaurantDescription, String restaurantType, String restaurantCoverPhotoLink, String address, Location location, RestaurantMenu restaurantMenu) {
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantType = restaurantType;
        this.restaurantCoverPhotoLink = restaurantCoverPhotoLink;
        this.address = address;
        this.location = location;
        this.restaurantMenu = restaurantMenu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public RestaurantMenu getRestaurantMenu() {
        return restaurantMenu;
    }

    public void setRestaurantMenu(RestaurantMenu restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
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
