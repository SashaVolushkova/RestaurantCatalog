package org.example.restaurant.service;

import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest extends AppContextTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void createRestaurantByName() {
        String name = "testName";
        long restaurantByName = restaurantService.createRestaurantByName(name);
        assertTrue(restaurantByName > 0);
    }

    @Test
    void getRestaurantNameById() throws RestaurantNotFoundException {
        String name = "testName";
        long restaurantByName = restaurantService.createRestaurantByName(name);
        String restaurantNameById = restaurantService.getRestaurantNameById(restaurantByName);
        assertEquals(name, restaurantNameById);
    }

    @Test
    void getRestaurantNameByIdException() {
        Assertions.assertThrowsExactly(RestaurantNotFoundException.class,
                () -> restaurantService.getRestaurantNameById(333L));
    }

    @Test
    void getTelephoneNumber() throws RestaurantNotFoundException {
        long number = restaurantService.createRestaurantByNameAndTelephone("number", "+79999999999");
        String restaurantTelephone = restaurantService.getRestaurantTelephone(number);
        assertEquals("+79999999999", restaurantTelephone);
        Assertions.assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurantTelephone(444L));
    }
}