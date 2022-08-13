
package restaurant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.in.RestaurantInDTO;
import restaurant.dto.out.RestaurantOutDTO;
import restaurant.exception.FoundationDateIsExpiredException;
import restaurant.exception.RestaurantNotFoundException;
import restaurant.mapper.RestaurantMapper;
import restaurant.model.RestaurantEntity;
import restaurant.service.RestaurantService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

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
    public RestaurantOutDTO addRestaurant(@Valid @RequestBody RestaurantInDTO restaurant) throws
            NumberFormatException, FoundationDateIsExpiredException {
        RestaurantEntity restaurantEntity = restaurantService.createRestaurant(restaurant);
        return restaurantMapper.restaurantEntityToRestaurantOutDTO(restaurantEntity);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantOutDTO getRestaurant(@PathVariable Long restaurantId) throws RestaurantNotFoundException {
        RestaurantEntity restaurantEntity = restaurantService.getRestaurant(restaurantId);
        return restaurantMapper.toDto(restaurantEntity);
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
