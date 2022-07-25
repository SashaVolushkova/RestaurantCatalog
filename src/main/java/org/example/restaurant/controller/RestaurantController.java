
package org.example.restaurant.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.dto.out.RestaurantOutDTO;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.mapper.RestaurantMapper;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantMapper restaurantMapper,
                                RestaurantService restaurantService) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant")
    public RestaurantOutDTO addRestaurant(@RequestBody RestaurantInDTO restaurant) throws NumberParseException {
        RestaurantEntity restaurantEntity = restaurantService.createRestaurant(restaurant);
        return restaurantMapper.restaurantEntityToRestaurantOutDTO(restaurantEntity);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantOutDTO getRestaurant(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
        RestaurantEntity restaurantEntity = restaurantService.getRestaurant(restaurantId);
        return restaurantMapper.restaurantEntityToRestaurantOutDTO(restaurantEntity);
    }
}
