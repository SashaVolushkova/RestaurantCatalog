package org.example.restaurant.service;

import org.example.restaurant.exception.RestaurantNotFoundException;

public interface RestaurantService {
    long createRestaurantByName(String name);

    long createRestaurantByNameAndTelephone(String name, String telephone);
    String getRestaurantTelephone(Long id) throws RestaurantNotFoundException;
    String getRestaurantNameById(Long id) throws RestaurantNotFoundException;
}
