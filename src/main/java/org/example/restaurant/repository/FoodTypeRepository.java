package org.example.restaurant.repository;

import org.example.restaurant.model.FoodTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodTypeEntity, Long> {
    FoodTypeEntity findByName(String name);
}
