package org.example.restaurant.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "food type не найден")
@Getter
@ToString
public class FoodTypeNotFoundException extends Exception {
    private final Long foodTypeId;

    public FoodTypeNotFoundException(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }
}
