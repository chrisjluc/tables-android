package tables.android.models;

import android.location.Location;

public class Restaurant {
    private String restaurantName;
    private String restaurantDescription;
    private String address;
    private Location location;
    private RestaurantMenu restaurantMenu;

    public Restaurant(String restaurantName, String restaurantDescription, Location location, RestaurantMenu restaurantMenu, String address) {
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.location = location;
        this.restaurantMenu = restaurantMenu;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
