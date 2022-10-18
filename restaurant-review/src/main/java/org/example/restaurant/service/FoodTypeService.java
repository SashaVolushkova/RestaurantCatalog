package org.example.restaurant.service;

import org.example.restaurant.dto.in.FoodTypeInDTO;
import org.example.restaurant.dto.in.FoodTypeUpdateInDTO;
import org.example.restaurant.exception.FoodTypeNotFoundException;

public interface FoodTypeService {
    Long addFoodType(FoodTypeInDTO foodType);
    void update(FoodTypeUpdateInDTO foodType) throws FoodTypeNotFoundException;
}
