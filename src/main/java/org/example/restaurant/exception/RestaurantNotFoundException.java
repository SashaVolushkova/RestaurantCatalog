package org.example.restaurant.exception;

public class RestaurantNotFoundException extends Exception {
    private final Long restaurantId;

    public RestaurantNotFoundException(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }
}
