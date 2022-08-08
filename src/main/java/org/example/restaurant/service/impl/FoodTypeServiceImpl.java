package org.example.restaurant.service.impl;

import org.example.restaurant.dto.in.FoodTypeInDTO;
import org.example.restaurant.dto.in.FoodTypeUpdateInDTO;
import org.example.restaurant.exception.FoodTypeNotFoundException;
import org.example.restaurant.model.FoodTypeEntity;
import org.example.restaurant.repository.FoodTypeRepository;
import org.example.restaurant.service.FoodTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {
    private final FoodTypeRepository foodTypeRepository;

    public FoodTypeServiceImpl(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }

    @Override
    public Long addFoodType(FoodTypeInDTO foodType) {
        FoodTypeEntity foodTypeEntity = FoodTypeEntity.builder()
                .name(foodType.getName())
                .description(foodType.getDescription())
                .build();
        FoodTypeEntity save = foodTypeRepository.save(foodTypeEntity);
        return save.getId();
    }

    @Override
    @Transactional
    public void update(FoodTypeUpdateInDTO foodType) throws FoodTypeNotFoundException {
        FoodTypeEntity foodTypeEntity = get(foodType.getId());
        foodTypeEntity.setDescription(foodType.getDescription());
        foodTypeEntity.setName(foodType.getName());
        //foodTypeRepository.save(foodTypeEntity);
    }

    private FoodTypeEntity get(Long id) throws FoodTypeNotFoundException {
        Optional<FoodTypeEntity> foodTypeEntity = foodTypeRepository.findById(id);
        if(foodTypeEntity.isPresent()) {
            return foodTypeEntity.get();
        }
        throw new FoodTypeNotFoundException(id);
    }
}
