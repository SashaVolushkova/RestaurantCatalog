
package org.example.restaurant.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import org.example.restaurant.dto.in.RestaurantInDTO;
import org.example.restaurant.dto.out.RestaurantOutDTO;
import org.example.restaurant.exception.FoundationDateIsExpiredException;
import org.example.restaurant.exception.RestaurantNotFoundException;
import org.example.restaurant.mapper.RestaurantMapper;
import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public RestaurantOutDTO addRestaurant(@Valid @RequestBody RestaurantInDTO restaurant) throws NumberParseException, FoundationDateIsExpiredException {
        RestaurantEntity restaurantEntity = restaurantService.createRestaurant(restaurant);
        return restaurantMapper.restaurantEntityToRestaurantOutDTO(restaurantEntity);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantOutDTO getRestaurant(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
        RestaurantEntity restaurantEntity = restaurantService.getRestaurant(restaurantId);
        return restaurantMapper.restaurantEntityToRestaurantOutDTO(restaurantEntity);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
