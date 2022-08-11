package org.example.restaurant.repository;

import org.example.restaurant.model.RestaurantEntity;
import org.example.restaurant.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    //@Query("from ReviewEntity e where e.restaurant.id = :restaurantId")
    List<ReviewEntity> findAllByRestaurant(RestaurantEntity restaurantId);
}
