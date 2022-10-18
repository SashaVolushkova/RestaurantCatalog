package org.example.restaurant.controller;

import org.example.restaurant.dto.in.FoodTypeInDTO;
import org.example.restaurant.dto.in.FoodTypeUpdateInDTO;
import org.example.restaurant.exception.FoodTypeNotFoundException;
import org.example.restaurant.service.FoodTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodTypeController {
    private final FoodTypeService foodTypeService;

    public FoodTypeController(FoodTypeService foodTypeService) {
        this.foodTypeService = foodTypeService;
    }

    @PostMapping("/food_type")
    public Long createFoodType(@RequestBody FoodTypeInDTO foodType) {
        return foodTypeService.addFoodType(foodType);
    }

    @PutMapping("/food_type")
    public void updateFoodType(@RequestBody FoodTypeUpdateInDTO foodType) throws FoodTypeNotFoundException {
        foodTypeService.update(foodType);
    }

}
