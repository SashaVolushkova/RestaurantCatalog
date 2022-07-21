package org.example.restaurant.repository;

import org.example.restaurant.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByRestaurantId(Long restaurantId);
}
