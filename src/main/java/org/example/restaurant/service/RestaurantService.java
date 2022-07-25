package org.example.restaurant.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.model.RestaurantEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RestaurantService {
    long createRestaurantByName(String name);
    long createRestaurantByNameAndDate(String name, LocalDate foundationDate) throws FoundationDateIsExpiredException;

    LocalDate getFoundationDate(Long id) throws RestaurantNotFoundException;

    long createRestaurantByNameAndTelephone(String name, String telephone);
    String getRestaurantTelephone(Long id) throws RestaurantNotFoundException;
    String getRestaurantNameById(Long id) throws RestaurantNotFoundException;

    RestaurantEntity createRestaurant(RestaurantInDTO restaurant) throws NumberParseException;

    RestaurantEntity getRestaurant(Long restaurantId) throws RestaurantNotFoundException;
}
