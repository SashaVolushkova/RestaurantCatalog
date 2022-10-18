package org.example.restaurant.repository;

import org.example.restaurant.model.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    RestaurantEntity findFirstByName(String name);
}
