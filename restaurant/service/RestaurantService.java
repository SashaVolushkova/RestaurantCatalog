package restaurant.service;
import restaurant.dto.in.RestaurantInDTO;
import restaurant.exception.FoundationDateIsExpiredException;
import restaurant.exception.RestaurantNotFoundException;
import restaurant.model.RestaurantEntity;

import java.time.LocalDate;

public interface RestaurantService {
    long createRestaurantByName(String name);
    long createRestaurantByNameAndDate(String name, LocalDate foundationDate) throws FoundationDateIsExpiredException;

    LocalDate getFoundationDate(Long id) throws RestaurantNotFoundException;

    long createRestaurantByNameAndTelephone(String name, String telephone);
    String getRestaurantTelephone(Long id) throws RestaurantNotFoundException;
    String getRestaurantNameById(Long id) throws RestaurantNotFoundException;

    RestaurantEntity createRestaurant(RestaurantInDTO restaurant) throws FoundationDateIsExpiredException;

    RestaurantEntity getRestaurant(Long restaurantId) throws RestaurantNotFoundException;
}
