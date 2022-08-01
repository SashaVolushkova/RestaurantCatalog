package org.example.restaurant.service;

import org.example.restaurant.AppContextTest;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

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

    @Test
    void createRestaurantByNameAndDateExpirationDate() throws FoundationDateIsExpiredException, RestaurantNotFoundException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultNow = LocalDate.of(2014, 12, 22);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultNow);

        Assertions.assertThrowsExactly(FoundationDateIsExpiredException.class,
                () -> restaurantService.createRestaurantByNameAndDate("test", LocalDate.of(2015, 12, 12)),
                "Restaurant with name \"" + "test" + "\"" +
                        "has foundation date " + LocalDate.now().plusDays(5));

        long test = restaurantService.createRestaurantByNameAndDate("test", LocalDate.of(2012, 12, 12));
        LocalDate foundationDate = restaurantService.getFoundationDate(test);
        assertEquals(LocalDate.of(2012, 12, 12), foundationDate);
    }
}