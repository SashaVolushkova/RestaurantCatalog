package org.example.restaurant.repository.habr;

import org.example.restaurant.model.FoodTypeEntity;
import org.example.restaurant.model.habr.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
