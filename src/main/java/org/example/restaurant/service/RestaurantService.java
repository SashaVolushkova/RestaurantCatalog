package org.example.restaurant.service;

import org.example.restaurant.exception.RestaurantNotFoundException;

public interface RestaurantService {
    long createRestaurantByName(String name);
    String getRestaurantNameById(Long id) throws RestaurantNotFoundException;
}
